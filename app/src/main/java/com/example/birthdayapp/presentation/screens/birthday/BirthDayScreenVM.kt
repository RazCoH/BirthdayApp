package com.example.birthdayapp.presentation.screens.birthday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.birthdayapp.data.models.BirthdayItem
import com.example.birthdayapp.data.models.SocketResult
import com.example.birthdayapp.data.repositories.BirthdayRepository
import com.example.birthdayapp.utils.Error
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BirthDayScreenVM(private val birthdayRepo: BirthdayRepository) : ViewModel() {

    private val _birthdayUIState = MutableStateFlow<BirthdayUIState>(BirthdayUIState.Loading)
    val birthdayState: StateFlow<BirthdayUIState> = _birthdayUIState

    fun observeBirthdayUpdates(host: String) {
        viewModelScope.launch {
            birthdayRepo.observeBirthdaySocket(host).collect {
                when (val result = it) {
                    is SocketResult.Failure -> _birthdayUIState.value =
                        BirthdayUIState.ShowError(result.error)

                    is SocketResult.Success<*> -> {
                        (result.data as? BirthdayItem)?.let { birthdayItem ->
                            _birthdayUIState.value = BirthdayUIState.ShowBirthdayUI(birthdayItem)
                        } ?: run {
                            BirthdayUIState.ShowError(Error.GeneralError)
                        }
                    }
                }
            }
        }
    }
}