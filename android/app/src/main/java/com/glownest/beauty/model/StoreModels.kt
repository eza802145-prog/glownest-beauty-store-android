package com.glownest.beauty.model

import androidx.annotation.DrawableRes

enum class ProductCategory(val label: String) {
    Skincare("Skincare"),
    Makeup("Makeup"),
    Haircare("Haircare"),
    Fragrance("Fragrance")
}

enum class CategoryFilter(val label: String, val category: ProductCategory? = null) {
    All("All", null),
    Skincare("Skincare", ProductCategory.Skincare),
    Makeup("Makeup", ProductCategory.Makeup),
    Haircare("Haircare", ProductCategory.Haircare),
    Fragrance("Fragrance", ProductCategory.Fragrance)
}

enum class GlowNestDestination(val route: String, val label: String) {
    Home("home", "Home"),
    Shop("shop", "Shop"),
    Details("details", "Details"),
    Contact("contact", "Contact")
}

data class Product(
    val id: String,
    val name: String,
    val category: ProductCategory,
    val priceNgn: Int,
    val size: String,
    val shortDescription: String,
    val fullDescription: String,
    val highlights: List<String>,
    @DrawableRes val imageRes: Int,
    val featured: Boolean,
    val tag: String? = null
)

data class CategorySummary(
    val category: ProductCategory,
    val subtitle: String,
    val itemCountLabel: String
)

data class OpeningHour(
    val days: String,
    val time: String
)

data class OrderStep(
    val step: String,
    val title: String,
    val detail: String
)

data class StoreInfo(
    val name: String,
    val tagline: String,
    val whatsappDigits: String,
    val phoneDisplay: String,
    val email: String,
    val instagramHandle: String,
    val address: String,
    val cityNote: String,
    val hours: List<OpeningHour>,
    val orderInstructions: List<OrderStep>
)
