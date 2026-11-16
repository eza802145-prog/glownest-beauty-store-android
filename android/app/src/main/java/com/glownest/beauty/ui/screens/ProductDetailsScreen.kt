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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glownest.beauty.data.GlowNestCatalog
import com.glownest.beauty.model.Product
import com.glownest.beauty.ui.theme.GlowNestColors
import kotlinx.coroutines.delay

@Composable
fun ProductDetailsScreen(
    product: Product,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    onBackToShop: () -> Unit,
    onSelectProduct: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    var copied by remember { mutableStateOf(false) }

    LaunchedEffect(copied) {
        if (copied) {
            delay(2200)
            copied = false
        }
    }

    val safeQty = quantity.coerceIn(1, 20)
    val totalPrice = product.priceNgn * safeQty
    val whatsappMessage = GlowNestCatalog.buildWhatsAppOrderMessage(product, safeQty)
    val whatsappUri = GlowNestCatalog.buildWhatsAppProductOrderUri(product, safeQty)

    val relatedProducts = GlowNestCatalog.products
        .filter { it.id != product.id && it.category == product.category }
        .take(3)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(GlowNestColors.CreamCanvas),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Back link
        item {
            TextButton(
                onClick = onBackToShop,
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = GlowNestColors.TextSecondary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Back to Shop",
                    color = GlowNestColors.TextSecondary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // Large Product Photograph
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f)
                    .clip(RoundedCornerShape(18.dp))
                    .background(GlowNestColors.BlushSurface)
            ) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // Product Metadata, Title, Price, Full Description & Key Benefits
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
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
                    Text(text = "·", fontSize = 12.sp, color = GlowNestColors.TextMuted)
                    Text(text = product.size, fontSize = 12.sp, color = GlowNestColors.TextMuted)
                    if (product.tag != null) {
                        Text(text = "·", fontSize = 12.sp, color = GlowNestColors.TextMuted)
                        Text(text = product.tag, fontSize = 12.sp, color = GlowNestColors.TextMuted)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineLarge,
                    color = GlowNestColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = GlowNestCatalog.formatNaira(product.priceNgn),
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        fontFeatureSettings = "tnum",
                        color = GlowNestColors.PrimaryBlushRose
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = product.fullDescription,
                    style = MaterialTheme.typography.bodyLarge,
                    color = GlowNestColors.TextSecondary
                )

                Spacer(modifier = Modifier.height(18.dp))
                HorizontalDivider(thickness = 1.dp, color = GlowNestColors.BorderRoseSand)
                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Key Benefits & Notes",
                    style = MaterialTheme.typography.titleMedium,
                    color = GlowNestColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(10.dp))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    product.highlights.forEach { highlight ->
                        Row(verticalAlignment = Alignment.Top) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = GlowNestColors.PrimaryBlushRose,
                                modifier = Modifier
                                    .padding(top = 2.dp)
                                    .size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = highlight,
                                style = MaterialTheme.typography.bodyMedium,
                                color = GlowNestColors.TextSecondary
                            )
                        }
                    }
                }
            }
        }

        // Quantity Selector & Order on WhatsApp Purchase Card
        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                color = GlowNestColors.CardWhite,
                border = BorderStroke(1.dp, GlowNestColors.BorderRoseSand)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Quantity",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = GlowNestColors.TextPrimary
                            )
                            Text(
                                text = "Select units to order",
                                fontSize = 12.sp,
                                color = GlowNestColors.TextMuted
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = GlowNestColors.CreamCanvas,
                            border = BorderStroke(1.dp, GlowNestColors.BorderRoseSand)
                        ) {
                            Row(
                                modifier = Modifier.padding(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(
                                    onClick = { onQuantityChange((safeQty - 1).coerceAtLeast(1)) },
                                    enabled = safeQty > 1,
                                    modifier = Modifier.size(44.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Remove,
                                        contentDescription = "Decrease quantity",
                                        tint = if (safeQty > 1) {
                                            GlowNestColors.TextPrimary
                                        } else {
                                            GlowNestColors.PlaceholderText
                                        }
                                    )
                                }

                                Text(
                                    text = safeQty.toString(),
                                    style = TextStyle(
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp,
                                        fontFeatureSettings = "tnum",
                                        color = GlowNestColors.TextPrimary
                                    ),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.width(42.dp)
                                )

                                IconButton(
                                    onClick = { onQuantityChange((safeQty + 1).coerceAtMost(20)) },
                                    enabled = safeQty < 20,
                                    modifier = Modifier.size(44.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Increase quantity",
                                        tint = if (safeQty < 20) {
                                            GlowNestColors.TextPrimary
                                        } else {
                                            GlowNestColors.PlaceholderText
                                        }
                                    )
                                }
                            }
                        }
                    }

                    HorizontalDivider(thickness = 1.dp, color = GlowNestColors.SubtleDivider)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Order Total",
                            style = MaterialTheme.typography.bodyMedium,
                            color = GlowNestColors.TextSecondary
                        )
                        Text(
                            text = GlowNestCatalog.formatNaira(totalPrice),
                            style = TextStyle(
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 21.sp,
                                fontFeatureSettings = "tnum",
                                color = GlowNestColors.TextPrimary
                            )
                        )
                    }

                    Button(
                        onClick = { GlowNestCatalog.openExternalUri(context, whatsappUri) },
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GlowNestColors.PrimaryBlushRose,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 50.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ChatBubbleOutline,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Order on WhatsApp",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    OutlinedButton(
                        onClick = {
                            clipboardManager.setText(AnnotatedString(whatsappMessage))
                            copied = true
                        },
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, GlowNestColors.BorderRoseSand),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = GlowNestColors.CreamCanvas,
                            contentColor = GlowNestColors.TextSecondary
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 44.dp)
                    ) {
                        Icon(
                            imageVector = if (copied) Icons.Default.Check else Icons.Default.ContentCopy,
                            contentDescription = null,
                            tint = if (copied) GlowNestColors.PrimaryBlushRose else GlowNestColors.TextMuted,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (copied) "Order message copied" else "Copy WhatsApp order message",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // Pre-filled WhatsApp Message Preview
        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = GlowNestColors.BlushSurface.copy(alpha = 0.7f),
                border = BorderStroke(1.dp, GlowNestColors.BorderRoseSand)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Pre-filled WhatsApp Order Summary",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = GlowNestColors.TextSecondary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = whatsappMessage,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        color = GlowNestColors.TextSecondary
                    )
                }
            }
        }

        // Related Products
        if (relatedProducts.isNotEmpty()) {
            item {
                Column {
                    HorizontalDivider(thickness = 1.dp, color = GlowNestColors.BorderRoseSand)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "More in ${product.category.label}",
                        style = MaterialTheme.typography.headlineMedium,
                        color = GlowNestColors.TextPrimary
                    )
                }
            }

            items(relatedProducts, key = { it.id }) { related ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectProduct(related) },
                    shape = RoundedCornerShape(16.dp),
                    color = GlowNestColors.CardWhite,
                    border = BorderStroke(1.dp, GlowNestColors.BorderRoseSand)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = related.imageRes),
                            contentDescription = related.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(76.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(GlowNestColors.BlushSurface)
                        )
                        Spacer(modifier = Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = related.size,
                                fontSize = 11.sp,
                                color = GlowNestColors.TextMuted
                            )
                            Text(
                                text = related.name,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = GlowNestColors.TextPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = GlowNestCatalog.formatNaira(related.priceNgn),
                                style = TextStyle(
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                    fontFeatureSettings = "tnum",
                                    color = GlowNestColors.PrimaryBlushRose
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
