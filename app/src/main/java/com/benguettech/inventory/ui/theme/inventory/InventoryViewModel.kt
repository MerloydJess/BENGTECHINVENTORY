package com.benguettech.inventory.ui.theme.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benguettech.inventory.data.model.InventoryItem
import com.benguettech.inventory.data.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val repository: InventoryRepository
) : ViewModel() {

    private val _inventoryList = MutableStateFlow<List<InventoryItem>>(emptyList())
    val inventoryList: StateFlow<List<InventoryItem>> = _inventoryList

    init {
        loadInventory() // ✅ Auto-load items when ViewModel is created
    }

    fun loadInventory() {
        viewModelScope.launch {
            try {
                val items = repository.getInventoryItems()
                _inventoryList.value = items
            } catch (e: Exception) {
                println("Error loading inventory: ${e.message}")
            }
        }
    }

    fun addItem(item: InventoryItem) {
        viewModelScope.launch {
            try {
                repository.addInventoryItem(item)
                loadInventory()
            } catch (e: Exception) {
                println("Error adding item: ${e.message}")
            }
        }
    }

    fun updateItem(item: InventoryItem) {
        viewModelScope.launch {
            try {
                repository.updateInventoryItem(item)
                loadInventory()
            } catch (e: Exception) {
                println("Error updating item: ${e.message}")
            }
        }
    }

    fun deleteItem(itemId: String) {
        viewModelScope.launch {
            try {
                repository.deleteInventoryItem(itemId)
                loadInventory()
            } catch (e: Exception) {
                println("Error deleting item: ${e.message}")
            }
        }
    }
}
