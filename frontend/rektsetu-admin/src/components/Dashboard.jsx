import StatsGrid from "./StatsGrid";
import BloodInventoryChart from "./BloodInventoryChart";
import EmergencyAlerts from "./EmergencyAlerts";
import DonationRecordsTable from "./DonationRecordsTable";
import StockStatus from "./StockStatus";


export default function Dashboard() {
  return (
    <div className="p-gutter max-w-container-max mx-auto">
      <section className="mb-8">
        <h2 className="font-headline-lg text-headline-lg text-primary">System Overview</h2>
        <p className="text-on-surface-variant font-body-md">
          Real-time status of blood inventory and emergency operations.
        </p>
      </section>

      <StatsGrid />

      <div className="grid grid-cols-1 lg:grid-coSls-3 gap-6">
        <BloodInventoryChart />
        <EmergencyAlerts />
      </div>

      <div className="mt-8 grid grid-cols-1 lg:grid-cols-3 gap-6">
        <DonationRecordsTable />
        <div className="space-y-6">
          <StockStatus />
          
        </div>
      </div>
    </div>
  );
}
