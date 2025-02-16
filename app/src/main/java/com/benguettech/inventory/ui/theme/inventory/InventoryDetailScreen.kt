package com.benguettech.inventory.ui.theme.inventory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun InventoryDetailScreen(
    itemId: String,
    viewModel: InventoryViewModel = hiltViewModel() // ✅ Ensure Hilt is used
) {
    val inventoryItems by viewModel.inventoryList.collectAsState()
    val item = inventoryItems.find { it.id == itemId }

    if (item == null) {
        Text("Item not found", color = MaterialTheme.colorScheme.error) // ✅ Prevent infinite loading
        return
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Item Name: ${item.name}", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Account Type: ${item.accountType ?: "N/A"}")
        Text(text = "Assigned User: ${item.assignedUser ?: "None"}")
        Text(text = "Status: ${item.status}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.updateItem(item.copy(name = "Updated Name")) }) {
            Text("Edit Item")
        }

        Button(onClick = { viewModel.deleteItem(item.id) }) {
            Text("Delete Item")
        }
    }
}
