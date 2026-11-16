package com.glownest.beauty.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glownest.beauty.data.GlowNestCatalog
import com.glownest.beauty.ui.theme.GlowNestColors

@Composable
fun ContactOrdersScreen(
    onBrowseShop: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val storeInfo = GlowNestCatalog.storeInfo

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(GlowNestColors.CreamCanvas),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Header + Chat on WhatsApp CTA
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Concierge & Storefront",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = GlowNestColors.PrimaryBlushRose
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Contact & Orders",
                    style = MaterialTheme.typography.headlineLarge,
                    color = GlowNestColors.TextPrimary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Reach our beauty concierge directly on WhatsApp for skincare recommendations, shade matching, and fast Lagos or nationwide dispatch.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = GlowNestColors.TextSecondary
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        GlowNestCatalog.openExternalUri(
                            context,
                            GlowNestCatalog.buildWhatsAppContactUri()
                        )
                    },
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
                        text = "Chat on WhatsApp",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        // Store Contact Information Card
        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = GlowNestColors.CardWhite,
                border = BorderStroke(1.dp, GlowNestColors.BorderRoseSand)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = "Store Information",
                        style = MaterialTheme.typography.headlineMedium,
                        color = GlowNestColors.TextPrimary
                    )

                    ContactRow(
                        icon = Icons.Outlined.LocationOn,
                        label = "Boutique Address",
                        value = storeInfo.address,
                        subtext = storeInfo.cityNote
                    )

                    HorizontalDivider(thickness = 1.dp, color = GlowNestColors.SubtleDivider)

                    ContactRow(
                        icon = Icons.Outlined.Phone,
                        label = "Phone & WhatsApp Line",
                        value = storeInfo.phoneDisplay,
                        onClick = { GlowNestCatalog.dialStorePhone(context) }
                    )

                    HorizontalDivider(thickness = 1.dp, color = GlowNestColors.SubtleDivider)

                    ContactRow(
                        icon = Icons.Outlined.Email,
                        label = "Email & Social",
                        value = storeInfo.email,
                        subtext = "Instagram: ${storeInfo.instagramHandle}",
                        onClick = { GlowNestCatalog.emailStore(context) }
                    )
                }
            }
        }

        // Opening Hours Card
        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = GlowNestColors.CardWhite,
                border = BorderStroke(1.dp, GlowNestColors.BorderRoseSand)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(GlowNestColors.BlushSurface),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Schedule,
                                contentDescription = null,
                                tint = GlowNestColors.PrimaryBlushRose,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Opening Hours",
                            style = MaterialTheme.typography.headlineMedium,
                            color = GlowNestColors.TextPrimary
                        )
                    }

                    storeInfo.hours.forEachIndexed { index, slot ->
                        if (index > 0) {
                            HorizontalDivider(thickness = 1.dp, color = GlowNestColors.SubtleDivider)
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = slot.days,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = GlowNestColors.TextPrimary
                            )
                            Text(
                                text = slot.time,
                                fontSize = 13.sp,
                                color = GlowNestColors.TextSecondary
                            )
                        }
                    }
                }
            }
        }

        // How to Place an Order Section
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                HorizontalDivider(thickness = 1.dp, color = GlowNestColors.BorderRoseSand)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "How to Place an Order",
                            style = MaterialTheme.typography.headlineMedium,
                            color = GlowNestColors.TextPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Simple, direct WhatsApp ordering with zero account sign-up required.",
                            style = MaterialTheme.typography.bodySmall,
                            color = GlowNestColors.TextMuted
                        )
                    }
                }
            }
        }

        items(storeInfo.orderInstructions, key = { it.step }) { step ->
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = GlowNestColors.CardWhite,
                border = BorderStroke(1.dp, GlowNestColors.BorderRoseSand)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "${step.step}.",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp,
                        color = GlowNestColors.PrimaryBlushRose
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = step.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = GlowNestColors.TextPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = step.detail,
                        style = MaterialTheme.typography.bodyMedium,
                        color = GlowNestColors.TextSecondary
                    )
                }
            }
        }

        item {
            OutlinedButton(
                onClick = onBrowseShop,
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, GlowNestColors.BorderRoseSand),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = GlowNestColors.CardWhite,
                    contentColor = GlowNestColors.TextPrimary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp)
            ) {
                Text(
                    text = "Browse Products",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun ContactRow(
    icon: ImageVector,
    label: String,
    value: String,
    subtext: String? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
            ),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(GlowNestColors.BlushSurface),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = GlowNestColors.PrimaryBlushRose,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = GlowNestColors.TextMuted
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = GlowNestColors.TextPrimary
            )
            if (subtext != null) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtext,
                    fontSize = 12.sp,
                    color = GlowNestColors.TextMuted
                )
            }
        }
    }
}
