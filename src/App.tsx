import React, { Component, ErrorInfo, ReactNode, useCallback, useEffect, useState } from 'react';
import { Navigation } from './components/Navigation';
import { OfflineIndicator } from './components/PWAInstallButton';
import { PRODUCTS, STORE_INFO } from './data/products';
import { ContactOrdersScreen } from './screens/ContactOrdersScreen';
import { HomeScreen } from './screens/HomeScreen';
import { ProductDetailsScreen } from './screens/ProductDetailsScreen';
import { ShopScreen } from './screens/ShopScreen';
import { CategoryFilter, Product, ProductCategory, ScreenId } from './types/store';

interface ErrorBoundaryState {
  hasError: boolean;
}

class StoreErrorBoundary extends Component<{ children: ReactNode; onRecover: () => void }, ErrorBoundaryState> {
  state: ErrorBoundaryState = { hasError: false };

  static getDerivedStateFromError(): ErrorBoundaryState {
    return { hasError: true };
  }

  componentDidCatch(error: Error, info: ErrorInfo) {
    console.error('GlowNest UI error:', error, info);
  }

  render() {
    if (this.state.hasError) {
      return (
        <div className="max-w-md mx-auto my-12 rounded-2xl bg-white border border-[#EADCE0] p-8 text-center">
          <h2 className="font-display text-2xl font-semibold text-[#24191C]">
            Something went wrong
          </h2>
          <p className="mt-2 text-sm text-[#5C494F]">
            We encountered an unexpected display issue. Tap below to return to the GlowNest home screen.
          </p>
          <button
            type="button"
            onClick={() => {
              this.setState({ hasError: false });
              this.props.onRecover();
            }}
            className="mt-5 min-h-[44px] px-5 py-2.5 rounded-xl bg-[#9F3B58] text-white text-sm font-medium hover:bg-[#862E48] transition-colors"
          >
            Return Home
          </button>
        </div>
      );
    }
    return this.props.children;
  }
}

