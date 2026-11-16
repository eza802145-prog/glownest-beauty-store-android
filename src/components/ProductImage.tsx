import React, { useState } from 'react';
import { Sparkles } from 'lucide-react';

interface ProductImageProps {
  src: string;
  alt: string;
  title?: string;
  className?: string;
  loading?: 'eager' | 'lazy';
}

export const ProductImage: React.FC<ProductImageProps> = ({
  src,
  alt,
  title,
  className = 'w-full h-full object-cover',
  loading = 'lazy',
}) => {
  const [hasError, setHasError] = useState(false);

  if (hasError) {
    return (
      <div
        className="w-full h-full flex flex-col items-center justify-center p-6 text-center bg-gradient-to-br from-[#F7EFF1] via-[#FCF9F6] to-[#F3E2DF] text-[#6E5A60]"
        role="img"
        aria-label={alt}
      >
        <div className="w-12 h-12 rounded-full bg-white/80 border border-[#EADCE0] flex items-center justify-center mb-3 text-[#9F3B58]">
          <Sparkles className="w-5 h-5" aria-hidden="true" />
        </div>
        <span className="font-display text-base font-semibold text-[#24191C] line-clamp-2">
          {title || alt}
        </span>
      </div>
    );
  }

  return (
    <img
      src={src}
      alt={alt}
      loading={loading}
      referrerPolicy="no-referrer"
      onError={() => setHasError(true)}
      className={className}
    />
  );
};
