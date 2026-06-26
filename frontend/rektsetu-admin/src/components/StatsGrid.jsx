import StatCard from "./StatCard";

const stats = [
  {
    id: "units",
    icon: "inventory_2",
    iconBgClass: "bg-primary-fixed",
    iconColorClass: "text-primary",
    badge: <span className="text-green-600 font-bold text-label-md">+4.2%</span>,
    label: "Total Units Available",
    value: "2,482",
    valueColorClass: "text-primary",
  },
  {
    id: "requests",
    icon: "emergency",
    iconBgClass: "bg-secondary-fixed",
    iconColorClass: "text-secondary",
    badge: <span className="text-secondary font-bold text-label-md">High Priority</span>,
    label: "Pending Requests",
    value: "18",
    valueColorClass: "text-secondary",
  },
  {
    id: "donors",
    icon: "volunteer_activism",
    iconBgClass: "bg-tertiary-fixed",
    iconColorClass: "text-tertiary",
    badge: <span className="text-on-surface-variant text-label-md">Today</span>,
    label: "Recent Donors",
    value: "142",
    valueColorClass: "text-tertiary",
  },
  {
    id: "camps",
    icon: "favorite",
    iconBgClass: "bg-primary",
    iconColorClass: "text-on-primary",
    badge: <span className="text-on-surface-variant text-label-md">Active</span>,
    label: "Live Camps",
    value: "04",
    valueColorClass: "text-on-surface",
  },
];

export default function StatsGrid() {
  return (
    <div className="grid grid-cols-1 md:grid-cols-4 gap-6 mb-8">
      {stats.map((stat) => (
        <StatCard key={stat.id} {...stat} />
      ))}
    </div>
  );
}
