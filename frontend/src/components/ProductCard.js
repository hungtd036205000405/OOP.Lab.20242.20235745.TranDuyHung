// src/components/ProductCard.jsx
import { Link } from "react-router-dom";
import api from "../services/api";
import { useState, useEffect } from "react";

function ProductCard({ product }) {
  const [loading, setLoading] = useState(false);
  const [userId, setUserId] = useState(null); // State để quản lý userId

  // Lấy userId từ localStorage và chuyển đổi sang Long khi component mount
  useEffect(() => {
    const storedUserId = localStorage.getItem("userId");
    if (storedUserId) {
      setUserId(Number(storedUserId)); // Chuyển đổi từ String sang Number (Long)
    }
  }, []); // Chỉ chạy một lần khi component được mount

  const handleAddToCart = async () => {
    if (!userId || isNaN(userId)) {
      alert("⚠️ Bạn cần đăng nhập để thêm vào giỏ hàng!");
      return;
    }

    try {
      setLoading(true);
      const res = await api.post(`/cart/${userId}/add`, {
        productId: product.id,
        quantity: 1,
      });

      console.log("🛒 Giỏ hàng sau khi thêm:", res.data);
      alert(`✅ Đã thêm "${product.name}" vào giỏ hàng!`);
    } catch (err) {
      console.error("❌ Lỗi khi thêm vào giỏ:", err.response?.data || err);
      alert("Có lỗi xảy ra khi thêm sản phẩm vào giỏ.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="card mb-4 h-100 d-flex flex-column">
      <img
        src={product.imageUrl}
        className="card-img-top"
        alt={product.name}
        style={{
          objectFit: "cover",
          height: "200px",
          width: "100%",
        }}
      />
      <div className="card-body d-flex flex-column flex-grow-1">
        <h5 className="card-title">{product.name}</h5>
        <p className="card-text text-danger fw-bold">
          {product.price.toLocaleString()} VND
        </p>
        <div className="mt-auto d-flex flex-column gap-2">
          <Link
            to={`/products/${product.id}`}
            className="btn btn-primary w-100"
          >
            Xem chi tiết
          </Link>
          <button
            className="btn btn-success w-100"
            onClick={handleAddToCart}
            disabled={loading}
          >
            {loading ? "⏳ Đang thêm..." : "🛒 Thêm vào giỏ"}
          </button>
        </div>
      </div>
    </div>
  );
}

export default ProductCard;