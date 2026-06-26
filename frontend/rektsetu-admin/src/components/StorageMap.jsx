export default function StorageMap() {
  return (
    <div className="bg-white p-6 rounded-xl border border-outline-variant shadow-sm">
      <h4 className="font-headline-md text-on-surface mb-4">Storage Map</h4>
      <div className="aspect-square bg-surface-container rounded-lg relative overflow-hidden">
        <div
          className="w-full h-full bg-cover bg-center"
          role="img"
          aria-label="A clean, medical-themed digital map showing a city layout with various hospital icons and blood bank storage hubs highlighted in deep maroon. The map uses a minimalist slate and white color scheme, emphasizing accessibility and professional logistics."
          style={{
            backgroundImage:
              "url('https://lh3.googleusercontent.com/aida-public/AB6AXuBD5OBuNyCTZ4ozXzb6YjmXEkpC3w23LbPFLdQB-WJV2O2e_bsGw3uUFtbYEEznBUI8rPNrIHB2gM7v4_wn1Evlg5xWiJZG7x5QnKRqVuetoOLSKW50VME1rCwrgm0Xq4W4JUqSL6Vm3PSHf-SAQm5Tn0aPlfdd31QpF3KV8jYH102EjXAPezi2T902R3XVq7fCYLr73yab264VZIhtViiB-NzT5xnAGHToDh_8xm6WT7TCM6C3VAP7U0E7qAVTjPx3NlnsKl2UIbQr')",
          }}
        />
        <div className="absolute inset-0 bg-primary/10 flex items-center justify-center">
          <button className="bg-white text-primary px-4 py-2 rounded-lg font-bold shadow-lg text-sm flex items-center gap-2">
            <span className="material-symbols-outlined text-lg">map</span>
            Expand Fleet View
          </button>
        </div>
      </div>
    </div>
  );
}
