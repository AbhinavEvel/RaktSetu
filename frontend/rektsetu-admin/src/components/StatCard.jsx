export default function StatCard({ icon, iconBgClass, iconColorClass, badge, label, value, valueColorClass }) {
  return (
    <div className="bg-surface-container-lowest p-6 rounded-xl border border-outline-variant shadow-sm flex flex-col justify-between h-40">
      <div className="flex justify-between">
        <span className={`material-symbols-outlined p-2 rounded-lg ${iconColorClass} ${iconBgClass}`}>
          {icon}
        </span>
        {badge}
      </div>
      <div>
        <p className="text-on-surface-variant text-label-md font-bold uppercase">{label}</p>
        <h3 className={`font-headline-xl text-headline-xl ${valueColorClass}`}>{value}</h3>
      </div>
    </div>
  );
}
