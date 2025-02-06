package com.benguettech.inventory.data.repository

import com.benguettech.inventory.FirebaseService
import com.benguettech.inventory.data.model.InventoryItem

class InventoryRepository(private val firebaseService: FirebaseService) {

    suspend fun addInventoryItem(item: InventoryItem) {
        firebaseService.addInventoryItem(item)
    }

    suspend fun updateInventoryItem(item: InventoryItem) {
        firebaseService.addInventoryItem(item) // Firestore `set()` overwrites existing data
    }

    suspend fun deleteInventoryItem(itemId: String) {
        firebaseService.deleteInventoryItem(itemId)
    }

    suspend fun getInventoryItems(): List<InventoryItem> {
        return firebaseService.getInventoryList()
    }
}
