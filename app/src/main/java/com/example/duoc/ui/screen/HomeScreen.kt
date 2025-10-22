package com.example.duoc.ui.screen

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLogout: () -> Unit
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
                            Text(
                                text = "",
                                fontSize = 48.sp,
                                modifier = Modifier.padding(bottom = 8.dp)
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
                NavigationBar(
                    containerColor = darkGray,
                    contentColor = Color.White
                ) {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home", color = if (selectedTab == 0) neonGreen else Color.White) },
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = neonGreen,
                            unselectedIconColor = Color.White,
                            indicatorColor = backgroundColor
                        )
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.ShoppingBag, contentDescription = "Productos") },
                        label = { Text("Productos", color = if (selectedTab == 1) neonGreen else Color.White) },
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = neonGreen,
                            unselectedIconColor = Color.White,
                            indicatorColor = backgroundColor
                        )
                    )
                    NavigationBarItem(
                        icon = {
                            BadgedBox(badge = { Badge { Text("0") } }) {
                                Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
                            }
                        },
                        label = { Text("Carrito", color = if (selectedTab == 2) neonGreen else Color.White) },
                        selected = selectedTab == 2,
                        onClick = { selectedTab = 2 },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = neonGreen,
                            unselectedIconColor = Color.White,
                            indicatorColor = backgroundColor
                        )
                    )
                }
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
                            Text(
                                text = "🎮",
                                fontSize = 48.sp
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
                            emoji = "⌨️",
                            name = "Teclado Mecánico RGB",
                            price = "$120.00",
                            neonGreen = neonGreen,
                            electricBlue = electricBlue,
                            cardBackground = cardBackground
                        )

                        // Producto 2
                        ProductCard(
                            modifier = Modifier.weight(1f),
                            emoji = "🖱️",
                            name = "Mouse de Alta Precisión",
                            price = "$80.00",
                            neonGreen = neonGreen,
                            electricBlue = electricBlue,
                            cardBackground = cardBackground
                        )

                        // Producto 3
                        ProductCard(
                            modifier = Modifier.weight(1f),
                            emoji = "🎧",
                            name = "Headset Gaming 7.1",
                            price = "$150.00",
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

                // Categorías - Grid 2x3
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
                                emoji = "⌨️",
                                name = "Teclados",
                                cardBackground = cardBackground,
                                neonGreen = neonGreen
                            )
                            CategoryCard(
                                modifier = Modifier.weight(1f),
                                emoji = "🖱️",
                                name = "Mouses",
                                cardBackground = cardBackground,
                                neonGreen = neonGreen
                            )
                        }

                        // Fila 2
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            CategoryCard(
                                modifier = Modifier.weight(1f),
                                emoji = "🖥️",
                                name = "Monitores",
                                cardBackground = cardBackground,
                                neonGreen = neonGreen
                            )
                            CategoryCard(
                                modifier = Modifier.weight(1f),
                                emoji = "🪑",
                                name = "Sillas Gamer",
                                cardBackground = cardBackground,
                                neonGreen = neonGreen
                            )
                        }

                        // Fila 3
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            CategoryCard(
                                modifier = Modifier.weight(1f),
                                emoji = "🎧",
                                name = "Headsets",
                                cardBackground = cardBackground,
                                neonGreen = neonGreen
                            )
                            CategoryCard(
                                modifier = Modifier.weight(1f),
                                emoji = "🎮",
                                name = "Accesorios",
                                cardBackground = cardBackground,
                                neonGreen = neonGreen
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
    emoji: String,
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
            // Imagen del producto
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
                    Text(
                        text = emoji,
                        fontSize = 40.sp
                    )
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
                lineHeight = 14.sp,
                modifier = Modifier.height(32.dp)
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
    emoji: String,
    name: String,
    cardBackground: Color,
    neonGreen: Color
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
                Text(
                    text = emoji,
                    fontSize = 48.sp
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

