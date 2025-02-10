package com.benguettech.inventory


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.benguettech.inventory.auth.LoginScreen
import com.benguettech.inventory.auth.RegisterScreen
import com.benguettech.inventory.ui.theme.inventory.InventoryListScreen
import com.benguettech.inventory.ui.theme.inventory.InventoryDetailScreen
import com.benguettech.inventory.ui.theme.InventoryTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔹 Ensure Firebase is initialized
        FirebaseApp.initializeApp(this)

        enableEdgeToEdge()

        auth = FirebaseAuth.getInstance()

        setContent {
            InventoryTheme {
                val navController = rememberNavController()

                // 🔹 Handle auto-login if user is already signed in
                LaunchedEffect(auth) {
                    val currentUser: FirebaseUser? = auth.currentUser
                    Log.d("MainActivity", "Current User: $currentUser")

                    if (currentUser != null) {
                        navController.navigate("inventory") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        auth = auth
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavigation(
    navController: androidx.navigation.NavHostController,
    modifier: Modifier = Modifier,
    auth: FirebaseAuth
) {
    NavHost(
        navController,
        startDestination = if (auth.currentUser != null) "inventory" else "login",
        modifier = modifier
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    Log.d("Navigation", "Navigating to Inventory")
                    navController.navigate("inventory") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    Log.d("Navigation", "Navigating to Register")
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    Log.d("Navigation", "Navigating back to Login")
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        composable("inventory") {
            Log.d("Navigation", "Showing Inventory Screen")
            InventoryListScreen()
        }

        composable("inventoryDetail/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            InventoryDetailScreen(itemId ?: "")
        }
    }
}
