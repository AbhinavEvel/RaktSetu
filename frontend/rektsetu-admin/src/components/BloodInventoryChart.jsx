import { useEffect, useState } from "react";

const bloodGroups = [
  { label: "A+", height: 85 },
  { label: "A-", height: 60 },
  { label: "B+", height: 95 },
  { label: "B-", height: 30 },
  { label: "O+", height: 75 },
  { label: "O-", height: 45 },
  { label: "AB+", height: 40 },
  { label: "AB-", height: 15 },
];

export default function BloodInventoryChart() {
  const [view, setView] = useState("volume"); // "volume" | "percentage"
  const [animated, setAnimated] = useState(false);

  // Mirrors the original window.addEventListener('load', ...) bar-fill animation
  useEffect(() => {
    const timer = setTimeout(() => setAnimated(true), 200);
    return () => clearTimeout(timer);
  }, []);

  return (
    <div className="lg:col-span-2 bg-surface-container-lowest p-6 rounded-xl border border-outline-variant shadow-sm">
      <div className="flex justify-between items-center mb-8">
        <h4 className="font-headline-md text-on-surface">Blood Inventory Distribution</h4>
        <div className="flex gap-2">
          <button
            onClick={() => setView("volume")}
            className={`text-label-md px-3 py-1 rounded-full font-bold ${
              view === "volume" ? "bg-surface-variant" : "text-on-surface-variant"
            }`}
          >
            Volume (Units)
          </button>
          <button
            onClick={() => setView("percentage")}
            className={`text-label-md px-3 py-1 rounded-full font-bold ${
              view === "percentage" ? "bg-surface-variant" : "text-on-surface-variant"
            }`}
          >
            Percentage
          </button>
        </div>
      </div>

      <div className="flex items-end justify-between h-64 px-4 gap-4">
        {bloodGroups.map((group) => (
          <div key={group.label} className="flex flex-col items-center flex-1 gap-4 h-full">
            <div className="w-full bg-surface-container rounded-t-lg relative flex flex-col justify-end overflow-hidden h-full">
              <div
                className="blood-level-gradient w-full transition-all duration-1000"
                style={{ height: animated ? `${group.height}%` : "0%" }}
              />
            </div>
            <span className="font-label-md text-primary font-bold">{group.label}</span>
          </div>
        ))}
      </div>
    </div>
  );
}
