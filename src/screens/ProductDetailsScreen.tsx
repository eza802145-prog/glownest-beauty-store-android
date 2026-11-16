import React, { useState } from 'react';
import { ArrowLeft, Check, Copy, MessageCircle, Minus, Plus } from 'lucide-react';
import {
  PRODUCTS,
  buildWhatsAppOrderMessage,
  buildWhatsAppProductOrderUrl,
  formatNaira,
} from '../data/products';
import { Product } from '../types/store';
import { ProductImage } from '../components/ProductImage';

interface ProductDetailsScreenProps {
  product: Product;
  quantity: number;
  onQuantityChange: (nextQuantity: number) => void;
  onBackToShop: () => void;
  onSelectProduct: (product: Product) => void;
}

export const ProductDetailsScreen: React.FC<ProductDetailsScreenProps> = ({
  product,
  quantity,
  onQuantityChange,
  onBackToShop,
  onSelectProduct,
}) => {
  const [copied, setCopied] = useState(false);

  const safeQty = Math.max(1, quantity);
  const totalPrice = product.priceNgn * safeQty;
  const whatsappUrl = buildWhatsAppProductOrderUrl(product, safeQty);
  const whatsappMessage = buildWhatsAppOrderMessage(product, safeQty);

  const relatedProducts = PRODUCTS.filter(
    (item) => item.id !== product.id && item.category === product.category
  ).slice(0, 3);

  const handleCopySummary = async () => {
    try {
      await navigator.clipboard.writeText(whatsappMessage);
      setCopied(true);
      window.setTimeout(() => setCopied(false), 2200);
    } catch {
      setCopied(false);
    }
  };

  return (
    <div className="space-y-12">
      {/* Back navigation */}
      <div>
        <button
          type="button"
          onClick={onBackToShop}
          className="inline-flex items-center gap-2 min-h-[44px] px-3 -ml-3 rounded-xl text-sm font-medium text-[#5C494F] hover:text-[#24191C] hover:bg-[#F7EFF1] transition-colors"
        >
          <ArrowLeft className="w-4 h-4" aria-hidden="true" />
          <span>Back to Shop</span>
        </button>
      </div>

      {/* Main Contiguous Purchase Module */}
      <div className="grid grid-cols-1 lg:grid-cols-12 gap-8 lg:gap-12 items-start">
        {/* Large Product Photograph */}
        <div className="lg:col-span-6 lg:sticky lg:top-24">
          <div className="aspect-[4/3] rounded-2xl overflow-hidden bg-[#F7EFF1] border border-[#EADCE0]">
            <ProductImage
              src={product.image}
              alt={product.name}
              title={product.name}
              loading="eager"
              className="w-full h-full object-cover"
            />
          </div>
        </div>

        {/* Product Info & WhatsApp Order Controls */}
        <div className="lg:col-span-6 space-y-6">
          <div>
            <div className="flex items-center gap-2 text-xs text-[#6E5A60]">
              <span className="font-semibold text-[#9F3B58]">{product.category}</span>
              <span aria-hidden="true">·</span>
              <span>{product.size}</span>
              {product.tag && (
                <>
                  <span aria-hidden="true">·</span>
                  <span>{product.tag}</span>
                </>
              )}
            </div>

            <h1 className="mt-2 font-display text-3xl sm:text-4xl font-semibold text-[#24191C] leading-tight text-balance">
              {product.name}
            </h1>

            <p className="mt-3 text-2xl font-semibold text-[#9F3B58] tabular-nums">
              {formatNaira(product.priceNgn)}
            </p>
          </div>

          <p className="text-base text-[#4A3A3F] leading-relaxed">
            {product.fullDescription}
          </p>

          {/* Key Formulation Highlights */}
          <div className="pt-4 border-t border-[#EADCE0]">
            <h2 className="text-sm font-semibold text-[#24191C]">Key Benefits &amp; Notes</h2>
            <ul className="mt-3 space-y-2.5">
              {product.highlights.map((item) => (
                <li key={item} className="flex items-start gap-2.5 text-sm text-[#4A3A3F]">
                  <Check className="w-4 h-4 text-[#9F3B58] mt-0.5 shrink-0" aria-hidden="true" />
                  <span>{item}</span>
                </li>
              ))}
            </ul>
          </div>

          {/* Quantity & Live Total */}
          <div className="p-5 rounded-2xl bg-white border border-[#EADCE0] space-y-5">
            <div className="flex flex-wrap items-center justify-between gap-4">
              <div>
                <span className="block text-sm font-semibold text-[#24191C]">Quantity</span>
                <span className="text-xs text-[#6E5A60]">Select units to order</span>
              </div>

              <div className="inline-flex items-center rounded-xl border border-[#EADCE0] bg-[#FCF9F6] p-1">
                <button
                  type="button"
                  onClick={() => onQuantityChange(Math.max(1, safeQty - 1))}
                  disabled={safeQty <= 1}
                  aria-label="Decrease quantity"
                  className="min-w-[44px] min-h-[44px] flex items-center justify-center rounded-lg text-[#24191C] hover:bg-white disabled:opacity-40 disabled:cursor-not-allowed transition-colors"
                >
                  <Minus className="w-4 h-4" />
                </button>
                <span
                  aria-live="polite"
                  className="min-w-[48px] text-center text-base font-semibold text-[#24191C] tabular-nums"
                >
                  {safeQty}
                </span>
                <button
                  type="button"
                  onClick={() => onQuantityChange(Math.min(20, safeQty + 1))}
                  disabled={safeQty >= 20}
                  aria-label="Increase quantity"
                  className="min-w-[44px] min-h-[44px] flex items-center justify-center rounded-lg text-[#24191C] hover:bg-white disabled:opacity-40 disabled:cursor-not-allowed transition-colors"
                >
                  <Plus className="w-4 h-4" />
                </button>
              </div>
            </div>

            <div className="flex items-center justify-between pt-4 border-t border-[#F3E8EC]">
              <span className="text-sm text-[#5C494F]">Order Total</span>
              <span className="text-xl font-semibold text-[#24191C] tabular-nums">
                {formatNaira(totalPrice)}
              </span>
            </div>

            {/* Order on WhatsApp CTA */}
            <div className="space-y-3">
              <a
                href={whatsappUrl}
                target="_blank"
                rel="noopener noreferrer"
                className="w-full min-h-[48px] px-6 py-3.5 rounded-xl bg-[#9F3B58] text-white text-sm font-semibold flex items-center justify-center gap-2.5 hover:bg-[#862E48] active:scale-[0.99] transition-all"
              >
                <MessageCircle className="w-5 h-5 shrink-0" aria-hidden="true" />
                <span>Order on WhatsApp</span>
              </a>

              <button
                type="button"
                onClick={handleCopySummary}
                className="w-full min-h-[44px] px-4 py-2.5 rounded-xl border border-[#EADCE0] bg-[#FCF9F6] text-xs font-medium text-[#4A3A3F] hover:bg-[#F7EFF1] transition-colors flex items-center justify-center gap-2"
              >
                {copied ? (
                  <>
                    <Check className="w-4 h-4 text-[#9F3B58]" aria-hidden="true" />
                    <span>Order message copied</span>
                  </>
                ) : (
                  <>
                    <Copy className="w-4 h-4 text-[#6E5A60]" aria-hidden="true" />
                    <span>Copy WhatsApp order message</span>
                  </>
                )}
              </button>
            </div>
          </div>

          {/* Transparent WhatsApp Message Preview */}
          <div className="rounded-2xl bg-[#F7EFF1]/70 border border-[#EADCE0] p-4">
            <p className="text-xs font-semibold text-[#5C494F] mb-2">
              Pre-filled WhatsApp Order Summary
            </p>
            <pre className="text-xs text-[#4A3A3F] whitespace-pre-wrap font-sans leading-relaxed">
              {whatsappMessage}
            </pre>
          </div>
        </div>
      </div>

      {/* Related Products */}
      {relatedProducts.length > 0 && (
        <section aria-labelledby="related-heading" className="pt-8 border-t border-[#EADCE0]">
          <h2 id="related-heading" className="font-display text-2xl font-semibold text-[#24191C] mb-5">
            More in {product.category}
          </h2>
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-5">
            {relatedProducts.map((item) => (
              <button
                key={item.id}
                type="button"
                onClick={() => onSelectProduct(item)}
                className="group text-left rounded-2xl bg-white border border-[#EADCE0] p-3 flex items-center gap-4 hover:border-[#C78D7E] transition-colors"
              >
                <div className="w-20 h-20 rounded-xl overflow-hidden bg-[#F7EFF1] shrink-0">
                  <ProductImage
                    src={item.image}
                    alt={item.name}
                    title={item.name}
                    className="w-full h-full object-cover"
                  />
                </div>
                <div className="min-w-0 flex-1">
                  <p className="text-xs text-[#6E5A60]">{item.size}</p>
                  <p className="text-sm font-semibold text-[#24191C] truncate group-hover:text-[#9F3B58] transition-colors">
                    {item.name}
                  </p>
                  <p className="mt-1 text-sm font-semibold text-[#9F3B58] tabular-nums">
                    {formatNaira(item.priceNgn)}
                  </p>
                </div>
              </button>
            ))}
          </div>
        </section>
      )}
    </div>
  );
};
