package com.benguettech.inventory.ui.theme.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benguettech.inventory.data.model.InventoryItem
import com.benguettech.inventory.data.repository.InventoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InventoryViewModel(private val repository: InventoryRepository) : ViewModel() {

    private val _inventoryList = MutableStateFlow<List<InventoryItem>>(emptyList())
    val inventoryList: StateFlow<List<InventoryItem>> = _inventoryList

    fun loadInventory() {
        viewModelScope.launch {
            val items = repository.getInventoryItems() // ✅ Calls Repository
            _inventoryList.value = items
        }
    }

    fun addItem(item: InventoryItem) {
        viewModelScope.launch {
            repository.addInventoryItem(item) // ✅ Calls Repository
            loadInventory() // Refresh the list
        }
    }

    fun updateItem(item: InventoryItem) {
        viewModelScope.launch {
            repository.updateInventoryItem(item) // ✅ Calls Repository
            loadInventory() // Refresh the list
        }
    }

    fun deleteItem(itemId: String) {
        viewModelScope.launch {
            repository.deleteInventoryItem(itemId) // ✅ Calls Repository
            loadInventory() // Refresh the list
        }
    }
}
