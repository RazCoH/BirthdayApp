package com.example.birthdayapp.presentation.screens.birthday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.birthdayapp.data.models.SocketResult
import com.example.birthdayapp.data.repositories.BirthdayRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BirthDayScreenVM(private val birthdayRepo: BirthdayRepository) : ViewModel() {

    private val _birthdayState = MutableStateFlow<SocketResult?>(null)
    val birthdayState: StateFlow<SocketResult?> = _birthdayState

    fun observeBirthdayUpdates(host:String) {
        viewModelScope.launch {
            birthdayRepo.observeBirthdaySocket(host).collect { result ->
                _birthdayState.value = result
            }
        }
    }
}