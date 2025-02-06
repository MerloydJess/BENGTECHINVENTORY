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
            auth.createUserWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun loginUser(email: String, password: String): Boolean {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun addInventoryItem(item: InventoryItem): Boolean {
        return try {
            firestore.collection("inventory").document(item.id).set(item).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun deleteInventoryItem(itemId: String): Boolean {
        return try {
            firestore.collection("inventory").document(itemId).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getInventoryList(): List<InventoryItem> {
        return try {
            firestore.collection("inventory").get().await().documents.mapNotNull { doc ->
                doc.toObject(InventoryItem::class.java)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
