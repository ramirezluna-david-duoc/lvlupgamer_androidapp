package com.example.duoc.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.duoc.R
import com.example.duoc.ui.components.MainBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    onBack: () -> Unit,
    onNavigateHome: (() -> Unit)? = null,
    onNavigateToCart: (() -> Unit)? = null
) {
    val backgroundColor = Color(0xFF000000)
    val neonGreen = Color(0xFF00FF9F)
    val electricBlue = Color(0xFF00BFFF)
    val darkGray = Color(0xFF1A1A1A)

    val images = listOf(R.drawable.play5, R.drawable.play5, R.drawable.play5)

    var selectedTab by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("PlayStation 5", color = Color.White, fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Favorito (opcional) */ }) {
                        Icon(Icons.Filled.FavoriteBorder, contentDescription = "Favorito", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor)
            )
        },
        bottomBar = {
            MainBottomBar(
                selectedTab = selectedTab,
                onHomeClick = { selectedTab = 0; onNavigateHome?.invoke() },
                onProductsClick = { selectedTab = 1 },
                onCartClick = { selectedTab = 2; onNavigateToCart?.invoke() },
                backgroundColor = backgroundColor,
                darkGray = darkGray,
                neonGreen = neonGreen
            )
        },
        containerColor = backgroundColor
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(padding)
        ) {
            // Carrusel de imágenes
            ImageCarousel(
                images = images,
                indicatorActiveColor = electricBlue,
                indicatorInactiveColor = Color.DarkGray
            )

            // Sección de detalles
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("PlayStation 5", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 12.dp))
                Text("SKU: CO001", color = Color(0xFFBDBDBD), fontSize = 14.sp, modifier = Modifier.padding(top = 4.dp))

                Text(
                    "PlayStation 5: La consola de última generación de Sony, que ofrece gráficos impresionantes y tiempos de carga ultrarrápidos para una experiencia de juego inmersiva.",
                    color = Color(0xFFBDBDBD),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 12.dp)
                )

                Text("$549.990 CLP", color = neonGreen, fontSize = 28.sp, fontWeight = FontWeight.Black, modifier = Modifier.padding(top = 16.dp))

                Button(
                    onClick = { onNavigateToCart?.invoke() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = electricBlue, contentColor = Color.Black),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Filled.ShoppingCart, contentDescription = "Añadir", tint = Color.Black)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Añadir al Carrito", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
        }
    }
}

@Composable
private fun ImageCarousel(
    images: List<Int>,
    indicatorActiveColor: Color,
    indicatorInactiveColor: Color
) {
    val listState = rememberLazyListState()
    val pageCount = images.size
    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(listState.firstVisibleItemIndex) {
        currentIndex = listState.firstVisibleItemIndex
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            color = Color(0xFF0D0D0D),
            shape = RoundedCornerShape(16.dp)
        ) {
            LazyRow(
                state = listState,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp)
            ) {
                items(pageCount) { index ->
                    Image(
                        painter = painterResource(id = images[index]),
                        contentDescription = "Imagen $index",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .height(196.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            }
        }

        // Indicadores
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pageCount) { i ->
                val color = if (i == currentIndex) indicatorActiveColor else indicatorInactiveColor
                Box(
                    modifier = Modifier
                        .size(if (i == currentIndex) 10.dp else 8.dp)
                        .clip(CircleShape)
                        .background(color)
                        .border(1.dp, Color.DarkGray, CircleShape)
                )
                if (i != pageCount - 1) Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}
