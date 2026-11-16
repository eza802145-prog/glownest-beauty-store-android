import React from 'react';
import { Search, X } from 'lucide-react';
import { PRODUCTS, formatNaira } from '../data/products';
import { CategoryFilter, Product } from '../types/store';
import { ProductImage } from '../components/ProductImage';

interface ShopScreenProps {
  selectedCategory: CategoryFilter;
  searchQuery: string;
  onCategoryChange: (category: CategoryFilter) => void;
  onSearchChange: (query: string) => void;
  onSelectProduct: (product: Product) => void;
  onResetFilters: () => void;
}

const CATEGORIES: CategoryFilter[] = ['All', 'Skincare', 'Makeup', 'Haircare', 'Fragrance'];

export const ShopScreen: React.FC<ShopScreenProps> = ({
  selectedCategory,
  searchQuery,
  onCategoryChange,
  onSearchChange,
  onSelectProduct,
  onResetFilters,
}) => {
  const normalizedQuery = searchQuery.trim().toLowerCase();

  const filteredProducts = PRODUCTS.filter((product) => {
    const matchesCategory = selectedCategory === 'All' || product.category === selectedCategory;
    if (!matchesCategory) return false;

    if (!normalizedQuery) return true;
    const haystack = [
      product.name,
      product.category,
      product.shortDescription,
      product.fullDescription,
      ...product.highlights,
    ]
      .join(' ')
      .toLowerCase();
    return haystack.includes(normalizedQuery);
  });

  return (
    <div className="space-y-6 sm:space-y-8">
      {/* Page Header */}
      <div className="flex flex-col sm:flex-row sm:items-end justify-between gap-4 pb-5 border-b border-[#EADCE0]">
        <div>
          <p className="text-xs font-medium text-[#9F3B58]">GlowNest Catalog</p>
          <h1 className="mt-1 font-display text-3xl sm:text-4xl font-semibold text-[#24191C]">
            Shop Beauty Essentials
          </h1>
        </div>
        <p className="text-sm text-[#6E5A60] tabular-nums">
          {filteredProducts.length} {filteredProducts.length === 1 ? 'product' : 'products'} available
        </p>
      </div>

      {/* Filter & Search Bar */}
      <div className="flex flex-col lg:flex-row lg:items-center justify-between gap-4">
        {/* Category Filter Buttons */}
        <div
          role="group"
          aria-label="Filter products by category"
          className="flex items-center gap-2 overflow-x-auto pb-1 sm:pb-0"
        >
          {CATEGORIES.map((category) => {
            const isActive = selectedCategory === category;
            return (
              <button
                key={category}
                type="button"
                onClick={() => onCategoryChange(category)}
                aria-pressed={isActive}
                className={`min-h-[44px] px-4 py-2 rounded-xl text-sm font-medium whitespace-nowrap shrink-0 transition-colors ${
                  isActive
                    ? 'bg-[#9F3B58] text-white'
                    : 'bg-white text-[#5C494F] border border-[#EADCE0] hover:border-[#C78D7E] hover:text-[#24191C]'
                }`}
              >
                {category}
              </button>
            );
          })}
        </div>

        {/* Search Input */}
        <div className="w-full lg:w-80">
          <label htmlFor="shop-search-input" className="sr-only">
            Search shop products
          </label>
          <div className="flex items-center gap-2 rounded-xl bg-white border border-[#EADCE0] px-3 focus-within:border-[#9F3B58] transition-colors">
            <Search className="w-4 h-4 text-[#6E5A60] shrink-0" aria-hidden="true" />
            <input
              id="shop-search-input"
              type="search"
              value={searchQuery}
              onChange={(e) => onSearchChange(e.target.value)}
              placeholder="Search by name or ingredient..."
              className="w-full min-h-[44px] bg-transparent text-sm text-[#24191C] placeholder:text-[#8E7A80] focus:outline-none"
            />
            {searchQuery && (
              <button
                type="button"
                onClick={() => onSearchChange('')}
                aria-label="Clear search query"
                className="min-w-[36px] min-h-[36px] flex items-center justify-center rounded-lg text-[#6E5A60] hover:bg-[#F7EFF1]"
              >
                <X className="w-4 h-4" />
              </button>
            )}
          </div>
        </div>
      </div>

      {/* Product Grid or Empty State */}
      {filteredProducts.length === 0 ? (
        <div className="rounded-2xl bg-white border border-[#EADCE0] p-10 text-center max-w-lg mx-auto my-8">
          <h2 className="font-display text-2xl font-semibold text-[#24191C]">
            No matching products found
          </h2>
          <p className="mt-2 text-sm text-[#6E5A60]">
            Try adjusting your search keywords or switching back to all beauty categories.
          </p>
          <button
            type="button"
            onClick={onResetFilters}
            className="mt-5 inline-flex items-center justify-center min-h-[44px] px-5 py-2.5 rounded-xl bg-[#9F3B58] text-white text-sm font-medium hover:bg-[#862E48] transition-colors"
          >
            Reset Filters
          </button>
        </div>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-5 sm:gap-6">
          {filteredProducts.map((product) => (
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

              <div className="p-5 flex-1 flex flex-col justify-between">
                <div>
                  <div className="flex items-center gap-2 text-xs text-[#6E5A60]">
                    <span className="font-medium text-[#9F3B58]">{product.category}</span>
                    <span aria-hidden="true">·</span>
                    <span>{product.size}</span>
                    {product.tag && (
                      <>
                        <span aria-hidden="true">·</span>
                        <span>{product.tag}</span>
                      </>
                    )}
                  </div>
                  <h2 className="mt-1.5 text-lg font-semibold text-[#24191C] leading-snug">
                    {product.name}
                  </h2>
                  <p className="mt-2 text-sm text-[#5C494F] line-clamp-2 leading-relaxed">
                    {product.shortDescription}
                  </p>
                </div>

                <div className="mt-5 pt-4 border-t border-[#F3E8EC] flex items-center justify-between gap-3">
                  <span className="text-lg font-semibold text-[#24191C] tabular-nums">
                    {formatNaira(product.priceNgn)}
                  </span>
                  <button
                    type="button"
                    onClick={() => onSelectProduct(product)}
                    className="min-h-[44px] px-4 py-2 rounded-xl bg-[#9F3B58] text-white text-sm font-medium hover:bg-[#862E48] transition-colors whitespace-nowrap shrink-0"
                  >
                    View Details
                  </button>
                </div>
              </div>
            </article>
          ))}
        </div>
      )}
    </div>
  );
};
