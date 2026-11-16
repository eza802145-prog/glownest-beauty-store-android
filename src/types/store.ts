export type ProductCategory = 'Skincare' | 'Makeup' | 'Haircare' | 'Fragrance';

export type CategoryFilter = 'All' | ProductCategory;

export type ScreenId = 'home' | 'shop' | 'details' | 'contact';

export interface Product {
  id: string;
  name: string;
  category: ProductCategory;
  priceNgn: number;
  size: string;
  shortDescription: string;
  fullDescription: string;
  highlights: string[];
  image: string;
  featured: boolean;
  tag?: string;
}

export interface CategorySummary {
  id: ProductCategory;
  label: string;
  subtitle: string;
  itemCountLabel: string;
}

export interface StoreInfo {
  name: string;
  tagline: string;
  whatsappDigits: string;
  phoneDisplay: string;
  email: string;
  instagramHandle: string;
  address: string;
  cityNote: string;
  hours: Array<{
    days: string;
    time: string;
  }>;
  orderInstructions: Array<{
    step: string;
    title: string;
    detail: string;
  }>;
}
