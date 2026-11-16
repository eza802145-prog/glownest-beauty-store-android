package com.glownest.beauty.data

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.glownest.beauty.R
import com.glownest.beauty.model.CategorySummary
import com.glownest.beauty.model.OpeningHour
import com.glownest.beauty.model.OrderStep
import com.glownest.beauty.model.Product
import com.glownest.beauty.model.ProductCategory
import com.glownest.beauty.model.StoreInfo
import java.text.NumberFormat
import java.util.Locale

object GlowNestCatalog {

    val heroImageRes: Int = R.drawable.hero_glownest_collection

    val storeInfo = StoreInfo(
        name = "GlowNest Beauty Store",
        tagline = "Your Beauty, Your Confidence.",
        whatsappDigits = "2348060677222",
        phoneDisplay = "+234 806 067 7222",
        email = "ezea80214@gmail.com",
        instagramHandle = "@glownest.ng",
        address = "125 Itire Road, Lawson, Surulere, Lagos, Nigeria.",
        cityNote = "Same-day Lagos dispatch & 24–72h nationwide delivery",
        hours = listOf(
            OpeningHour(days = "Monday – Saturday", time = "9:00 AM – 7:00 PM WAT"),
            OpeningHour(days = "Sunday", time = "12:00 PM – 5:00 PM WAT")
        ),
        orderInstructions = listOf(
            OrderStep(
                step = "01",
                title = "Browse & Choose Your Essentials",
                detail = "Explore our Skincare, Makeup, Haircare, and Fragrance collections in Nigerian Naira (₦)."
            ),
            OrderStep(
                step = "02",
                title = "Select Quantity on Product Details",
                detail = "Open any product to read full formulation notes, adjust your desired quantity, and see your live total."
            ),
            OrderStep(
                step = "03",
                title = "Send Order via WhatsApp",
                detail = "Tap \"Order on WhatsApp\" to launch a pre-filled chat with your product name, quantity, and total price."
            ),
            OrderStep(
                step = "04",
                title = "Confirm Delivery & Dispatch",
                detail = "Our beauty concierge confirms stock, shares verified bank transfer details, and dispatches your parcel."
            )
        )
    )

    val categories: List<CategorySummary> = listOf(
        CategorySummary(
            category = ProductCategory.Skincare,
            subtitle = "Serums, barrier creams & botanical mists",
            itemCountLabel = "3 Essentials"
        ),
        CategorySummary(
            category = ProductCategory.Makeup,
            subtitle = "Silk-matte lips, skin tints & cheek duos",
            itemCountLabel = "3 Essentials"
        ),
        CategorySummary(
            category = ProductCategory.Haircare,
            subtitle = "Argan elixirs & whipped hibiscus masks",
            itemCountLabel = "2 Essentials"
        ),
        CategorySummary(
            category = ProductCategory.Fragrance,
            subtitle = "Crystal eau de parfum & warm body mists",
            itemCountLabel = "2 Essentials"
        )
    )

