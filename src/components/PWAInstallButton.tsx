import React, { useState } from 'react';
import { Download, Smartphone, WifiOff, X } from 'lucide-react';
import { useOnlineStatus, usePWAInstall } from '../hooks/usePWAInstall';

interface PWAInstallButtonProps {
  variant?: 'header' | 'card';
}

export const PWAInstallButton: React.FC<PWAInstallButtonProps> = ({ variant = 'header' }) => {
  const { isInstallable, isInstalled, isIOS, install } = usePWAInstall();
  const [showGuide, setShowGuide] = useState(false);

  if (isInstalled) {
    return variant === 'card' ? (
      <p className="text-sm text-[#6E5A60]">
        GlowNest Beauty Store is currently running in standalone app mode.
      </p>
    ) : null;
  }

  if (isInstallable) {
    return (
      <button
        type="button"
        onClick={install}
        className={
          variant === 'card'
            ? 'inline-flex items-center justify-center gap-2 min-h-[44px] px-5 py-2.5 rounded-xl bg-[#9F3B58] text-white text-sm font-medium hover:bg-[#862E48] transition-colors whitespace-nowrap shrink-0'
            : 'inline-flex items-center justify-center gap-2 min-h-[40px] px-4 py-2 rounded-lg bg-[#9F3B58] text-white text-xs font-medium hover:bg-[#862E48] transition-colors whitespace-nowrap shrink-0'
        }
      >
        <Download className="w-4 h-4 shrink-0" aria-hidden="true" />
        <span>Install App</span>
      </button>
    );
  }

  if (isIOS || variant === 'card') {
    return (
      <>
        <button
          type="button"
          onClick={() => setShowGuide(true)}
          className={
            variant === 'card'
              ? 'inline-flex items-center justify-center gap-2 min-h-[44px] px-5 py-2.5 rounded-xl border border-[#D8C3C9] bg-white text-[#24191C] text-sm font-medium hover:bg-[#F7EFF1] transition-colors whitespace-nowrap shrink-0'
              : 'inline-flex items-center justify-center gap-2 min-h-[40px] px-3.5 py-1.5 rounded-lg border border-[#D8C3C9] bg-white text-[#24191C] text-xs font-medium hover:bg-[#F7EFF1] transition-colors whitespace-nowrap shrink-0'
          }
        >
          <Smartphone className="w-4 h-4 text-[#9F3B58] shrink-0" aria-hidden="true" />
          <span>{isIOS ? 'Install on iOS' : 'Android Install Guide'}</span>
        </button>

        {showGuide && (
          <div
            className="fixed inset-0 z-50 flex items-center justify-center bg-[#24191C]/50 backdrop-blur-sm p-4"
            role="dialog"
            aria-modal="true"
            aria-labelledby="pwa-guide-title"
          >
            <div className="w-full max-w-sm rounded-2xl bg-white p-6 border border-[#EADCE0] shadow-xl">
              <div className="flex items-start justify-between gap-4">
                <h3 id="pwa-guide-title" className="font-display text-2xl font-semibold text-[#24191C]">
                  {isIOS ? 'Install on iPhone or iPad' : 'Install on Android'}
                </h3>
                <button
                  type="button"
                  onClick={() => setShowGuide(false)}
                  aria-label="Close install instructions"
                  className="min-w-[44px] min-h-[44px] -mt-2 -mr-2 flex items-center justify-center rounded-full text-[#6E5A60] hover:bg-[#F7EFF1] transition-colors"
                >
                  <X className="w-5 h-5" />
                </button>
              </div>
              {isIOS ? (
                <ol className="mt-4 space-y-2.5 text-sm text-[#4A3A3F] list-decimal list-inside">
                  <li>Tap the <strong>Share</strong> icon in your Safari toolbar.</li>
                  <li>Scroll down and tap <strong>Add to Home Screen</strong>.</li>
                  <li>Tap <strong>Add</strong> to launch GlowNest like a native app.</li>
                </ol>
              ) : (
                <ol className="mt-4 space-y-2.5 text-sm text-[#4A3A3F] list-decimal list-inside">
                  <li>Open GlowNest Beauty Store in Chrome on your Android phone.</li>
                  <li>Tap the browser menu icon and select <strong>Install app</strong> or <strong>Add to Home screen</strong>.</li>
                  <li>Launch GlowNest from your home screen in standalone full-screen mode.</li>
                </ol>
              )}
              <button
                type="button"
                onClick={() => setShowGuide(false)}
                className="mt-6 w-full min-h-[44px] rounded-xl bg-[#24191C] text-white text-sm font-medium hover:bg-[#3A292E] transition-colors"
              >
                Done
              </button>
            </div>
          </div>
        )}
      </>
    );
  }

  return null;
};

export const OfflineIndicator: React.FC = () => {
  const isOnline = useOnlineStatus();

  if (isOnline) return null;

  return (
    <div
      role="status"
      aria-live="polite"
      className="fixed bottom-20 md:bottom-5 left-4 right-4 md:left-auto md:right-6 z-50 flex items-center gap-2.5 rounded-xl bg-[#24191C] px-4 py-2.5 text-xs font-medium text-white shadow-lg"
    >
      <WifiOff className="w-4 h-4 text-[#E6A4B4] shrink-0" aria-hidden="true" />
      <span>Offline mode — Browsing cached GlowNest catalog.</span>
    </div>
  );
};
