package com.benguettech.inventory.data.repository

import com.benguettech.inventory.FirebaseService

class AuthRepository(private val firebaseService: FirebaseService) {

    suspend fun register(email: String, password: String): Boolean {
        return firebaseService.registerUser(email, password)
    }

    suspend fun login(email: String, password: String): Boolean {
        return firebaseService.loginUser(email, password)
    }
}