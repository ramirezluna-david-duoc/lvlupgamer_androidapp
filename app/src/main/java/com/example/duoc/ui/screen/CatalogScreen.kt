package com.example.duoc.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.duoc.model.Product
import com.example.duoc.viewmodel.CatalogViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel = viewModel(),
    onBack: (() -> Unit)? = null
) {
    // Colores del proyecto (consistentes con Home)
    val backgroundColor = Color(0xFF000000)
    val neonGreen = Color(0xFF00FF9F)
    val electricBlue = Color(0xFF00BFFF)
    val cardBackground = Color(0xFF1A1A2E)
    val darkGray = Color(0xFF1A1A1A)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Nuestros Productos", color = Color.White, fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    if (onBack != null) {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Navegar al carrito */ }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Carrito", tint = electricBlue)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor)
            )
        },
        containerColor = backgroundColor
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            // Barra de búsqueda
            SearchBar(
                query = viewModel.query,
                onQueryChange = viewModel::onQueryChange,
                electricBlue = electricBlue,
                darkGray = darkGray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Catálogo de Productos",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Grid 2 columnas (Rows con 2 items)
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                val products = viewModel.products
                items(products.chunked(2)) { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        rowItems.forEach { product ->
                            ProductCard(
                                modifier = Modifier.weight(1f),
                                product = product,
                                cardBackground = cardBackground,
                                neonGreen = neonGreen,
                                electricBlue = electricBlue,
                                onAddToCart = { /* TODO: Integrar con carrito */ }
                            )
                        }
                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    electricBlue: Color,
    darkGray: Color
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = { Text("Buscar en Level Up Gamer...", color = Color(0xFFBDBDBD)) },
        leadingIcon = {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
            ) {
                Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFF00C853))
            }
        },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = darkGray,
            unfocusedContainerColor = darkGray,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = electricBlue,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
private fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    cardBackground: Color,
    neonGreen: Color,
    electricBlue: Color,
    onAddToCart: (Product) -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = cardBackground),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            // Imagen
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp)),
                color = Color(0xFF0D0D0D)
            ) {
                Image(
                    painter = painterResource(id = product.imageResId),
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(product.name, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(product.category, color = Color(0xFFBDBDBD), fontSize = 12.sp)

            Spacer(modifier = Modifier.height(8.dp))

            Text(product.price, color = neonGreen, fontSize = 16.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onAddToCart(product) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = electricBlue, contentColor = Color.White),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Agregar al carrito", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
