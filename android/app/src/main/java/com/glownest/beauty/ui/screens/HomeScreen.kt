package com.glownest.beauty.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glownest.beauty.data.GlowNestCatalog
import com.glownest.beauty.model.CategorySummary
import com.glownest.beauty.model.Product
import com.glownest.beauty.model.ProductCategory
import com.glownest.beauty.ui.components.GlowNestSearchBar
import com.glownest.beauty.ui.components.ProductCard
import com.glownest.beauty.ui.theme.GlowNestColors

@Composable
fun HomeScreen(
    onShopNow: () -> Unit,
    onSearchSubmit: (String) -> Unit,
    onSelectCategory: (ProductCategory) -> Unit,
    onSelectProduct: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchValue by rememberSaveable { mutableStateOf("") }
    val featuredProducts = GlowNestCatalog.products.filter { it.featured }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(GlowNestColors.CreamCanvas),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Hero Welcome & Search Section
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "GlowNest Beauty Store · Lagos & Nationwide",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = GlowNestColors.PrimaryBlushRose
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Your Beauty, Your Confidence.",
                    style = MaterialTheme.typography.displayLarge,
                    color = GlowNestColors.TextPrimary
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Curated skincare, silk-matte makeup, botanical haircare, and luminous fragrances priced transparently in Nigerian Naira with instant WhatsApp ordering.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = GlowNestColors.TextSecondary
                )
                Spacer(modifier = Modifier.height(18.dp))

                GlowNestSearchBar(
                    query = searchValue,
                    onQueryChange = { searchValue = it },
                    placeholder = "Search serums, lipstick, perfume...",
                    onSubmit = { onSearchSubmit(searchValue.trim()) },
                    showSubmitButton = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onShopNow,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GlowNestColors.PrimaryBlushRose,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp),
                    modifier = Modifier.heightIn(min = 48.dp)
                ) {
                    Text(
                        text = "Shop Now",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 10f)
                        .clip(RoundedCornerShape(18.dp))
                        .background(GlowNestColors.BlushSurface)
                ) {
                    Image(
                        painter = painterResource(id = GlowNestCatalog.heroImageRes),
                        contentDescription = "GlowNest Beauty Store curated skincare, lipstick, and fragrance collection",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

        // Beauty Categories Section
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                HorizontalDivider(thickness = 1.dp, color = GlowNestColors.BorderRoseSand)
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Beauty Categories",
                    style = MaterialTheme.typography.headlineMedium,
                    color = GlowNestColors.TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Explore essentials tailored for radiant skin, effortless color, healthy hair, and signature scent.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = GlowNestColors.TextMuted
                )
                Spacer(modifier = Modifier.height(14.dp))

                val categoryRows = GlowNestCatalog.categories.chunked(2)
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    categoryRows.forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            rowItems.forEach { summary ->
                                CategoryTile(
                                    summary = summary,
                                    onClick = { onSelectCategory(summary.category) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Featured Products Header
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                HorizontalDivider(thickness = 1.dp, color = GlowNestColors.BorderRoseSand)
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Featured Essentials",
                            style = MaterialTheme.typography.headlineMedium,
                            color = GlowNestColors.TextPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Our most-requested formulas across skincare, makeup, and fragrance.",
                            style = MaterialTheme.typography.bodySmall,
                            color = GlowNestColors.TextMuted
                        )
                    }
                    TextButton(onClick = onShopNow) {
                        Text(
                            text = "View All",
                            color = GlowNestColors.PrimaryBlushRose,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }

        // Featured Product Cards
        items(featuredProducts, key = { it.id }) { product ->
            ProductCard(
                product = product,
                onSelectProduct = onSelectProduct,
                compactActionStyle = true
            )
        }
    }
}

@Composable
private fun CategoryTile(
    summary: CategorySummary,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .heightIn(min = 120.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        color = GlowNestColors.CardWhite,
        border = BorderStroke(1.dp, GlowNestColors.BorderRoseSand)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = summary.itemCountLabel,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = GlowNestColors.PrimaryBlushRose
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = summary.category.label,
                    style = MaterialTheme.typography.titleLarge,
                    color = GlowNestColors.TextPrimary
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = summary.subtitle,
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    color = GlowNestColors.TextMuted,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = GlowNestColors.PrimaryBlushRose,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
