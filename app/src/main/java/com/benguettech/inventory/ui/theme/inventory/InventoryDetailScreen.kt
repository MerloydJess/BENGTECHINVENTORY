package com.benguettech.inventory.ui.theme.inventory


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.benguettech.inventory.data.model.InventoryItem

@Composable
fun InventoryDetailScreen(
    itemId: String,
    viewModel: InventoryViewModel = viewModel()
) {
    var item by remember { mutableStateOf<InventoryItem?>(null) }

    LaunchedEffect(itemId) {
        item = viewModel.inventoryList.value.find { it.id == itemId }
    }

    item?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Item Name: ${it.name}", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Account Type: ${it.accountType}")
            Text(text = "Assigned User: ${it.assignedUser ?: "None"}")
            Text(text = "Status: ${it.status}")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { viewModel.updateItem(it.copy(name = "Updated Name")) }) {
                Text("Edit Item")
            }

            Button(onClick = { viewModel.deleteItem(it.id) }) {
                Text("Delete Item")
            }
        }
    } ?: CircularProgressIndicator()
}
