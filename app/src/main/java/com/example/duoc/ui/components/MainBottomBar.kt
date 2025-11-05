package com.example.duoc.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MainBottomBar(
    selectedTab: Int,
    onHomeClick: () -> Unit,
    onProductsClick: () -> Unit,
    onCartClick: () -> Unit,
    backgroundColor: Color = Color(0xFF000000),
    darkGray: Color = Color(0xFF1A1A1A),
    neonGreen: Color = Color(0xFF00FF9F)
) {
    NavigationBar(
        containerColor = darkGray,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            icon = { androidx.compose.material3.Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home", color = if (selectedTab == 0) neonGreen else Color.White) },
            selected = selectedTab == 0,
            onClick = onHomeClick,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = neonGreen,
                unselectedIconColor = Color.White,
                indicatorColor = backgroundColor
            )
        )
        NavigationBarItem(
            icon = { androidx.compose.material3.Icon(Icons.Default.ShoppingBag, contentDescription = "Productos") },
            label = { Text("Productos", color = if (selectedTab == 1) neonGreen else Color.White) },
            selected = selectedTab == 1,
            onClick = onProductsClick,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = neonGreen,
                unselectedIconColor = Color.White,
                indicatorColor = backgroundColor
            )
        )
        NavigationBarItem(
            icon = {
                BadgedBox(badge = { Badge { Text("0") } }) {
                    androidx.compose.material3.Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
                }
            },
            label = { Text("Carrito", color = if (selectedTab == 2) neonGreen else Color.White) },
            selected = selectedTab == 2,
            onClick = onCartClick,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = neonGreen,
                unselectedIconColor = Color.White,
                indicatorColor = backgroundColor
            )
        )
    }
}

