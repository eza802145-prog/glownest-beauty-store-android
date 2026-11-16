import { CategorySummary, Product, StoreInfo } from '../types/store';

export const HERO_IMAGE = '/src/assets/images/hero_glownest_collection_1794823660957.jpg';

export const STORE_INFO: StoreInfo = {
  name: 'GlowNest Beauty Store',
  tagline: 'Your Beauty, Your Confidence.',
  whatsappDigits: '2348060677222',
  phoneDisplay: '+234 806 067 7222',
  email: 'ezea80214@gmail.com',
  instagramHandle: '@glownest.ng',
  address: '125 Itire Road, Lawson, Surulere, Lagos, Nigeria.',
  cityNote: 'Same-day Lagos dispatch & 24–72h nationwide delivery',
  hours: [
    { days: 'Monday – Saturday', time: '9:00 AM – 7:00 PM WAT' },
    { days: 'Sunday', time: '12:00 PM – 5:00 PM WAT' },
  ],
  orderInstructions: [
    {
      step: '01',
      title: 'Browse & Choose Your Essentials',
      detail:
        'Explore our Skincare, Makeup, Haircare, and Fragrance collections in Nigerian Naira (₦).',
    },
    {
      step: '02',
      title: 'Select Quantity on Product Details',
      detail:
        'Open any product to read full formulation notes, adjust your desired quantity, and see your live total.',
    },
    {
      step: '03',
      title: 'Send Order via WhatsApp',
      detail:
        'Tap "Order on WhatsApp" to launch a pre-filled chat with your product name, quantity, and total price.',
    },
    {
      step: '04',
      title: 'Confirm Delivery & Dispatch',
      detail:
        'Our beauty concierge confirms stock, shares verified bank transfer details, and dispatches your parcel.',
    },
  ],
};

export const BEAUTY_CATEGORIES: CategorySummary[] = [
  {
    id: 'Skincare',
    label: 'Skincare',
    subtitle: 'Serums, barrier creams & botanical mists',
    itemCountLabel: '3 Essentials',
  },
  {
    id: 'Makeup',
    label: 'Makeup',
    subtitle: 'Silk-matte lips, skin tints & cheek duos',
    itemCountLabel: '3 Essentials',
  },
  {
    id: 'Haircare',
    label: 'Haircare',
    subtitle: 'Argan elixirs & whipped hibiscus masks',
    itemCountLabel: '2 Essentials',
  },
  {
    id: 'Fragrance',
    label: 'Fragrance',
    subtitle: 'Crystal eau de parfum & warm body mists',
    itemCountLabel: '2 Essentials',
  },
];

