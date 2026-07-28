const alerts = [
  {
    id: 1,
    severity: "Critical",
    badgeClass: "bg-secondary text-on-secondary",
    time: "2 mins ago",
    title: "O- Negative Required",
    description: "City Hospital ICU - 4 units immediate requirement.",
    opacity: "",
  },
  {
    id: 2,
    severity: "Urgent",
    badgeClass: "bg-orange-600 text-white",
    time: "15 mins ago",
    title: "B+ Stock Low",
    description: "Inventory below safety threshold (15 units remaining).",
    opacity: "opacity-90",
  },
];

export default function EmergencyAlerts() {
  return (
    <div className="bg-primary text-on-primary p-6 rounded-xl shadow-lg flex flex-col relative overflow-hidden">
      <div className="absolute -right-10 -top-10 opacity-10">
        <span
          className="material-symbols-outlined text-[160px]"
          style={{ fontVariationSettings: "'FILL' 1" }}
        >
          notification_important
        </span>
      </div>

      <div className="flex items-center gap-3 mb-6 relative z-10">
        <span
          className="material-symbols-outlined text-secondary-fixed animate-pulse"
          style={{ fontVariationSettings: "'FILL' 1" }}
        >
          warning
        </span>
        <h4 className="font-headline-md">Emergency Alerts</h4>
      </div>

      <div className="space-y-4 relative z-10 flex-1">
        {alerts.map((alert) => (
          <div
            key={alert.id}
            className={`bg-primary-container p-4 rounded-lg border border-on-primary-container/20 ${alert.opacity}`}
          >
            <div className="flex justify-between items-start mb-2">
              <span className={`px-2 py-0.5 rounded text-[10px] font-bold uppercase ${alert.badgeClass}`}>
                {alert.severity}
              </span>
              <span className="text-xs opacity-70">{alert.time}</span>
            </div>
            <p className="font-bold text-body-md mb-1">{alert.title}</p>
            <p className="text-sm opacity-90">{alert.description}</p>
          </div>
        ))}
      </div>

      <button className="mt-6 w-full bg-on-primary text-primary py-3 rounded-lg font-bold hover:bg-opacity-90 transition-all relative z-10">
        Dispatch Logistics
      </button>
    </div>
  );
}
