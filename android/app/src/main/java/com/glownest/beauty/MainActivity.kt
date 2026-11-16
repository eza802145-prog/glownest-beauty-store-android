package com.glownest.beauty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.glownest.beauty.data.GlowNestCatalog
import com.glownest.beauty.model.CategoryFilter
import com.glownest.beauty.model.GlowNestDestination
import com.glownest.beauty.model.Product
import com.glownest.beauty.model.ProductCategory
import com.glownest.beauty.ui.components.GlowNestBottomBar
import com.glownest.beauty.ui.components.GlowNestTopBar
import com.glownest.beauty.ui.screens.ContactOrdersScreen
import com.glownest.beauty.ui.screens.HomeScreen
import com.glownest.beauty.ui.screens.ProductDetailsScreen
import com.glownest.beauty.ui.screens.ShopScreen
import com.glownest.beauty.ui.theme.GlowNestColors
import com.glownest.beauty.ui.theme.GlowNestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GlowNestTheme {
                GlowNestMobileApp()
            }
        }
    }
}

@Composable
fun GlowNestMobileApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: GlowNestDestination.Home.route

    val currentDestination = GlowNestDestination.entries.firstOrNull {
        it.route == currentRoute
    } ?: GlowNestDestination.Home

    var selectedCategory by rememberSaveable { mutableStateOf(CategoryFilter.All) }
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var selectedProductId by rememberSaveable { mutableStateOf(GlowNestCatalog.products.first().id) }
    var quantity by rememberSaveable { mutableIntStateOf(1) }

    val selectedProduct = GlowNestCatalog.findProductById(selectedProductId)

    fun navigateToTab(destination: GlowNestDestination) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun openProductDetails(product: Product) {
        selectedProductId = product.id
        quantity = 1
        navigateToTab(GlowNestDestination.Details)
    }

    fun openShopWithCategory(category: ProductCategory) {
        selectedCategory = CategoryFilter.entries.firstOrNull { it.category == category }
            ?: CategoryFilter.All
        searchQuery = ""
        navigateToTab(GlowNestDestination.Shop)
    }

    fun openShopWithSearch(query: String) {
        selectedCategory = CategoryFilter.All
        searchQuery = query
        navigateToTab(GlowNestDestination.Shop)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = GlowNestColors.CreamCanvas,
        topBar = {
            GlowNestTopBar(
                currentDestination = currentDestination,
                selectedProductName = selectedProduct.name,
                onNavigateHome = { navigateToTab(GlowNestDestination.Home) },
                onBackFromDetails = {
                    if (!navController.popBackStack()) {
                        navigateToTab(GlowNestDestination.Shop)
                    }
                },
                onOpenSearch = { navigateToTab(GlowNestDestination.Shop) }
            )
        },
        bottomBar = {
            GlowNestBottomBar(
                currentDestination = currentDestination,
                onNavigate = { navigateToTab(it) }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = GlowNestDestination.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(GlowNestDestination.Home.route) {
                HomeScreen(
                    onShopNow = { navigateToTab(GlowNestDestination.Shop) },
                    onSearchSubmit = { openShopWithSearch(it) },
                    onSelectCategory = { openShopWithCategory(it) },
                    onSelectProduct = { openProductDetails(it) }
                )
            }

            composable(GlowNestDestination.Shop.route) {
                ShopScreen(
                    selectedCategory = selectedCategory,
                    searchQuery = searchQuery,
                    onCategoryChange = { selectedCategory = it },
                    onSearchChange = { searchQuery = it },
                    onSelectProduct = { openProductDetails(it) },
                    onResetFilters = {
                        selectedCategory = CategoryFilter.All
                        searchQuery = ""
                    }
                )
            }

            composable(GlowNestDestination.Details.route) {
                ProductDetailsScreen(
                    product = selectedProduct,
                    quantity = quantity,
                    onQuantityChange = { quantity = it },
                    onBackToShop = { navigateToTab(GlowNestDestination.Shop) },
                    onSelectProduct = { openProductDetails(it) }
                )
            }

            composable(GlowNestDestination.Contact.route) {
                ContactOrdersScreen(
                    onBrowseShop = { navigateToTab(GlowNestDestination.Shop) }
                )
            }
        }
    }
}
