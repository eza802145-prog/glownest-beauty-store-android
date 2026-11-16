import React, { useState } from 'react';
import { ArrowRight, Search, X } from 'lucide-react';
import {
  BEAUTY_CATEGORIES,
  HERO_IMAGE,
  PRODUCTS,
  formatNaira,
} from '../data/products';
import { Product, ProductCategory } from '../types/store';
import { ProductImage } from '../components/ProductImage';

interface HomeScreenProps {
  onShopNow: () => void;
  onSearchSubmit: (query: string) => void;
  onSelectCategory: (category: ProductCategory) => void;
  onSelectProduct: (product: Product) => void;
}

export const HomeScreen: React.FC<HomeScreenProps> = ({
  onShopNow,
  onSearchSubmit,
  onSelectCategory,
  onSelectProduct,
}) => {
  const [searchValue, setSearchValue] = useState('');
  const featuredProducts = PRODUCTS.filter((product) => product.featured);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSearchSubmit(searchValue.trim());
  };

  return (
    <div className="space-y-12 md:space-y-16">
      {/* Section 1: Storefront Editorial Hero + Search */}
      <section className="grid grid-cols-1 lg:grid-cols-12 gap-6 lg:gap-10 items-stretch">
        <div className="lg:col-span-6 flex flex-col justify-center py-2 lg:py-6">
          <p className="text-xs font-medium tracking-wide text-[#9F3B58] mb-3">
            GlowNest Beauty Store · Lagos &amp; Nationwide
          </p>
          <h1 className="font-display text-4xl sm:text-5xl lg:text-[3.5rem] font-semibold leading-[1.08] tracking-tight text-[#24191C] text-balance">
            Your Beauty, Your Confidence.
          </h1>
          <p className="mt-4 text-base text-[#5C494F] leading-relaxed max-w-xl">
            Curated skincare, silk-matte makeup, botanical haircare, and luminous fragrances priced
            transparently in Nigerian Naira with instant WhatsApp ordering.
          </p>

          {/* Search bar */}
          <form onSubmit={handleSubmit} className="mt-6 max-w-lg" role="search">
            <label htmlFor="home-product-search" className="sr-only">
              Search beauty products
            </label>
            <div className="flex items-center gap-2 rounded-2xl bg-white border border-[#EADCE0] p-1.5 shadow-sm focus-within:border-[#9F3B58] transition-colors">
              <Search className="w-4 h-4 text-[#6E5A60] ml-3 shrink-0" aria-hidden="true" />
              <input
                id="home-product-search"
                type="search"
                value={searchValue}
                onChange={(e) => setSearchValue(e.target.value)}
                placeholder="Search serums, lipstick, perfume..."
                className="w-full min-h-[40px] bg-transparent text-sm text-[#24191C] placeholder:text-[#8E7A80] focus:outline-none"
              />
              {searchValue && (
                <button
                  type="button"
                  onClick={() => setSearchValue('')}
                  aria-label="Clear search"
                  className="min-w-[36px] min-h-[36px] flex items-center justify-center rounded-xl text-[#6E5A60] hover:bg-[#F7EFF1]"
                >
                  <X className="w-4 h-4" />
                </button>
              )}
              <button
                type="submit"
                className="min-h-[40px] px-4 rounded-xl bg-[#24191C] text-white text-xs font-medium hover:bg-[#3A292E] transition-colors whitespace-nowrap shrink-0"
              >
                Search
              </button>
            </div>
          </form>

          {/* Primary CTA */}
          <div className="mt-6 flex flex-wrap items-center gap-4">
            <button
              type="button"
              onClick={onShopNow}
              className="inline-flex items-center justify-center gap-2.5 min-h-[48px] px-7 py-3 rounded-xl bg-[#9F3B58] text-white text-sm font-medium hover:bg-[#862E48] active:scale-[0.99] transition-all whitespace-nowrap shrink-0"
            >
              <span>Shop Now</span>
              <ArrowRight className="w-4 h-4 shrink-0" aria-hidden="true" />
            </button>
            <span className="text-xs text-[#6E5A60]">
              Direct concierge ordering · Pay on verified confirmation
            </span>
          </div>
        </div>

        {/* Hero Photograph */}
        <div className="lg:col-span-6">
          <div className="aspect-[16/10] lg:aspect-auto lg:h-full lg:min-h-[380px] rounded-2xl overflow-hidden bg-[#F7EFF1] border border-[#EADCE0]">
            <ProductImage
              src={HERO_IMAGE}
              alt="GlowNest Beauty Store curated skincare serums, blush lipstick, and crystal fragrance"
              title="GlowNest Beauty Store"
              loading="eager"
              className="w-full h-full object-cover"
            />
          </div>
        </div>
      </section>

      {/* Section 2: Beauty Categories */}
      <section aria-labelledby="categories-heading" className="pt-2 border-t border-[#EADCE0]">
        <div className="flex items-end justify-between gap-4 mb-6">
          <div>
            <h2 id="categories-heading" className="font-display text-2xl sm:text-3xl font-semibold text-[#24191C]">
              Beauty Categories
            </h2>
            <p className="mt-1 text-sm text-[#6E5A60]">
              Explore essentials tailored for radiant skin, effortless color, healthy hair, and signature scent.
            </p>
          </div>
          <button
            type="button"
            onClick={onShopNow}
            className="hidden sm:inline-flex items-center gap-1.5 text-sm font-medium text-[#9F3B58] hover:text-[#862E48] whitespace-nowrap shrink-0"
          >
            <span>Browse All</span>
            <ArrowRight className="w-4 h-4" aria-hidden="true" />
          </button>
        </div>

        <div className="grid grid-cols-2 lg:grid-cols-4 gap-3 sm:gap-5">
          {BEAUTY_CATEGORIES.map((category) => (
            <button
              key={category.id}
              type="button"
              onClick={() => onSelectCategory(category.id)}
              className="group text-left p-4 sm:p-5 rounded-2xl bg-white border border-[#EADCE0] hover:border-[#C78D7E] hover:-translate-y-0.5 transition-all min-h-[112px] flex flex-col justify-between"
            >
              <div>
                <p className="text-xs text-[#9F3B58] font-medium">{category.itemCountLabel}</p>
                <h3 className="mt-1 font-display text-xl sm:text-2xl font-semibold text-[#24191C] group-hover:text-[#9F3B58] transition-colors">
                  {category.label}
                </h3>
              </div>
              <div className="mt-3 flex items-center justify-between gap-2">
                <span className="text-xs text-[#6E5A60] line-clamp-2">{category.subtitle}</span>
                <ArrowRight
                  className="w-4 h-4 text-[#9F3B58] shrink-0 group-hover:translate-x-0.5 transition-transform"
                  aria-hidden="true"
                />
              </div>
            </button>
          ))}
        </div>
      </section>

      {/* Section 3: Featured Products */}
      <section aria-labelledby="featured-heading" className="pt-2 border-t border-[#EADCE0]">
        <div className="flex items-end justify-between gap-4 mb-6">
          <div>
            <h2 id="featured-heading" className="font-display text-2xl sm:text-3xl font-semibold text-[#24191C]">
              Featured Essentials
            </h2>
            <p className="mt-1 text-sm text-[#6E5A60]">
              Our most-requested formulas across skincare, makeup, and fragrance.
            </p>
          </div>
          <button
            type="button"
            onClick={onShopNow}
            className="inline-flex items-center gap-1.5 min-h-[44px] px-2 text-sm font-medium text-[#9F3B58] hover:text-[#862E48] whitespace-nowrap shrink-0"
          >
            <span>View Full Shop</span>
            <ArrowRight className="w-4 h-4" aria-hidden="true" />
          </button>
        </div>

        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-5 sm:gap-6">
          {featuredProducts.map((product) => (
            <article
              key={product.id}
              className="group rounded-2xl bg-white border border-[#EADCE0] overflow-hidden flex flex-col hover:-translate-y-0.5 hover:shadow-md transition-all duration-200"
            >
              <button
                type="button"
                onClick={() => onSelectProduct(product)}
                className="aspect-[4/3] w-full bg-[#F7EFF1] overflow-hidden text-left"
              >
                <ProductImage
                  src={product.image}
                  alt={product.name}
                  title={product.name}
                  className="w-full h-full object-cover group-hover:scale-[1.03] transition-transform duration-300"
                />
              </button>

              <div className="p-4 flex-1 flex flex-col justify-between">
                <div>
                  <div className="flex items-center gap-2 text-xs text-[#6E5A60]">
                    <span>{product.category}</span>
                    <span aria-hidden="true">·</span>
                    <span>{product.size}</span>
                  </div>
                  <h3 className="mt-1.5 text-base font-semibold text-[#24191C] leading-snug">
                    {product.name}
                  </h3>
                  <p className="mt-1.5 text-xs text-[#5C494F] line-clamp-2 leading-relaxed">
                    {product.shortDescription}
                  </p>
                </div>

                <div className="mt-4 pt-3 border-t border-[#F3E8EC] flex items-center justify-between gap-3">
                  <span className="text-base font-semibold text-[#24191C] tabular-nums">
                    {formatNaira(product.priceNgn)}
                  </span>
                  <button
                    type="button"
                    onClick={() => onSelectProduct(product)}
                    className="min-h-[40px] px-3.5 py-2 rounded-xl bg-[#F7EFF1] text-[#9F3B58] text-xs font-semibold hover:bg-[#9F3B58] hover:text-white transition-colors whitespace-nowrap shrink-0"
                  >
                    View Details
                  </button>
                </div>
              </div>
            </article>
          ))}
        </div>
      </section>
    </div>
  );
};
