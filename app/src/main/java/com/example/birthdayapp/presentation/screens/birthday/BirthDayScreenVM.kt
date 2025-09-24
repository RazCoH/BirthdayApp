package com.example.birthdayapp.presentation.screens.birthday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.birthdayapp.data.models.BirthdayItem
import com.example.birthdayapp.data.models.SocketResult
import com.example.birthdayapp.data.repositories.BirthdayRepository
import com.example.birthdayapp.utils.Error
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BirthDayScreenVM(
    private val birthdayRepo: BirthdayRepository,
    private val hostIp: String
) : ViewModel() {

    private val _birthdayUIState = MutableStateFlow<BirthdayUIState>(BirthdayUIState.Loading)
    val birthdayState: StateFlow<BirthdayUIState> = _birthdayUIState

    private val _birthdayUIEvents = MutableSharedFlow<BirthdayUIEvent>()
    val birthdayUIEvent = _birthdayUIEvents.asSharedFlow()

    init {
        viewModelScope.launch {
            observeBirthdayUpdates(hostIp)
        }
    }

    private suspend fun observeBirthdayUpdates(host: String) {
        birthdayRepo.observeBirthdaySocket(host).collectLatest {
            when (val result = it) {
                is SocketResult.Failure -> {
                    _birthdayUIEvents.emit(BirthdayUIEvent.ShowError(result.error))
                }

                is SocketResult.Success<*> -> {
                    (result.data as? BirthdayItem)?.let { birthdayItem ->
                        _birthdayUIState.value = BirthdayUIState.ShowBirthdayUI(birthdayItem)
                    } ?: run {
                        _birthdayUIEvents.emit(BirthdayUIEvent.ShowError(Error.GeneralError))
                    }
                }
            }
        }
    }
}