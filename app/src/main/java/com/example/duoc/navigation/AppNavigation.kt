package com.example.duoc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.duoc.ui.screen.HomeScreen
import com.example.duoc.ui.screen.LoginScreen
import com.example.duoc.ui.screen.CatalogScreen
import com.example.duoc.ui.screen.ProductDetailScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object Catalog : Screen("catalog")
    object ProductDetail : Screen("product_detail")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onNavigateToCatalog = {
                    navController.navigate(Screen.Catalog.route)
                }
            )
        }

        composable(Screen.Catalog.route) {
            CatalogScreen(
                onBack = { navController.popBackStack() },
                onNavigateHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = false }
                    }
                },
                onNavigateToCart = {
                    // TODO: Navegar a pantalla de carrito cuando exista
                },
                onOpenProductDetail = {
                    navController.navigate(Screen.ProductDetail.route)
                }
            )
        }

        composable(Screen.ProductDetail.route) {
            ProductDetailScreen(
                onBack = { navController.popBackStack() },
                onNavigateHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = false }
                    }
                },
                onNavigateToCart = {
                    // TODO: Navegar a pantalla de carrito cuando exista
                }
            )
        }
    }
}
