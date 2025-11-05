package com.example.duoc.ui.screen

import com.example.duoc.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.annotation.DrawableRes
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.duoc.ui.components.MainBottomBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    onNavigateToCatalog: () -> Unit = {}
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedTab by remember { mutableStateOf(0) }

    // Colores personalizados
    val backgroundColor = Color(0xFF000000)
    val neonGreen = Color(0xFF00FF9F)
    val electricBlue = Color(0xFF00BFFF)
    val darkGray = Color(0xFF1A1A1A)
    val cardBackground = Color(0xFF1A1A2E)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = darkGray
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Header del Drawer
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        color = backgroundColor,
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Logo dentro del recuadro del header
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "Logo Level Up Gamer",
                                modifier = Modifier
                                    .size(72.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = "Level Up Gamer",
                                color = neonGreen,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    HorizontalDivider(color = Color.Gray, modifier = Modifier.padding(vertical = 8.dp))

                    // Opciones del menú
                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = null, tint = electricBlue) },
                        label = { Text("Inicio", color = Color.White) },
                        selected = true,
                        onClick = {
                            scope.launch { drawerState.close() }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = backgroundColor,
                            unselectedContainerColor = Color.Transparent
                        )
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.ShoppingBag, contentDescription = null, tint = electricBlue) },
                        label = { Text("Productos", color = Color.White) },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            onNavigateToCatalog()
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = Color.Transparent
                        )
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = electricBlue) },
                        label = { Text("Carrito de Compras", color = Color.White) },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    NavigationDrawerItem(
                        icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null, tint = Color.Red) },
                        label = { Text("Cerrar Sesión", color = Color.Red) },
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                onLogout()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Level Up Gamer",
                            color = neonGreen,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
//                        IconButton(onClick = { /* Acción de búsqueda */ }) {
//                            Icon(
//                                imageVector = Icons.Default.Search,
//                                contentDescription = "Buscar",
//                                tint = electricBlue
//                            )
//                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = backgroundColor
                    )
                )
            },
            bottomBar = {
                MainBottomBar(
                    selectedTab = selectedTab,
                    onHomeClick = { selectedTab = 0 },
                    onProductsClick = { onNavigateToCatalog() },
                    onCartClick = { selectedTab = 2 },
                    backgroundColor = backgroundColor,
                    darkGray = darkGray,
                    neonGreen = neonGreen
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                // Banner de bienvenida
                item {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        color = cardBackground,
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Logo de la empresa en vez de emoji
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "Logo Level Up Gamer",
                                modifier = Modifier
                                    .size(72.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = "Level Up Gamer",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            Text(
                                text = "Tu tienda para encontrar los mejores productos y accesorios para llevar tu experiencia de juego al siguiente nivel.",
                                color = Color.Gray,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }

                // Título de Productos Destacados
                item {
                    Text(
                        text = "Productos Destacados",
                        color = electricBlue,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }

                // Productos Destacados - 3 columnas
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Producto 1
                        ProductCard(
                            modifier = Modifier.weight(1f),
                            imageResId = R.drawable.play5, // TODO: reemplazar por imagen real de PS5
                            name = "PlayStation 5",
                            price = "$549.990 CLP",
                            neonGreen = neonGreen,
                            electricBlue = electricBlue,
                            cardBackground = cardBackground
                        )

                        // Producto 2
                        ProductCard(
                            modifier = Modifier.weight(1f),
                            imageResId = R.drawable.pc, // TODO: reemplazar por imagen real de PC ASUS ROG Strix
                            name = "PC Gamer ASUS ROG Strix",
                            price = "$1.299.990 CLP",
                            neonGreen = neonGreen,
                            electricBlue = electricBlue,
                            cardBackground = cardBackground
                        )

                        // Producto 3
                        ProductCard(
                            modifier = Modifier.weight(1f),
                            imageResId = R.drawable.mouse, // TODO: reemplazar por imagen real del Mouse Logitech G502 HERO
                            name = "Mouse Gamer Logitech G502 HERO",
                            price = "$49.990 CLP",
                            neonGreen = neonGreen,
                            electricBlue = electricBlue,
                            cardBackground = cardBackground
                        )
                    }
                }

                // Título de Categorías
                item {
                    Text(
                        text = "Categorías",
                        color = electricBlue,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }

                // Categorías - Grid 4x2 (8 categorías)
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Fila 1
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            CategoryCard(
                                modifier = Modifier.weight(1f),
                                imageResId = R.drawable.catan2,
                                name = "Juegos de Mesa",
                                cardBackground = cardBackground
                            )
                            CategoryCard(
                                modifier = Modifier.weight(1f),
                                imageResId = R.drawable.auric,
                                name = "Accesorios",
                                cardBackground = cardBackground
                            )
                        }

                        // Fila 2
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            CategoryCard(
                                modifier = Modifier.weight(1f),
                                imageResId = R.drawable.play5,
                                name = "Consolas",
                                cardBackground = cardBackground
                            )
                            CategoryCard(
                                modifier = Modifier.weight(1f),
                                imageResId = R.drawable.pc2,
                                name = "Computadores Gamers",
                                cardBackground = cardBackground
                            )
                        }

                        // Fila 3
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            CategoryCard(
                                modifier = Modifier.weight(1f),
                                imageResId = R.drawable.silla2,
                                name = "Sillas Gamers",
                                cardBackground = cardBackground
                            )
                            CategoryCard(
                                modifier = Modifier.weight(1f),
                                imageResId = R.drawable.mouse2,
                                name = "Mouse",
                                cardBackground = cardBackground
                            )
                        }

                        // Fila 4
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            CategoryCard(
                                modifier = Modifier.weight(1f),
                                imageResId = R.drawable.mousepad2,
                                name = "Mousepad",
                                cardBackground = cardBackground
                            )
                            CategoryCard(
                                modifier = Modifier.weight(1f),
                                imageResId = R.drawable.polera_negra,
                                name = "Poleras Personalizadas",
                                cardBackground = cardBackground
                            )
                        }
                    }
                }

                // Espaciado final
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    @DrawableRes imageResId: Int?,
    name: String,
    price: String,
    neonGreen: Color,
    electricBlue: Color,
    cardBackground: Color
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = cardBackground),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen del producto (desde recursos)
            Surface(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                color = Color(0xFF0D0D0D)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (imageResId != null) {
                        Image(
                            painter = painterResource(id = imageResId),
                            contentDescription = name,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = name,
                            tint = Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Nombre del producto
            Text(
                text = name,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp,
                maxLines = 2,
                modifier = Modifier.height(40.dp)
            )

            // Precio
            Text(
                text = price,
                color = neonGreen,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            // Botón Añadir al carrito
            Button(
                onClick = { /* Añadir al carrito */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = electricBlue.copy(alpha = 0.2f),
                    contentColor = electricBlue
                ),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Añadir",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Añadir",
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    @DrawableRes imageResId: Int,
    name: String,
    cardBackground: Color
) {
    Card(
        modifier = modifier.height(120.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackground),
        shape = RoundedCornerShape(12.dp),
        onClick = { /* Navegar a categoría */ }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Imagen de la categoría
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = name,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = name,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
