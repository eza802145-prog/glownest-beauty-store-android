import React from 'react';
import { Clock, Mail, MapPin, MessageCircle, Phone, Smartphone } from 'lucide-react';
import { STORE_INFO, buildWhatsAppContactUrl } from '../data/products';
import { PWAInstallButton } from '../components/PWAInstallButton';

interface ContactOrdersScreenProps {
  onBrowseShop: () => void;
}

export const ContactOrdersScreen: React.FC<ContactOrdersScreenProps> = ({ onBrowseShop }) => {
  return (
    <div className="space-y-10">
      {/* Header */}
      <div className="pb-5 border-b border-[#EADCE0] flex flex-col sm:flex-row sm:items-end justify-between gap-4">
        <div>
          <p className="text-xs font-medium text-[#9F3B58]">Concierge &amp; Storefront</p>
          <h1 className="mt-1 font-display text-3xl sm:text-4xl font-semibold text-[#24191C]">
            Contact &amp; Orders
          </h1>
          <p className="mt-2 text-sm text-[#5C494F] max-w-xl">
            Reach our beauty concierge directly on WhatsApp for skincare recommendations, shade
            matching, and fast Lagos or nationwide dispatch.
          </p>
        </div>

        <a
          href={buildWhatsAppContactUrl()}
          target="_blank"
          rel="noopener noreferrer"
          className="inline-flex items-center justify-center gap-2.5 min-h-[48px] px-6 py-3 rounded-xl bg-[#9F3B58] text-white text-sm font-semibold hover:bg-[#862E48] transition-colors whitespace-nowrap shrink-0"
        >
          <MessageCircle className="w-5 h-5 shrink-0" aria-hidden="true" />
          <span>Chat on WhatsApp</span>
        </a>
      </div>

      {/* Contact Info & Opening Hours Grid */}
      <div className="grid grid-cols-1 lg:grid-cols-12 gap-6">
        {/* Store Contact Card */}
        <section
          aria-labelledby="store-contact-heading"
          className="lg:col-span-6 rounded-2xl bg-white border border-[#EADCE0] p-6 space-y-5"
        >
          <h2 id="store-contact-heading" className="font-display text-2xl font-semibold text-[#24191C]">
            Store Information
          </h2>

          <div className="space-y-4">
            <div className="flex items-start gap-3.5">
              <div className="w-10 h-10 rounded-xl bg-[#F7EFF1] text-[#9F3B58] flex items-center justify-center shrink-0">
                <MapPin className="w-5 h-5" aria-hidden="true" />
              </div>
              <div>
                <p className="text-xs font-medium text-[#6E5A60]">Boutique Address</p>
                <p className="mt-0.5 text-sm font-medium text-[#24191C]">{STORE_INFO.address}</p>
                <p className="mt-0.5 text-xs text-[#6E5A60]">{STORE_INFO.cityNote}</p>
              </div>
            </div>

            <div className="flex items-start gap-3.5 pt-4 border-t border-[#F3E8EC]">
              <div className="w-10 h-10 rounded-xl bg-[#F7EFF1] text-[#9F3B58] flex items-center justify-center shrink-0">
                <Phone className="w-5 h-5" aria-hidden="true" />
              </div>
              <div>
                <p className="text-xs font-medium text-[#6E5A60]">Phone &amp; WhatsApp Line</p>
                <a
                  href={`tel:${STORE_INFO.phoneDisplay.replace(/\s+/g, '')}`}
                  className="mt-0.5 inline-block text-sm font-medium text-[#24191C] hover:text-[#9F3B58] tabular-nums"
                >
                  {STORE_INFO.phoneDisplay}
                </a>
              </div>
            </div>

            <div className="flex items-start gap-3.5 pt-4 border-t border-[#F3E8EC]">
              <div className="w-10 h-10 rounded-xl bg-[#F7EFF1] text-[#9F3B58] flex items-center justify-center shrink-0">
                <Mail className="w-5 h-5" aria-hidden="true" />
              </div>
              <div>
                <p className="text-xs font-medium text-[#6E5A60]">Email &amp; Social</p>
                <a
                  href={`mailto:${STORE_INFO.email}`}
                  className="mt-0.5 inline-block text-sm font-medium text-[#24191C] hover:text-[#9F3B58]"
                >
                  {STORE_INFO.email}
                </a>
                <p className="mt-0.5 text-xs text-[#6E5A60]">Instagram: {STORE_INFO.instagramHandle}</p>
              </div>
            </div>
          </div>
        </section>

        {/* Opening Hours & Mobile App Install */}
        <div className="lg:col-span-6 space-y-6">
          <section
            aria-labelledby="hours-heading"
            className="rounded-2xl bg-white border border-[#EADCE0] p-6 space-y-4"
          >
            <div className="flex items-center gap-3">
              <div className="w-10 h-10 rounded-xl bg-[#F7EFF1] text-[#9F3B58] flex items-center justify-center shrink-0">
                <Clock className="w-5 h-5" aria-hidden="true" />
              </div>
              <h2 id="hours-heading" className="font-display text-2xl font-semibold text-[#24191C]">
                Opening Hours
              </h2>
            </div>

            <div className="divide-y divide-[#F3E8EC]">
              {STORE_INFO.hours.map((slot) => (
                <div key={slot.days} className="py-3 flex items-center justify-between gap-4 text-sm">
                  <span className="font-medium text-[#24191C]">{slot.days}</span>
                  <span className="text-[#5C494F] tabular-nums">{slot.time}</span>
                </div>
              ))}
            </div>
          </section>

          <section
            aria-labelledby="mobile-app-heading"
            className="rounded-2xl bg-[#F7EFF1] border border-[#EADCE0] p-6 space-y-3"
          >
            <div className="flex items-center gap-2.5 text-[#9F3B58]">
              <Smartphone className="w-5 h-5 shrink-0" aria-hidden="true" />
              <h2 id="mobile-app-heading" className="font-display text-2xl font-semibold text-[#24191C]">
                GlowNest Mobile App
              </h2>
            </div>
            <p className="text-sm text-[#4A3A3F] leading-relaxed">
              Install GlowNest Beauty Store directly on your Android or iPhone home screen for
              instant full-screen shopping and offline catalog access.
            </p>
            <div className="pt-1">
              <PWAInstallButton variant="card" />
            </div>
          </section>
        </div>
      </div>

      {/* Customer Order Instructions */}
      <section aria-labelledby="instructions-heading" className="pt-2 border-t border-[#EADCE0]">
        <div className="flex flex-col sm:flex-row sm:items-end justify-between gap-4 mb-6">
          <div>
            <h2 id="instructions-heading" className="font-display text-2xl sm:text-3xl font-semibold text-[#24191C]">
              How to Place an Order
            </h2>
            <p className="mt-1 text-sm text-[#6E5A60]">
              Simple, direct WhatsApp ordering with zero account sign-up required.
            </p>
          </div>
          <button
            type="button"
            onClick={onBrowseShop}
            className="min-h-[44px] px-4 py-2 rounded-xl border border-[#EADCE0] bg-white text-sm font-medium text-[#24191C] hover:border-[#C78D7E] transition-colors whitespace-nowrap shrink-0"
          >
            Browse Products
          </button>
        </div>

        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4 sm:gap-5">
          {STORE_INFO.orderInstructions.map((item) => (
            <div
              key={item.step}
              className="rounded-2xl bg-white border border-[#EADCE0] p-5 flex flex-col justify-between"
            >
              <div>
                <span className="font-display text-2xl font-semibold text-[#9F3B58] tabular-nums">
                  {item.step}.
                </span>
                <h3 className="mt-2 text-base font-semibold text-[#24191C]">{item.title}</h3>
                <p className="mt-2 text-sm text-[#5C494F] leading-relaxed">{item.detail}</p>
              </div>
            </div>
          ))}
        </div>
      </section>
    </div>
  );
};
