package com.benguettech.inventory.data.repository

import com.benguettech.inventory.FirebaseService
import javax.inject.Inject

class AuthRepository @Inject constructor(private val firebaseService: FirebaseService) {

    suspend fun register(email: String, password: String): Boolean {
        return try {
            firebaseService.registerUser(email, password)
        } catch (e: Exception) {
            println("AuthRepository Error: ${e.message}")
            false
        }
    }

    suspend fun login(email: String, password: String): Boolean {
        return try {
            firebaseService.loginUser(email, password)
        } catch (e: Exception) {
            println("AuthRepository Error: ${e.message}")
            false
        }
    }
}
