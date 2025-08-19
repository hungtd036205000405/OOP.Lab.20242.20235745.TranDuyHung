// src/pages/Cart.jsx
import { useEffect, useState } from "react";
import api from "../services/api";

function Cart() {
  const storedUserId = localStorage.getItem("userId"); // lấy userId từ localStorage
  const token = localStorage.getItem("token"); // lấy token từ localStorage

  const [cart, setCart] = useState(null);
  const [error, setError] = useState(null);

  // ép kiểu userId về số (Long)
  const numericUserId = storedUserId ? Number(storedUserId) : null;

  // ✅ Khai báo config chuẩn
  const config = {
    headers: {
      Authorization: token ? `Bearer ${token}` : "",
      "Content-Type": "application/json",
    },
  };

  // 👉 Lấy giỏ hàng
  const fetchCart = async () => {
    if (!numericUserId) return; // nếu chưa có userId thì không gọi API
    try {
      const res = await api.get(`/cart/${numericUserId}`, config);
      if (res.data && res.data.cartDetails) {
        setCart(res.data);
      } else {
        setCart({ cartDetails: [] });
      }
      setError(null);
    } catch (err) {
      console.error("Lỗi khi lấy giỏ hàng:", err.response?.data || err.message);
      setError("Không thể tải giỏ hàng. Vui lòng thử lại sau!");
    }
  };

  useEffect(() => {
    fetchCart();
  }, [numericUserId]);

  // 👉 Đặt hàng
  const placeOrder = async () => {
    try {
      const res = await api.post(`/orders/place/${numericUserId}`, {}, config);
      alert("🎉 Đặt hàng thành công!");
      console.log("Chi tiết đơn hàng:", res.data);
      fetchCart();
    } catch (err) {
      console.error("Lỗi khi đặt hàng:", err.response?.data || err.message);
      alert("❌ Đặt hàng thất bại!");
    }
  };

  // 👉 Tăng số lượng
  const increaseQuantity = async (productId, currentQty) => {
    try {
      await api.put(
        `/cart/${numericUserId}/update`,
        { productId, quantity: currentQty + 1 },
        config
      );
      fetchCart();
    } catch (err) {
      console.error("Lỗi khi tăng số lượng:", err);
    }
  };

  // 👉 Giảm số lượng
  const decreaseQuantity = async (productId, currentQty) => {
    if (currentQty > 1) {
      try {
        await api.put(
          `/cart/${numericUserId}/update`,
          { productId, quantity: currentQty - 1 },
          config
        );
        fetchCart();
      } catch (err) {
        console.error("Lỗi khi giảm số lượng:", err);
      }
    }
  };

  // 👉 Xóa sản phẩm
  const removeItem = async (productId) => {
    try {
      await api.delete(`/cart/${numericUserId}/remove/${productId}`, config);
      fetchCart();
    } catch (err) {
      console.error("Lỗi khi xóa sản phẩm:", err);
    }
  };

  // 👉 Xóa toàn bộ giỏ
  const clearCart = async () => {
    try {
      await api.delete(`/cart/${numericUserId}/clear`, config);
      fetchCart();
    } catch (err) {
      console.error("Lỗi khi xóa giỏ hàng:", err);
    }
  };

  // 👉 Render
  if (!numericUserId) {
    return (
      <p className="text-center mt-5 text-danger">
        Vui lòng đăng nhập để xem giỏ hàng!
      </p>
    );
  }

  if (!cart) return <p className="text-center mt-5">Đang tải giỏ hàng...</p>;
  if (error) return <p className="text-center mt-5 text-danger">{error}</p>;

  const total = cart.cartDetails.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0
  );

  return (
    <div className="container mt-5">
      <h2 className="mb-4 text-center">🛒 Giỏ hàng của bạn</h2>

      <div className="card shadow">
        <div className="card-body">
          <table className="table table-hover align-middle text-center">
            <thead className="table-dark">
              <tr>
                <th>Sản phẩm</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th>Tổng</th>
                <th>Thao tác</th>
              </tr>
            </thead>
            <tbody>
              {cart.cartDetails.map((item) => (
                <tr key={item.productId}>
                  <td className="text-start">
                    <b>{item.productName}</b>
                  </td>
                  <td>{item.price.toLocaleString()} VND</td>
                  <td>
                    <button
                      className="btn btn-sm btn-outline-secondary me-2"
                      onClick={() =>
                        decreaseQuantity(item.productId, item.quantity)
                      }
                    >
                      -
                    </button>
                    <span className="badge bg-primary">{item.quantity}</span>
                    <button
                      className="btn btn-sm btn-outline-secondary ms-2"
                      onClick={() =>
                        increaseQuantity(item.productId, item.quantity)
                      }
                    >
                      +
                    </button>
                  </td>
                  <td className="fw-bold text-success">
                    {(item.price * item.quantity).toLocaleString()} VND
                  </td>
                  <td>
                    <button
                      className="btn btn-sm btn-danger"
                      onClick={() => removeItem(item.productId)}
                    >
                      Xóa
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          <div className="d-flex justify-content-between align-items-center mt-3">
            <h4>
              Tổng cộng:{" "}
              <span className="text-danger">{total.toLocaleString()} VND</span>
            </h4>
            <div>
              <button
                className="btn btn-outline-danger me-3"
                onClick={clearCart}
              >
                Xóa giỏ hàng
              </button>
              <button className="btn btn-success btn-lg" onClick={placeOrder}>
                Đặt hàng ngay 🚀
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Cart;
