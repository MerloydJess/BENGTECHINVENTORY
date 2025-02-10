package com.benguettech.inventory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benguettech.inventory.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    fun register(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = authRepository.register(email, password)
            onResult(success)
        }
    }

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = authRepository.login(email, password)
            onResult(success)
        }
    }
}
