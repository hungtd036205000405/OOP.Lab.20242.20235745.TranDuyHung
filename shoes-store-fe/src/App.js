import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import ProductDetail from "./pages/ProductDetail";
import Login from "./pages/Login";
import Register from "./pages/Register"; // ✅ Thêm import
import Cart from "./pages/Cart";
import AdminDashboard from "./pages/AdminDashboard";
import PrivateRoute from "./components/PrivateRoute";
import AdminManageProduct from "./pages/AdminManageProduct"; 


function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        {/* Route public */}
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} /> {/* ✅ Thêm Register */}

        {/* Route private */}
        <Route path="/" element={<PrivateRoute><Home /></PrivateRoute>} />
        <Route path="/products/:id" element={<PrivateRoute><ProductDetail /></PrivateRoute>} />
        <Route path="/cart" element={<PrivateRoute><Cart /></PrivateRoute>} />
        <Route path="/admin" element={<PrivateRoute><AdminDashboard /></PrivateRoute>} />
        <Route path="/admin/manage-products" element={<PrivateRoute><AdminManageProduct /></PrivateRoute>} />
        
        {/* Redirect cho các route không tồn tại */}
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </Router>
  );
}

export default App;
