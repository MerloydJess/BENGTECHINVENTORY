package com.benguettech.inventory.data.repository

import com.benguettech.inventory.FirebaseService
import com.benguettech.inventory.data.model.InventoryItem
import javax.inject.Inject

class InventoryRepository @Inject constructor(private val firebaseService: FirebaseService) {

    suspend fun addInventoryItem(item: InventoryItem): Boolean {
        return firebaseService.addInventoryItem(item)
    }

    suspend fun deleteInventoryItem(itemId: String): Boolean {
        return firebaseService.deleteInventoryItem(itemId)
    }

    suspend fun getInventoryItems(): List<InventoryItem> { // ✅ FIXED: Function added
        return firebaseService.getInventoryList()
    }

    suspend fun updateInventoryItem(item: InventoryItem): Boolean { // ✅ FIXED: Function added
        return firebaseService.addInventoryItem(item) // Firestore uses set() for updates
    }
}
