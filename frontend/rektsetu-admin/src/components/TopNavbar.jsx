import { useState } from "react";

export default function TopNavbar() {
  const [searchQuery, setSearchQuery] = useState("");

  return (
    <header className="h-16 flex justify-between items-center px-gutter bg-surface border-b border-outline-variant sticky top-0 z-40 shadow-sm">
      <div className="flex items-center gap-4 flex-1">
        <div className="relative w-full max-w-md">
          <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-on-surface-variant">
            search
          </span>
          <input
            className="w-full pl-10 pr-4 py-2 bg-surface-container rounded-lg border-none focus:ring-2 focus:ring-primary text-body-md"
            placeholder="Search donors, requests, or stock..."
            type="text"
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
          />
        </div>
      </div>

      <div className="flex items-center gap-6">
        <button className="bg-secondary text-on-secondary px-6 py-2 rounded-lg font-bold text-label-md hover:opacity-90 active:scale-95 transition-all">
          Emergency Request
        </button>
        <div className="flex items-center gap-4">
          <button className="material-symbols-outlined text-on-surface-variant hover:text-primary transition-all">
            notifications
          </button>
          <div className="flex items-center gap-2 cursor-pointer hover:bg-surface-container px-2 py-1 rounded-lg transition-all">
            <span className="material-symbols-outlined text-on-surface-variant text-3xl">
              account_circle
            </span>
            <div className="hidden lg:block text-left">
              <p className="text-label-md font-bold text-on-surface leading-tight">Admin User</p>
              <p className="text-[10px] text-on-surface-variant uppercase font-bold tracking-tighter">
                Super Admin
              </p>
            </div>
          </div>
        </div>
      </div>
    </header>
  );
}
