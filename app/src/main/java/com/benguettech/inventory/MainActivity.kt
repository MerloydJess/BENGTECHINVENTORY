package com.benguettech.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.benguettech.inventory.auth.LoginScreen
import com.benguettech.inventory.auth.RegisterScreen
import com.benguettech.inventory.ui.theme.inventory.InventoryListScreen
import com.benguettech.inventory.ui.theme.inventory.InventoryDetailScreen
import com.benguettech.inventory.ui.theme.InventoryTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InventoryTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavigation(navController: androidx.navigation.NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = "login", modifier = modifier) {
        composable("login") { LoginScreen { navController.navigate("inventory") } }
        composable("register") { RegisterScreen { navController.navigate("login") } }
        composable("inventory") { InventoryListScreen() }
        composable("inventoryDetail/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            InventoryDetailScreen(itemId ?: "")
        }
    }
}
