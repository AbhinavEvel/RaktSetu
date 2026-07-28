import { useState } from "react";

const navItems = [
  { icon: "dashboard", label: "Dashboard" },
  { icon: "bloodtype", label: "Inventory" },
  { icon: "group", label: "Donors" },
  { icon: "calendar_month", label: "Appointments" },
  { icon: "emergency_share", label: "Blood Requests" },
  { icon: "monitoring", label: "Analytics" },
];

export default function Sidebar() {
  const [activeItem, setActiveItem] = useState("Dashboard");

  return (
    <aside className="w-[280px] h-screen fixed left-0 top-0 bg-surface-container-lowest border-r border-outline-variant flex flex-col gap-4 py-6 z-50">
      <div className="px-6 flex items-center gap-3">
        <img
          alt="RaktSetu Logo"
          className="w-16 h-16 object-contain"
          src="https://lh3.googleusercontent.com/aida-public/AB6AXuBeqiShyj59d_ontOFwSq4xr_HVCy6dKA_NwxVx8MC5la9e4x2BC8HqZ_p-BHQRXfaZqiAnc3PM18f2LEPCarNNOuSXoCQixQQ8JsXNwsxAeVEndCnlTP9r1F8CEVrU95oFCF0QiVQ3ieqDP9EbesPUMSHegAuRSNKWNPhuWIenSJ0Bh6zBiy8Tt1cg9tV6GL5enpMmiLzLIOU9zDwt6zaYy_jzRHehS8ZSYvoqJ1wNqBzxWk4Sgthc-TpArDpWnkzIku8fJhdzKmfs"
        />
        <div>
          
          <p className="text-xs text-on-surface-variant uppercase tracking-wider font-semibold">
            Blood Bank Management
          </p>
        </div>
      </div>

      <nav className="flex-1 mt-6 px-4 space-y-1">
        {navItems.map((item) => {
          const isActive = activeItem === item.label;
          return (
            <a
              key={item.label}
              href="#"
              onClick={(e) => {
                e.preventDefault();
                setActiveItem(item.label);
              }}
              className={`flex items-center gap-3 px-4 py-3 transition-colors duration-200 ${
                isActive
                  ? "text-primary font-bold border-r-4 border-primary bg-primary-fixed"
                  : "text-on-surface-variant hover:bg-surface-container-high"
              }`}
            >
              <span className="material-symbols-outlined">{item.icon}</span>
              <span className="font-body-md">{item.label}</span>
            </a>
          );
        })}
      </nav>

      <div className="px-6 py-4">
        <button className="w-full bg-primary text-on-primary py-3 rounded-lg font-bold shadow-md hover:opacity-90 active:scale-95 transition-all">
          Schedule Donation
        </button>
      </div>

      <div className="px-4 border-t border-outline-variant pt-4">
        <a
          href="#"
          className="flex items-center gap-3 px-4 py-2 text-on-surface-variant hover:bg-surface-container-high transition-colors duration-200"
        >
          <span className="material-symbols-outlined">settings</span>
          <span className="font-body-md">Settings</span>
        </a>
        <a
          href="#"
          className="flex items-center gap-3 px-4 py-2 text-on-surface-variant hover:bg-surface-container-high transition-colors duration-200"
        >
          <span className="material-symbols-outlined">help</span>
          <span className="font-body-md">Support</span>
        </a>
      </div>
    </aside>
  );
}
