const donationRecords = [
  {
    id: "DR-8821",
    name: "Rahul Kapoor",
    initials: "RK",
    avatarBg: "bg-primary-fixed",
    avatarText: "text-primary",
    bloodGroup: "A+",
    location: "South Delhi Hub",
    status: "Completed",
    statusDot: "bg-green-500",
  },
  {
    id: "DR-8822",
    name: "Sarah Malik",
    initials: "SM",
    avatarBg: "bg-tertiary-fixed",
    avatarText: "text-tertiary",
    bloodGroup: "O-",
    location: "West Hub",
    status: "Completed",
    statusDot: "bg-green-500",
  },
  {
    id: "DR-8823",
    name: "Vikram Das",
    initials: "VD",
    avatarBg: "bg-primary-fixed",
    avatarText: "text-primary",
    bloodGroup: "B+",
    location: "Main Bank",
    status: "Processing",
    statusDot: "bg-orange-400",
  },
];

export default function DonationRecordsTable() {
  return (
    <div className="bg-surface-container-lowest p-6 rounded-xl border border-outline-variant shadow-sm lg:col-span-2">
      <div className="flex justify-between items-center mb-6">
        <h4 className="font-headline-md text-on-surface">Recent Donation Records</h4>
        <button className="text-primary font-bold text-label-md hover:underline">
          View All Records
        </button>
      </div>
      <div className="overflow-x-auto">
        <table className="w-full text-left">
          <thead>
            <tr className="border-b border-outline-variant">
              <th className="py-4 font-bold text-on-surface-variant text-label-md uppercase">Donor Name</th>
              <th className="py-4 font-bold text-on-surface-variant text-label-md uppercase">Blood Group</th>
              <th className="py-4 font-bold text-on-surface-variant text-label-md uppercase">Location</th>
              <th className="py-4 font-bold text-on-surface-variant text-label-md uppercase">Status</th>
              <th className="py-4 font-bold text-on-surface-variant text-label-md uppercase text-right">
                Action
              </th>
            </tr>
          </thead>
          <tbody className="divide-y divide-outline-variant/30">
            {donationRecords.map((record) => (
              <tr key={record.id} className="hover:bg-surface-container-low transition-colors">
                <td className="py-4 flex items-center gap-3">
                  <div
                    className={`w-8 h-8 rounded-full flex items-center justify-center font-bold text-xs ${record.avatarBg} ${record.avatarText}`}
                  >
                    {record.initials}
                  </div>
                  <div>
                    <p className="font-bold text-body-md">{record.name}</p>
                    <p className="text-xs text-on-surface-variant">ID: #{record.id}</p>
                  </div>
                </td>
                <td className="py-4">
                  <span className="bg-red-50 text-red-700 px-3 py-1 rounded-full font-bold text-xs border border-red-100">
                    {record.bloodGroup}
                  </span>
                </td>
                <td className="py-4 text-body-md text-on-surface-variant">{record.location}</td>
                <td className="py-4">
                  <div className="flex items-center gap-2">
                    <span className={`w-2 h-2 rounded-full ${record.statusDot}`} />
                    <span className="text-xs font-bold text-on-surface-variant">{record.status}</span>
                  </div>
                </td>
                <td className="py-4 text-right">
                  <button className="material-symbols-outlined text-on-surface-variant hover:text-primary">
                    more_vert
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