    val products: List<Product> = listOf(
        Product(
            id = "rosehip-vitamin-c-serum",
            name = "Luminous Rosehip & Vitamin C Serum",
            category = ProductCategory.Skincare,
            priceNgn = 18500,
            size = "30 ml",
            shortDescription = "Cold-pressed rosehip oil and stabilized Vitamin C for visible daily radiance.",
            fullDescription = "A featherlight facial serum formulated to brighten uneven tone, soften fine dry lines, and restore natural bounce without greasiness in humid Nigerian climates.",
            highlights = listOf(
                "10% stabilized Vitamin C + organic rosehip seed oil",
                "Fast-absorbing finish suitable for melanin-rich skin",
                "Fragrance-free & gentle for morning and evening routines"
            ),
            imageRes = R.drawable.product_rosehip_serum,
            featured = true,
            tag = "Bestseller"
        ),
        Product(
            id = "velvet-ceramide-cream",
            name = "Velvet Ceramide Barrier Cream",
            category = ProductCategory.Skincare,
            priceNgn = 16000,
            size = "50 ml",
            shortDescription = "Whipped triple-ceramide moisturizer that seals in 24-hour hydration.",
            fullDescription = "Rich yet breathable cushion cream enriched with plant squalane, oat lipids, and skin-identical ceramides to calm sensitivity and strengthen your moisture barrier.",
            highlights = listOf(
                "Triple-ceramide complex for lasting barrier repair",
                "Velvety satin texture sits cleanly under sunscreen and makeup",
                "Non-comedogenic & dermatologist tested"
            ),
            imageRes = R.drawable.product_ceramide_cream,
            featured = false
        ),
        Product(
            id = "hibiscus-hydrating-mist",
            name = "Hibiscus & Rosewater Hydrating Mist",
            category = ProductCategory.Skincare,
            priceNgn = 12500,
            size = "100 ml",
            shortDescription = "Ultra-fine botanical facial tonic to refresh, calm, and de-puff skin anytime.",
            fullDescription = "Distilled Damask rosewater infused with hibiscus petal antioxidants and panthenol. Use after cleansing, before serums, or midday for an instant cooling reset.",
            highlights = listOf(
                "Micro-droplet rose-gold atomizer for even coverage",
                "Alcohol-free calming formula with panthenol (Vitamin B5)",
                "Doubles as a hydrating makeup setting veil"
            ),
            imageRes = R.drawable.product_hibiscus_mist,
            featured = false
        ),
        Product(
            id = "silk-matte-velvet-lipstick",
            name = "Silk-Matte Velvet Lipstick — Blush Rose",
            category = ProductCategory.Makeup,
            priceNgn = 9500,
            size = "3.8 g",
            shortDescription = "One-swipe high-pigment dusty rose lipstick with cushiony jojoba comfort.",
            fullDescription = "Sculpted in a brushed rose-gold case, this modern silk-matte formula delivers rich, flattering dusty-rose color without tugging or drying out the lips.",
            highlights = listOf(
                "Infused with jojoba oil and shea butter for 8-hour comfort",
                "Soft-focus matte finish that never cracks or flakes",
                "Universal dusty blush-rose tone curated for warm undertones"
            ),
            imageRes = R.drawable.product_velvet_lipstick,
            featured = true,
            tag = "Customer Favorite"
        ),
        Product(
            id = "dewy-honey-skin-tint",
            name = "Lumière Dewy Skin Tint — Warm Honey",
            category = ProductCategory.Makeup,
            priceNgn = 21000,
            size = "30 ml",
            shortDescription = "Breathable serum-foundation hybrid with buildable medium coverage and glow.",
            fullDescription = "Blends seamlessly with fingertips or brush to even out skin tone while letting natural skin texture shine through. Sweat-resistant for long Lagos workdays.",
            highlights = listOf(
                "Hyaluronic acid + niacinamide serum base",
                "Zero gray cast or oxidation over 10+ hours",
                "Precision rose-gold pump dispenser"
            ),
            imageRes = R.drawable.product_skin_tint,
            featured = false
        ),
        Product(
            id = "petal-blush-highlighter-duo",
            name = "Rose-Gold Cheek & Glow Compact Duo",
            category = ProductCategory.Makeup,
            priceNgn = 14500,
            size = "12 g",
            shortDescription = "Silky petal blush paired with a finely milled champagne rose-gold highlighter.",
            fullDescription = "A travel-ready mirrored compact featuring a velvety matte blush and a lit-from-within luminizer that melts onto cheekbones with zero chalky fallout.",
            highlights = listOf(
                "Finely milled talc-free mineral pigments",
                "Buildable flush paired with champagne-rose shimmer",
                "Slim pocket mirror compact ideal for touch-ups"
            ),
            imageRes = R.drawable.product_blush_highlighter_duo,
            featured = true
        ),
        Product(
            id = "argan-silk-hair-elixir",
            name = "Botanical Argan & Shea Hair Elixir",
            category = ProductCategory.Haircare,
            priceNgn = 15500,
            size = "100 ml",
            shortDescription = "Nourishing cold-pressed oil blend to tame frizz and seal luminous shine.",
            fullDescription = "Crafted with Moroccan argan oil, sweet almond, and light unrefined shea esters to protect curls, coils, braids, and silk presses from heat and dryness.",
            highlights = listOf(
                "Seals split ends and boosts natural curl definition",
                "Lightweight glass dropper prevents heavy buildup",
                "Safe for natural hair, locs, and protective styles"
            ),
            imageRes = R.drawable.product_argan_hair_elixir,
            featured = false
        ),
        Product(
            id = "hibiscus-whipped-hair-mask",
            name = "Whipped Hibiscus & Shea Moisture Mask",
            category = ProductCategory.Haircare,
            priceNgn = 13800,
            size = "200 ml",
            shortDescription = "Deep-conditioning botanical treatment that restores softness in 10 minutes.",
            fullDescription = "An intensely hydrating whipped hair mask powered by Nigerian hibiscus extract, raw shea butter, and hydrolyzed oat protein to detangle and strengthen brittle strands.",
            highlights = listOf(
                "Rich slip for effortless wash-day detangling",
                "Sulfate-free, silicone-free, paraben-free",
                "Leaves hair soft with a subtle warm floral scent"
            ),
            imageRes = R.drawable.product_hibiscus_hair_mask,
            featured = false
        ),
        Product(
            id = "blush-peony-oud-parfum",
            name = "Éclat Rose & Peony Eau de Parfum",
            category = ProductCategory.Fragrance,
            priceNgn = 34000,
            size = "50 ml",
            shortDescription = "Radiant damask rose, pink peony, and warm white amber in a faceted flacon.",
            fullDescription = "Our signature long-wear Eau de Parfum opens with crisp bergamot and pear blossom, blooming into velvety peony before settling into warm sandalwood and soft musk.",
            highlights = listOf(
                "20% perfume oil concentration for 8–10 hour sillage",
                "Notes: Bergamot · Pink Peony · Cashmere Musk",
                "Heavy faceted crystal bottle with brushed rose-gold cap"
            ),
            imageRes = R.drawable.product_peony_oud_perfume,
            featured = true,
            tag = "Signature Scent"
        ),
        Product(
            id = "champagne-vanilla-body-mist",
            name = "Champagne Rose & Vanilla Body Mist",
            category = ProductCategory.Fragrance,
            priceNgn = 17500,
            size = "100 ml",
            shortDescription = "Airy everyday veil of sugared rose petals, Madagascar vanilla, and warm musk.",
            fullDescription = "Designed for effortless daily layering after your shower or over body butter. Leaves skin delicately scented with a soft, inviting trail from morning to evening.",
            highlights = listOf(
                "Aloe-infused base hydrates while scenting skin",
                "Notes: Sparkling Rose · Vanilla Bean · Soft Amber",
                "Ideal layering partner for Éclat Rose Eau de Parfum"
            ),
            imageRes = R.drawable.product_vanilla_body_mist,
            featured = false
        )
    )

