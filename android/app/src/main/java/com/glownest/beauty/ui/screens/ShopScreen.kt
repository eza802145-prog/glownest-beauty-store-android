package com.glownest.beauty.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glownest.beauty.data.GlowNestCatalog
import com.glownest.beauty.model.CategoryFilter
import com.glownest.beauty.model.Product
import com.glownest.beauty.ui.components.GlowNestSearchBar
import com.glownest.beauty.ui.components.ProductCard
import com.glownest.beauty.ui.theme.GlowNestColors

@Composable
fun ShopScreen(
    selectedCategory: CategoryFilter,
    searchQuery: String,
    onCategoryChange: (CategoryFilter) -> Unit,
    onSearchChange: (String) -> Unit,
    onSelectProduct: (Product) -> Unit,
    onResetFilters: () -> Unit,
    modifier: Modifier = Modifier
) {
    val normalizedQuery = searchQuery.trim().lowercase()

    val filteredProducts = GlowNestCatalog.products.filter { product ->
        val matchesCategory =
            selectedCategory == CategoryFilter.All || product.category == selectedCategory.category
        if (!matchesCategory) return@filter false

        if (normalizedQuery.isEmpty()) return@filter true

        val haystack = buildString {
            append(product.name).append(' ')
            append(product.category.label).append(' ')
            append(product.shortDescription).append(' ')
            append(product.fullDescription).append(' ')
            append(product.highlights.joinToString(" "))
        }.lowercase()

        haystack.contains(normalizedQuery)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(GlowNestColors.CreamCanvas),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header + Search + Category Filters
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "GlowNest Catalog",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = GlowNestColors.PrimaryBlushRose
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Shop Beauty Essentials",
                        style = MaterialTheme.typography.headlineLarge,
                        color = GlowNestColors.TextPrimary,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${filteredProducts.size} ${if (filteredProducts.size == 1) "product" else "products"}",
                        fontSize = 13.sp,
                        color = GlowNestColors.TextMuted
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider(thickness = 1.dp, color = GlowNestColors.BorderRoseSand)
                Spacer(modifier = Modifier.height(16.dp))

                GlowNestSearchBar(
                    query = searchQuery,
                    onQueryChange = onSearchChange,
                    placeholder = "Search by name or ingredient..."
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CategoryFilter.entries.forEach { filter ->
                        val isActive = selectedCategory == filter
                        Surface(
                            onClick = { onCategoryChange(filter) },
                            shape = RoundedCornerShape(12.dp),
                            color = if (isActive) {
                                GlowNestColors.PrimaryBlushRose
                            } else {
                                GlowNestColors.CardWhite
                            },
                            border = if (isActive) {
                                null
                            } else {
                                BorderStroke(1.dp, GlowNestColors.BorderRoseSand)
                            },
                            modifier = Modifier.heightIn(min = 44.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = filter.label,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = if (isActive) Color.White else GlowNestColors.TextSecondary,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        }

        // Product List or Empty State
        if (filteredProducts.isEmpty()) {
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = GlowNestColors.CardWhite,
                    border = BorderStroke(1.dp, GlowNestColors.BorderRoseSand)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No matching products found",
                            style = MaterialTheme.typography.headlineMedium,
                            color = GlowNestColors.TextPrimary,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Try adjusting your search keywords or switching back to all beauty categories.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = GlowNestColors.TextMuted,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(18.dp))
                        Button(
                            onClick = onResetFilters,
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GlowNestColors.PrimaryBlushRose,
                                contentColor = Color.White
                            ),
                            modifier = Modifier.heightIn(min = 44.dp)
                        ) {
                            Text(
                                text = "Reset Filters",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        } else {
            items(filteredProducts, key = { it.id }) { product ->
                ProductCard(
                    product = product,
                    onSelectProduct = onSelectProduct,
                    compactActionStyle = false
                )
            }
        }
    }
}
