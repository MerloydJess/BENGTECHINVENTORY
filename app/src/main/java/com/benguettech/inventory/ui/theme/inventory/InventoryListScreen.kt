package com.benguettech.inventory.ui.theme.inventory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.benguettech.inventory.data.model.InventoryItem

@Composable
fun InventoryListScreen(viewModel: InventoryViewModel = viewModel()) {
    val inventoryItems by viewModel.inventoryList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadInventory()
    }

    Column {
        Button(onClick = {
            val newItem = InventoryItem(
                id = "item_${System.currentTimeMillis()}",
                name = "New Item",
                accountType = "General"
            )
            viewModel.addItem(newItem)
        }) {
            Text("Add Item")
        }

        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(inventoryItems) { item ->
                InventoryItemRow(item, viewModel)
            }
        }
    }
}

@Composable
fun InventoryItemRow(item: InventoryItem, viewModel: InventoryViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${item.name}", style = MaterialTheme.typography.headlineSmall)
            Text(text = "Status: ${item.status}")

            Button(onClick = { viewModel.deleteItem(item.id) }) {
                Text("Delete")
            }
        }
    }
}