    fun findProductById(productId: String?): Product {
        return products.firstOrNull { it.id == productId } ?: products.first()
    }

    fun formatNaira(amount: Int): String {
        val formatter = NumberFormat.getIntegerInstance(Locale("en", "NG"))
        return "₦${formatter.format(amount)}"
    }

    fun buildWhatsAppOrderMessage(product: Product, quantity: Int): String {
        val safeQty = quantity.coerceIn(1, 20)
        val total = product.priceNgn * safeQty
        return buildString {
            appendLine("Hello GlowNest Beauty Store, I would like to place an order:")
            appendLine()
            appendLine("Product: ${product.name}")
            appendLine("Category: ${product.category.label} (${product.size})")
            appendLine("Quantity: $safeQty")
            appendLine("Unit Price: ${formatNaira(product.priceNgn)}")
            appendLine("Total Price: ${formatNaira(total)}")
            appendLine()
            append("Please let me know availability and delivery details. Thank you!")
        }
    }

    fun buildWhatsAppProductOrderUri(product: Product, quantity: Int): Uri {
        val message = buildWhatsAppOrderMessage(product, quantity)
        return Uri.parse("https://wa.me/${storeInfo.whatsappDigits}?text=${Uri.encode(message)}")
    }

    fun buildWhatsAppContactUri(customNote: String? = null): Uri {
        val text = customNote
            ?: "Hello GlowNest Beauty Store, I would like to inquire about your beauty products and delivery options."
        return Uri.parse("https://wa.me/${storeInfo.whatsappDigits}?text=${Uri.encode(text)}")
    }

    fun openExternalUri(context: Context, uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        try {
            context.startActivity(intent)
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(
                context,
                "No app found to open this link on your device.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun dialStorePhone(context: Context) {
        val cleanPhone = storeInfo.phoneDisplay.replace(" ", "")
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$cleanPhone")).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        try {
            context.startActivity(intent)
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(context, "Phone dialer unavailable.", Toast.LENGTH_SHORT).show()
        }
    }

    fun emailStore(context: Context) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${storeInfo.email}")).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        try {
            context.startActivity(intent)
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(context, "No email app installed.", Toast.LENGTH_SHORT).show()
        }
    }
}
