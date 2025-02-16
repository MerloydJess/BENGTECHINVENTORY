package com.benguettech.inventory.data.repository

import com.benguettech.inventory.FirebaseService
import com.benguettech.inventory.data.model.InventoryItem
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class InventoryRepository @Inject constructor(private val firebaseService: FirebaseService) {

    suspend fun addInventoryItem(item: InventoryItem): Boolean {
        return try {
            firebaseService.addInventoryItem(item)
        } catch (e: Exception) {
            println("Error adding item: ${e.message}")
            false
        }
    }

    suspend fun deleteInventoryItem(itemId: String): Boolean {
        return try {
            firebaseService.deleteInventoryItem(itemId)
        } catch (e: Exception) {
            println("Error deleting item: ${e.message}")
            false
        }
    }

    suspend fun getInventoryItems(): List<InventoryItem> {
        return try {
            firebaseService.getInventoryList()
        } catch (e: Exception) {
            println("Error fetching inventory: ${e.message}")
            emptyList()
        }
    }

    suspend fun updateInventoryItem(item: InventoryItem): Boolean {
        return try {
            firebaseService.firestore.collection("inventory")
                .document(item.id)
                .set(item, SetOptions.merge())
                .await()
            true
        } catch (e: Exception) {
            println("Error updating item: ${e.message}")
            false
        }
    }
}