export const PRODUCTS: Product[] = [
  {
    id: 'rosehip-vitamin-c-serum',
    name: 'Luminous Rosehip & Vitamin C Serum',
    category: 'Skincare',
    priceNgn: 18500,
    size: '30 ml',
    shortDescription: 'Cold-pressed rosehip oil and stabilized Vitamin C for visible daily radiance.',
    fullDescription:
      'A featherlight facial serum formulated to brighten uneven tone, soften fine dry lines, and restore natural bounce without greasiness in humid Nigerian climates.',
    highlights: [
      '10% stabilized Vitamin C + organic rosehip seed oil',
      'Fast-absorbing finish suitable for melanin-rich skin',
      'Fragrance-free & gentle for morning and evening routines',
    ],
    image: '/src/assets/images/product_rosehip_serum_1794823661210.jpg',
    featured: true,
    tag: 'Bestseller',
  },
  {
    id: 'velvet-ceramide-cream',
    name: 'Velvet Ceramide Barrier Cream',
    category: 'Skincare',
    priceNgn: 16000,
    size: '50 ml',
    shortDescription: 'Whipped triple-ceramide moisturizer that seals in 24-hour hydration.',
    fullDescription:
      'Rich yet breathable cushion cream enriched with plant squalane, oat lipids, and skin-identical ceramides to calm sensitivity and strengthen your moisture barrier.',
    highlights: [
      'Triple-ceramide complex for lasting barrier repair',
      'Velvety satin texture sits cleanly under sunscreen and makeup',
      'Non-comedogenic & dermatologist tested',
    ],
    image: '/src/assets/images/product_ceramide_cream_1794823668032.jpg',
    featured: false,
  },
  {
    id: 'hibiscus-hydrating-mist',
    name: 'Hibiscus & Rosewater Hydrating Mist',
    category: 'Skincare',
    priceNgn: 12500,
    size: '100 ml',
    shortDescription: 'Ultra-fine botanical facial tonic to refresh, calm, and de-puff skin anytime.',
    fullDescription:
      'Distilled Damask rosewater infused with hibiscus petal antioxidants and panthenol. Use after cleansing, before serums, or midday for an instant cooling reset.',
    highlights: [
      'Micro-droplet rose-gold atomizer for even coverage',
      'Alcohol-free calming formula with panthenol (Vitamin B5)',
      'Doubles as a hydrating makeup setting veil',
    ],
    image: '/src/assets/images/product_hibiscus_mist_1794823663055.jpg',
    featured: false,
  },
  {
    id: 'silk-matte-velvet-lipstick',
    name: 'Silk-Matte Velvet Lipstick — Blush Rose',
    category: 'Makeup',
    priceNgn: 9500,
    size: '3.8 g',
    shortDescription: 'One-swipe high-pigment dusty rose lipstick with cushiony jojoba comfort.',
    fullDescription:
      'Sculpted in a brushed rose-gold case, this modern silk-matte formula delivers rich, flattering dusty-rose color without tugging or drying out the lips.',
    highlights: [
      'Infused with jojoba oil and shea butter for 8-hour comfort',
      'Soft-focus matte finish that never cracks or flakes',
      'Universal dusty blush-rose tone curated for warm undertones',
    ],
    image: '/src/assets/images/product_velvet_lipstick_1794823662941.jpg',
    featured: true,
    tag: 'Customer Favorite',
  },
  {
    id: 'dewy-honey-skin-tint',
    name: 'Lumière Dewy Skin Tint — Warm Honey',
    category: 'Makeup',
    priceNgn: 21000,
    size: '30 ml',
    shortDescription: 'Breathable serum-foundation hybrid with buildable medium coverage and glow.',
    fullDescription:
      'Blends seamlessly with fingertips or brush to even out skin tone while letting natural skin texture shine through. Sweat-resistant for long Lagos workdays.',
    highlights: [
      'Hyaluronic acid + niacinamide serum base',
      'Zero gray cast or oxidation over 10+ hours',
      'Precision rose-gold pump dispenser',
    ],
    image: '/src/assets/images/product_skin_tint_1794823664114.jpg',
    featured: false,
  },
  {
    id: 'petal-blush-highlighter-duo',
    name: 'Rose-Gold Cheek & Glow Compact Duo',
    category: 'Makeup',
    priceNgn: 14500,
    size: '12 g',
    shortDescription: 'Silky petal blush paired with a finely milled champagne rose-gold highlighter.',
    fullDescription:
      'A travel-ready mirrored compact featuring a velvety matte blush and a lit-from-within luminizer that melts onto cheekbones with zero chalky fallout.',
    highlights: [
      'Finely milled talc-free mineral pigments',
      'Buildable flush paired with champagne-rose shimmer',
      'Slim pocket mirror compact ideal for touch-ups',
    ],
    image: '/src/assets/images/product_blush_highlighter_duo_1794823663393.jpg',
    featured: true,
  },
  {
    id: 'argan-silk-hair-elixir',
    name: 'Botanical Argan & Shea Hair Elixir',
    category: 'Haircare',
    priceNgn: 15500,
    size: '100 ml',
    shortDescription: 'Nourishing cold-pressed oil blend to tame frizz and seal luminous shine.',
    fullDescription:
      'Crafted with Moroccan argan oil, sweet almond, and light unrefined shea esters to protect curls, coils, braids, and silk presses from heat and dryness.',
    highlights: [
      'Seals split ends and boosts natural curl definition',
      'Lightweight glass dropper prevents heavy buildup',
      'Safe for natural hair, locs, and protective styles',
    ],
    image: '/src/assets/images/product_argan_hair_elixir_1794823666192.jpg',
    featured: false,
  },
  {
    id: 'hibiscus-whipped-hair-mask',
    name: 'Whipped Hibiscus & Shea Moisture Mask',
    category: 'Haircare',
    priceNgn: 13800,
    size: '200 ml',
    shortDescription: 'Deep-conditioning botanical treatment that restores softness in 10 minutes.',
    fullDescription:
      'An intensely hydrating whipped hair mask powered by Nigerian hibiscus extract, raw shea butter, and hydrolyzed oat protein to detangle and strengthen brittle strands.',
    highlights: [
      'Rich slip for effortless wash-day detangling',
      'Sulfate-free, silicone-free, paraben-free',
      'Leaves hair soft with a subtle warm floral scent',
    ],
    image: '/src/assets/images/product_hibiscus_hair_mask_1794823665280.jpg',
    featured: false,
  },
  {
    id: 'blush-peony-oud-parfum',
    name: 'Éclat Rose & Peony Eau de Parfum',
    category: 'Fragrance',
    priceNgn: 34000,
    size: '50 ml',
    shortDescription: 'Radiant damask rose, pink peony, and warm white amber in a faceted flacon.',
    fullDescription:
      'Our signature long-wear Eau de Parfum opens with crisp bergamot and pear blossom, blooming into velvety peony before settling into warm sandalwood and soft musk.',
    highlights: [
      '20% perfume oil concentration for 8–10 hour sillage',
      'Notes: Bergamot · Pink Peony · Cashmere Musk',
      'Heavy faceted crystal bottle with brushed rose-gold cap',
    ],
    image: '/src/assets/images/product_peony_oud_perfume_1794823664575.jpg',
    featured: true,
    tag: 'Signature Scent',
  },
  {
    id: 'champagne-vanilla-body-mist',
    name: 'Champagne Rose & Vanilla Body Mist',
    category: 'Fragrance',
    priceNgn: 17500,
    size: '100 ml',
    shortDescription: 'Airy everyday veil of sugared rose petals, Madagascar vanilla, and warm musk.',
    fullDescription:
      'Designed for effortless daily layering after your shower or over body butter. Leaves skin delicately scented with a soft, inviting trail from morning to evening.',
    highlights: [
      'Aloe-infused base hydrates while scenting skin',
      'Notes: Sparkling Rose · Vanilla Bean · Soft Amber',
      'Ideal layering partner for Éclat Rose Eau de Parfum',
    ],
    image: '/src/assets/images/product_vanilla_body_mist_1794823664751.jpg',
    featured: false,
  },
];

