package com.benguettech.inventory.ui.theme.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benguettech.inventory.FirebaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseService: FirebaseService
) : ViewModel() {

    private val _authState = MutableStateFlow<Boolean>(false)
    val authState: StateFlow<Boolean> = _authState

    fun register(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val success = firebaseService.registerUser(email, password)
                withContext(Dispatchers.Main) {
                    onResult(success)
                }
            } catch (e: Exception) {
                println("AuthViewModel Error: ${e.message}")
                withContext(Dispatchers.Main) { onResult(false) }
            }
        }
    }

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val success = firebaseService.loginUser(email, password)
                withContext(Dispatchers.Main) {
                    onResult(success)
                }
            } catch (e: Exception) {
                println("AuthViewModel Error: ${e.message}")
                withContext(Dispatchers.Main) { onResult(false) }
            }
        }
    }
}
