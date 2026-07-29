import Sidebar from "./components/Sidebar";
import TopNavbar from "./components/TopNavbar";
import Dashboard from "./components/Dashboard";
import "./styles/index.css";

export default function App() {
  return (
    <>
      <Sidebar />
      <main className="ml-[280px] min-h-screen">
        <TopNavbar />
        <Dashboard />
      </main>
    </>
  );
}