export function formatNaira(amount: number): string {
  return `₦${Math.round(amount).toLocaleString('en-NG')}`;
}

export function buildWhatsAppOrderMessage(product: Product, quantity: number): string {
  const safeQty = Math.max(1, Math.floor(quantity));
  const total = product.priceNgn * safeQty;

  return [
    'Hello GlowNest Beauty Store, I would like to place an order:',
    '',
    `Product: ${product.name}`,
    `Category: ${product.category} (${product.size})`,
    `Quantity: ${safeQty}`,
    `Unit Price: ${formatNaira(product.priceNgn)}`,
    `Total Price: ${formatNaira(total)}`,
    '',
    'Please let me know availability and delivery details. Thank you!',
  ].join('\n');
}

export function buildWhatsAppProductOrderUrl(product: Product, quantity: number): string {
  const message = buildWhatsAppOrderMessage(product, quantity);
  return `https://wa.me/${STORE_INFO.whatsappDigits}?text=${encodeURIComponent(message)}`;
}

export function buildWhatsAppContactUrl(customNote?: string): string {
  const text =
    customNote ??
    'Hello GlowNest Beauty Store, I would like to inquire about your beauty products and delivery options.';
  return `https://wa.me/${STORE_INFO.whatsappDigits}?text=${encodeURIComponent(text)}`;
}