function getInitialRoute(): { screen: ScreenId; productId: string } {
  if (typeof window === 'undefined') {
    return { screen: 'home', productId: PRODUCTS[0].id };
  }
  const hash = window.location.hash.replace(/^#/, '');
  if (hash === 'shop' || hash === 'contact' || hash === 'home') {
    return { screen: hash, productId: PRODUCTS[0].id };
  }
  if (hash.startsWith('product/')) {
    const id = hash.slice('product/'.length);
    const matched = PRODUCTS.find((p) => p.id === id);
    if (matched) {
      return { screen: 'details', productId: matched.id };
    }
  }
  return { screen: 'home', productId: PRODUCTS[0].id };
}

export default function App() {
  const initial = getInitialRoute();
  const [activeScreen, setActiveScreen] = useState<ScreenId>(initial.screen);
  const [selectedCategory, setSelectedCategory] = useState<CategoryFilter>('All');
  const [searchQuery, setSearchQuery] = useState('');
  const [selectedProductId, setSelectedProductId] = useState<string>(initial.productId);
  const [quantity, setQuantity] = useState<number>(1);

  const selectedProduct =
    PRODUCTS.find((product) => product.id === selectedProductId) ?? PRODUCTS[0];

  const syncHash = useCallback((screen: ScreenId, productId?: string) => {
    const nextHash = screen === 'details' && productId ? `#product/${productId}` : `#${screen}`;
    if (window.location.hash !== nextHash) {
      window.history.pushState({ screen, productId }, '', nextHash);
    }
  }, []);

  useEffect(() => {
    const handlePopState = () => {
      const next = getInitialRoute();
      setActiveScreen(next.screen);
      setSelectedProductId(next.productId);
      setQuantity(1);
    };

    window.addEventListener('popstate', handlePopState);
    return () => window.removeEventListener('popstate', handlePopState);
  }, []);

  const navigateTo = useCallback(
    (screen: ScreenId) => {
      setActiveScreen(screen);
      syncHash(screen, screen === 'details' ? selectedProduct.id : undefined);
      window.scrollTo({ top: 0, behavior: 'smooth' });
    },
    [selectedProduct.id, syncHash]
  );

  const handleSelectProduct = useCallback(
    (product: Product) => {
      setSelectedProductId(product.id);
      setQuantity(1);
      setActiveScreen('details');
      syncHash('details', product.id);
      window.scrollTo({ top: 0, behavior: 'smooth' });
    },
    [syncHash]
  );

  const handleHomeSearch = useCallback(
    (query: string) => {
      setSearchQuery(query);
      setSelectedCategory('All');
      setActiveScreen('shop');
      syncHash('shop');
      window.scrollTo({ top: 0, behavior: 'smooth' });
    },
    [syncHash]
  );

  const handleSelectCategory = useCallback(
    (category: ProductCategory) => {
      setSelectedCategory(category);
      setSearchQuery('');
      setActiveScreen('shop');
      syncHash('shop');
      window.scrollTo({ top: 0, behavior: 'smooth' });
    },
    [syncHash]
  );

  const handleResetFilters = useCallback(() => {
    setSelectedCategory('All');
    setSearchQuery('');
  }, []);

  return (
    <div className="min-h-screen flex flex-col bg-[#FCF9F6] text-[#24191C]">
      <Navigation
        activeScreen={activeScreen}
        onNavigate={navigateTo}
        onBackFromDetails={() => navigateTo('shop')}
        selectedProductName={selectedProduct.name}
      />

      <main className="flex-1 w-full max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 pt-6 sm:pt-10 pb-24 md:pb-16">
        <StoreErrorBoundary onRecover={() => navigateTo('home')}>
          {activeScreen === 'home' && (
            <HomeScreen
              onShopNow={() => navigateTo('shop')}
              onSearchSubmit={handleHomeSearch}
              onSelectCategory={handleSelectCategory}
              onSelectProduct={handleSelectProduct}
            />
          )}

          {activeScreen === 'shop' && (
            <ShopScreen
              selectedCategory={selectedCategory}
              searchQuery={searchQuery}
              onCategoryChange={setSelectedCategory}
              onSearchChange={setSearchQuery}
              onSelectProduct={handleSelectProduct}
              onResetFilters={handleResetFilters}
            />
          )}

          {activeScreen === 'details' && (
            <ProductDetailsScreen
              product={selectedProduct}
              quantity={quantity}
              onQuantityChange={setQuantity}
              onBackToShop={() => navigateTo('shop')}
              onSelectProduct={handleSelectProduct}
            />
          )}

          {activeScreen === 'contact' && (
            <ContactOrdersScreen onBrowseShop={() => navigateTo('shop')} />
          )}
        </StoreErrorBoundary>
      </main>

      {/* Clean Editorial Footer */}
      <footer className="hidden md:block border-t border-[#EADCE0] bg-white">
        <div className="max-w-7xl mx-auto px-6 lg:px-8 py-8 flex flex-col sm:flex-row items-center justify-between gap-4 text-xs text-[#6E5A60]">
          <p className="font-medium text-[#24191C]">
            {STORE_INFO.name} — {STORE_INFO.tagline}
          </p>
          <div className="flex items-center gap-6">
            <button
              type="button"
              onClick={() => navigateTo('home')}
              className="hover:text-[#9F3B58] transition-colors"
            >
              Home
            </button>
            <button
              type="button"
              onClick={() => navigateTo('shop')}
              className="hover:text-[#9F3B58] transition-colors"
            >
              Shop
            </button>
            <button
              type="button"
              onClick={() => navigateTo('contact')}
              className="hover:text-[#9F3B58] transition-colors"
            >
              Contact &amp; Orders
            </button>
          </div>
          <p>{STORE_INFO.address}</p>
        </div>
      </footer>

      <OfflineIndicator />
    </div>
  );
}
