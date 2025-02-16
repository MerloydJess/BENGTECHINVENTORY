package com.benguettech.inventory.data.model

enum class AccountType {
    FIXED_ASSETS, SUPPLIES, EQUIPMENT
}

    data class InventoryItem(
        val id: String = "",
        val name: String = "",
        val accountType: String? = null,
        val assignedUser: String? = null,
        val status: String = "Available" // Available, Assigned, Returned, Waste

    )
