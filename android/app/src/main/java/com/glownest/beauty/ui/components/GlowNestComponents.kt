package com.glownest.beauty.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glownest.beauty.data.GlowNestCatalog
import com.glownest.beauty.model.GlowNestDestination
import com.glownest.beauty.model.Product
import com.glownest.beauty.ui.theme.GlowNestColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlowNestTopBar(
    currentDestination: GlowNestDestination,
    selectedProductName: String,
    onNavigateHome: () -> Unit,
    onBackFromDetails: () -> Unit,
    onOpenSearch: () -> Unit
) {
    val title = when (currentDestination) {
        GlowNestDestination.Home -> "GlowNest"
        GlowNestDestination.Shop -> "Shop Collection"
        GlowNestDestination.Details -> selectedProductName
        GlowNestDestination.Contact -> "Contact & Orders"
    }

    Column {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = GlowNestColors.CreamCanvas,
                titleContentColor = GlowNestColors.TextPrimary
            ),
            navigationIcon = {
                if (currentDestination == GlowNestDestination.Details) {
                    IconButton(onClick = onBackFromDetails) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to Shop",
                            tint = GlowNestColors.TextPrimary
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(GlowNestColors.PrimaryBlushRose)
                            .clickable(onClick = onNavigateHome),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "G",
                            color = Color.White,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp
                        )
                    }
                }
            },
            title = {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = if (currentDestination == GlowNestDestination.Home) {
                        FontFamily.Serif
                    } else {
                        FontFamily.SansSerif
                    },
                    fontWeight = FontWeight.SemiBold,
                    fontSize = if (currentDestination == GlowNestDestination.Home) 22.sp else 16.sp
                )
            },
            actions = {
                if (currentDestination != GlowNestDestination.Shop) {
                    IconButton(onClick = onOpenSearch) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search products",
                            tint = GlowNestColors.TextPrimary
                        )
                    }
                }
            }
        )
        HorizontalDivider(thickness = 1.dp, color = GlowNestColors.BorderRoseSand)
    }
}

@Composable
fun GlowNestBottomBar(
    currentDestination: GlowNestDestination,
    onNavigate: (GlowNestDestination) -> Unit
) {
    Column {
        HorizontalDivider(thickness = 1.dp, color = GlowNestColors.BorderRoseSand)
        NavigationBar(
            containerColor = GlowNestColors.CardWhite,
            tonalElevation = 0.dp
        ) {
            GlowNestDestination.entries.forEach { destination ->
                val selected = currentDestination == destination
                val icon = when (destination) {
                    GlowNestDestination.Home -> if (selected) Icons.Filled.Home else Icons.Outlined.Home
                    GlowNestDestination.Shop -> if (selected) Icons.Filled.ShoppingBag else Icons.Outlined.ShoppingBag
                    GlowNestDestination.Details -> Icons.Outlined.Spa
                    GlowNestDestination.Contact -> Icons.Outlined.ChatBubbleOutline
                }
                NavigationBarItem(
                    selected = selected,
                    onClick = { onNavigate(destination) },
                    icon = {
                        Icon(
                            imageVector = icon,
                            contentDescription = destination.label,
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = {
                        Text(
                            text = destination.label,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = GlowNestColors.PrimaryBlushRose,
                        selectedTextColor = GlowNestColors.PrimaryBlushRose,
                        unselectedIconColor = GlowNestColors.TextMuted,
                        unselectedTextColor = GlowNestColors.TextMuted,
                        indicatorColor = GlowNestColors.BlushSurface
                    )
                )
            }
        }
    }
}

@Composable
fun GlowNestSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    placeholder: String,
    onSubmit: (() -> Unit)? = null,
    showSubmitButton: Boolean = false,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = GlowNestColors.CardWhite,
        border = BorderStroke(1.dp, GlowNestColors.BorderRoseSand)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 52.dp)
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = GlowNestColors.TextMuted,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                if (query.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodyMedium,
                        color = GlowNestColors.PlaceholderText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    singleLine = true,
                    textStyle = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 14.sp,
                        color = GlowNestColors.TextPrimary
                    ),
                    cursorBrush = SolidColor(GlowNestColors.PrimaryBlushRose),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = { onSubmit?.invoke() }
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (query.isNotEmpty()) {
                IconButton(
                    onClick = { onQueryChange("") },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear search",
                        tint = GlowNestColors.TextMuted,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            if (showSubmitButton && onSubmit != null) {
                Spacer(modifier = Modifier.width(6.dp))
                Button(
                    onClick = onSubmit,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GlowNestColors.DarkButton,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 10.dp),
                    modifier = Modifier.height(40.dp)
                ) {
                    Text(
                        text = "Search",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onSelectProduct: (Product) -> Unit,
    compactActionStyle: Boolean = false,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSelectProduct(product) },
        shape = RoundedCornerShape(16.dp),
        color = GlowNestColors.CardWhite,
        border = BorderStroke(1.dp, GlowNestColors.BorderRoseSand)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f)
                    .background(GlowNestColors.BlushSurface)
            ) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = product.category.label,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = GlowNestColors.PrimaryBlushRose
                    )
                    Text(
                        text = "·",
                        fontSize = 12.sp,
                        color = GlowNestColors.TextMuted
                    )
                    Text(
                        text = product.size,
                        fontSize = 12.sp,
                        color = GlowNestColors.TextMuted
                    )
                    if (product.tag != null) {
                        Text(
                            text = "·",
                            fontSize = 12.sp,
                            color = GlowNestColors.TextMuted
                        )
                        Text(
                            text = product.tag,
                            fontSize = 12.sp,
                            color = GlowNestColors.TextMuted
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = GlowNestColors.TextPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = product.shortDescription,
                    style = MaterialTheme.typography.bodySmall,
                    color = GlowNestColors.TextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider(thickness = 1.dp, color = GlowNestColors.SubtleDivider)
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = GlowNestCatalog.formatNaira(product.priceNgn),
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp,
                            fontFeatureSettings = "tnum",
                            color = GlowNestColors.TextPrimary
                        )
                    )

                    Button(
                        onClick = { onSelectProduct(product) },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (compactActionStyle) {
                                GlowNestColors.BlushSurface
                            } else {
                                GlowNestColors.PrimaryBlushRose
                            },
                            contentColor = if (compactActionStyle) {
                                GlowNestColors.PrimaryBlushRose
                            } else {
                                Color.White
                            }
                        ),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                        modifier = Modifier.heightIn(min = 42.dp)
                    ) {
                        Text(
                            text = "View Details",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}
