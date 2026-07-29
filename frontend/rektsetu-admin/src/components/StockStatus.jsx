const stockLevels = [
  {
    id: "o-plus",
    group: "O+",
    label: "Safe Level",
    percentage: 85,
    percentColorClass: "text-green-600",
    barColorClass: "bg-green-500",
  },
  {
    id: "ab-minus",
    group: "AB-",
    label: "Low Stock",
    percentage: 15,
    percentColorClass: "text-secondary",
    barColorClass: "bg-secondary",
  },
];

export default function StockStatus() {
  return (
    <div className="bg-surface-container-lowest p-6 rounded-xl border border-outline-variant shadow-sm">
      <h4 className="font-headline-md text-on-surface mb-4">Stock Status</h4>
      <div className="space-y-4">
        {stockLevels.map((stock, index) => (
          <div key={stock.id} className={index > 0 ? "mt-6" : ""}>
            <div className="flex justify-between items-center">
              <div className="flex items-center gap-2">
                <div className="w-8 h-8 flex items-center justify-center bg-red-50 text-red-800 font-bold rounded-lg border border-red-100">
                  {stock.group}
                </div>
                <span className="text-body-md font-medium">{stock.label}</span>
              </div>
              <span className={`text-label-md font-bold ${stock.percentColorClass}`}>
                {stock.percentage}%
              </span>
            </div>
            <div className="w-full bg-surface-container h-2 rounded-full overflow-hidden mt-2">
              <div
                className={`h-full ${stock.barColorClass}`}
                style={{ width: `${stock.percentage}%` }}
              />
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
