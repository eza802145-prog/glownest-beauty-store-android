import React from 'react';
import { ArrowLeft, Home, MessageCircle, Search, ShoppingBag, Sparkles } from 'lucide-react';
import { ScreenId } from '../types/store';
import { buildWhatsAppContactUrl } from '../data/products';
import { PWAInstallButton } from './PWAInstallButton';

interface NavigationProps {
  activeScreen: ScreenId;
  onNavigate: (screen: ScreenId) => void;
  onBackFromDetails: () => void;
  selectedProductName?: string;
}

const NAV_ITEMS: Array<{ id: ScreenId; label: string; mobileLabel: string; icon: React.ComponentType<{ className?: string }> }> = [
  { id: 'home', label: 'Home', mobileLabel: 'Home', icon: Home },
  { id: 'shop', label: 'Shop', mobileLabel: 'Shop', icon: ShoppingBag },
  { id: 'details', label: 'Product Details', mobileLabel: 'Details', icon: Sparkles },
  { id: 'contact', label: 'Contact & Orders', mobileLabel: 'Contact', icon: MessageCircle },
];

export const Navigation: React.FC<NavigationProps> = ({
  activeScreen,
  onNavigate,
  onBackFromDetails,
  selectedProductName,
}) => {
  const mobileTitle =
    activeScreen === 'home'
      ? 'GlowNest'
      : activeScreen === 'shop'
        ? 'Shop Collection'
        : activeScreen === 'details'
          ? selectedProductName || 'Product Details'
          : 'Contact & Orders';

  return (
    <>
      {/* Desktop Top Bar (3-zone contract) */}
      <header className="hidden md:block sticky top-0 z-40 bg-[#FCF9F6]/90 backdrop-blur-md border-b border-[#EADCE0]">
        <div className="max-w-7xl mx-auto px-6 lg:px-8 h-16 flex items-center justify-between gap-8">
          {/* Zone 1: Single-line brand wordmark */}
          <button
            type="button"
            onClick={() => onNavigate('home')}
            className="font-display text-2xl font-semibold tracking-tight text-[#24191C] whitespace-nowrap shrink-0 hover:text-[#9F3B58] transition-colors"
          >
            GlowNest
          </button>

          {/* Zone 2: Clean single-line text nav links */}
          <nav aria-label="Main navigation" className="flex items-center gap-7">
            {NAV_ITEMS.map((item) => {
              const isActive = activeScreen === item.id;
              return (
                <button
                  key={item.id}
                  type="button"
                  onClick={() => onNavigate(item.id)}
                  aria-current={isActive ? 'page' : undefined}
                  className={`relative py-2 text-sm font-medium whitespace-nowrap shrink-0 transition-colors ${
                    isActive ? 'text-[#9F3B58]' : 'text-[#5C494F] hover:text-[#24191C]'
                  }`}
                >
                  {item.label}
                  {isActive && (
                    <span
                      aria-hidden="true"
                      className="absolute inset-x-0 bottom-0 h-0.5 rounded-full bg-[#9F3B58]"
                    />
                  )}
                </button>
              );
            })}
          </nav>

          {/* Zone 3: Single primary action */}
          <div className="flex items-center shrink-0">
            <a
              href={buildWhatsAppContactUrl()}
              target="_blank"
              rel="noopener noreferrer"
              className="inline-flex items-center gap-2 min-h-[40px] px-4 py-2 rounded-lg bg-[#9F3B58] text-white text-xs font-medium hover:bg-[#862E48] transition-colors whitespace-nowrap shrink-0"
            >
              <MessageCircle className="w-3.5 h-3.5 shrink-0" aria-hidden="true" />
              <span>Order on WhatsApp</span>
            </a>
          </div>
        </div>
      </header>

      {/* Mobile Top App Bar (48px compact sticky header) */}
      <header className="md:hidden sticky top-0 z-40 h-12 bg-[#FCF9F6]/92 backdrop-blur-md border-b border-[#EADCE0] px-3 flex items-center justify-between gap-2">
        <div className="flex items-center min-w-[44px]">
          {activeScreen === 'details' ? (
            <button
              type="button"
              onClick={onBackFromDetails}
              aria-label="Back to Shop"
              className="min-w-[44px] min-h-[44px] -ml-1.5 flex items-center justify-center rounded-full text-[#24191C] hover:bg-[#F7EFF1] transition-colors"
            >
              <ArrowLeft className="w-5 h-5" />
            </button>
          ) : (
            <button
              type="button"
              onClick={() => onNavigate('home')}
              aria-label="GlowNest Home"
              className="min-w-[44px] min-h-[44px] -ml-1 flex items-center justify-center"
            >
              <span className="w-7 h-7 rounded-full bg-[#9F3B58] text-white font-display text-sm font-semibold flex items-center justify-center">
                G
              </span>
            </button>
          )}
        </div>

        <span
          className={`truncate text-center ${
            activeScreen === 'home'
              ? 'font-display text-xl font-semibold text-[#24191C]'
              : 'text-sm font-semibold text-[#24191C]'
          }`}
        >
          {mobileTitle}
        </span>

        <div className="flex items-center justify-end min-w-[44px]">
          <PWAInstallButton variant="header" />
          {activeScreen !== 'shop' && (
            <button
              type="button"
              onClick={() => onNavigate('shop')}
              aria-label="Search products"
              className="min-w-[44px] min-h-[44px] -mr-1.5 flex items-center justify-center rounded-full text-[#24191C] hover:bg-[#F7EFF1] transition-colors"
            >
              <Search className="w-4 h-4" />
            </button>
          )}
        </div>
      </header>

      {/* Mobile Fixed Bottom Tab Bar (56px height + safe area) */}
      <nav
        aria-label="Mobile bottom navigation"
        className="md:hidden fixed bottom-0 left-0 right-0 z-40 bg-white/95 backdrop-blur-md border-t border-[#EADCE0] pb-safe"
      >
        <div className="grid grid-cols-4 items-center h-14">
          {NAV_ITEMS.map((item) => {
            const Icon = item.icon;
            const isActive = activeScreen === item.id;
            return (
              <button
                key={item.id}
                type="button"
                onClick={() => onNavigate(item.id)}
                aria-current={isActive ? 'page' : undefined}
                className={`min-h-[44px] flex flex-col items-center justify-center gap-0.5 px-1 transition-colors ${
                  isActive ? 'text-[#9F3B58]' : 'text-[#6E5A60] hover:text-[#24191C]'
                }`}
              >
                <Icon className="w-5 h-5 shrink-0" aria-hidden="true" />
                <span className="text-[11px] font-medium tracking-tight leading-none whitespace-nowrap">
                  {item.mobileLabel}
                </span>
              </button>
            );
          })}
        </div>
      </nav>
    </>
  );
};
