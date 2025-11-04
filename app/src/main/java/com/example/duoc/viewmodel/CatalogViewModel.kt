package com.example.duoc.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.duoc.R
import com.example.duoc.model.Product

class CatalogViewModel : ViewModel() {
    // Estado de búsqueda y filtro
    var query by mutableStateOf("")
        private set
    var selectedCategory by mutableStateOf("Todo")
        private set

    // Lista base de productos
    private val allProducts: List<Product> = listOf(
        Product("JM001", "Juegos de Mesa", "Catan", "\$29.990 CLP", R.drawable.catan2),
        Product("JM002", "Juegos de Mesa", "Carcassonne", "\$24.990 CLP", R.drawable.carc2),
        Product("AC001", "Accesorios", "Controlador Inalámbrico Xbox Series X", "\$59.990 CLP", R.drawable.ctrl),
        Product("AC002", "Accesorios", "Auriculares Gamer HyperX Cloud II", "\$79.990 CLP", R.drawable.auric2),
        Product("CO001", "Consolas", "PlayStation 5", "\$549.990 CLP", R.drawable.play5),
        Product("CG001", "Computadores Gamers", "PC Gamer ASUS ROG Strix", "\$1.299.990 CLP", R.drawable.pc2),
        Product("SG001", "Sillas Gamers", "Silla Gamer Secretlab Titan", "\$349.990 CLP", R.drawable.silla2),
        Product("MS001", "Mouse", "Mouse Gamer Logitech G502 HERO", "\$49.990 CLP", R.drawable.mouse2),
        Product("MP001", "Mousepad", "Mousepad Razer Goliathus Extended Chroma", "\$29.990 CLP", R.drawable.mousepad2),
        Product("PP001", "Poleras Personalizadas", "Polera Gamer Personalizada 'Level-Up'", "\$14.990 CLP", R.drawable.polera_negra)
    )

    val categories: List<String> = listOf(
        "Todo",
        "Juegos de Mesa",
        "Accesorios",
        "Consolas",
        "Computadores Gamers",
        "Sillas Gamers",
        "Mouse",
        "Mousepad",
        "Poleras Personalizadas"
    )

    // Productos filtrados según query y categoría
    val products: List<Product>
        get() {
            val base = if (selectedCategory == "Todo") allProducts else allProducts.filter { it.category == selectedCategory }
            val q = query.trim().lowercase()
            return if (q.isEmpty()) base else base.filter { p ->
                p.name.lowercase().contains(q) || p.category.lowercase().contains(q) || p.code.lowercase().contains(q)
            }
        }

    fun onQueryChange(value: String) { query = value }
    fun onCategorySelected(cat: String) { selectedCategory = cat }
}
