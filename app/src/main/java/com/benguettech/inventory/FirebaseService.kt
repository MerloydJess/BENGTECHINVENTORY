package com.benguettech.inventory

import com.benguettech.inventory.data.model.InventoryItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseService(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    suspend fun registerUser(email: String, password: String): Boolean {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await() // ✅ Firebase Registration
            true
        } catch (e: Exception) {
            println("Registration Error: ${e.message}") // ✅ Debugging Log
            false
        }
    }

    suspend fun loginUser(email: String, password: String): Boolean {
        return try {
            auth.signInWithEmailAndPassword(email, password).await() // ✅ Firebase Login
            true
        } catch (e: Exception) {
            println("Login Error: ${e.message}") // ✅ Debugging Log
            false
        }
    }

    suspend fun addInventoryItem(item: InventoryItem): Boolean {
        return try {
            firestore.collection("inventory").document(item.id).set(item).await() // ✅ Add to Firestore
            true
        } catch (e: Exception) {
            println("Add Item Error: ${e.message}")
            false
        }
    }

    suspend fun deleteInventoryItem(itemId: String): Boolean {
        return try {
            firestore.collection("inventory").document(itemId).delete().await() // ✅ Delete from Firestore
            true
        } catch (e: Exception) {
            println("Delete Item Error: ${e.message}")
            false
        }
    }

    suspend fun getInventoryList(): List<InventoryItem> {
        return try {
            firestore.collection("inventory").get().await().documents.mapNotNull { doc ->
                doc.toObject(InventoryItem::class.java)
            } // ✅ Fetch from Firestore
        } catch (e: Exception) {
            println("Get Inventory Error: ${e.message}")
            emptyList()
        }
    }
}
