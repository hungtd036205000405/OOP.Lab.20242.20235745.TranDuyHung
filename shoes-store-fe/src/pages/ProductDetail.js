import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../services/api";

function ProductDetail() {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [reviews, setReviews] = useState([]);
  const [newReview, setNewReview] = useState({ rating: 5, comment: "" });

  useEffect(() => {
    api.get(`/products/${id}`).then((res) => setProduct(res.data.result));
  }, [id]);

  const fetchReviews = async () => {
    try {
      const res = await api.get(`/reviews/product/${id}`);
      setReviews(res.data.result || []);
    } catch (error) {
      console.error("Lỗi lấy review:", error);
    }
  };

  useEffect(() => {
    fetchReviews();
  }, [id]);

  const handleSubmitReview = async (e) => {
    e.preventDefault();
    try {
      const userId = localStorage.getItem("userId");
      if (!userId) {
        alert("Bạn cần đăng nhập để đánh giá!");
        return;
      }

      const payload = {
        userId: parseInt(userId),
        productId: parseInt(id),
        rating: newReview.rating,
        comment: newReview.comment,
      };

      await api.post("/reviews", payload);
      setNewReview({ rating: 5, comment: "" });
      fetchReviews();
    } catch (error) {
      console.error("Lỗi gửi review:", error.response?.data || error);
    }
  };

  // 🛒 Hàm thêm vào giỏ
  const handleAddToCart = async () => {
    try {
      const userId = localStorage.getItem("userId");
      if (!userId) {
        alert("Bạn cần đăng nhập để thêm vào giỏ!");
        return;
      }

      const payload = {
        productId: parseInt(id),
        quantity: 1, // mặc định 1 sản phẩm
      };

      await api.post(`/cart/${userId}/add`, payload);
      alert("Đã thêm sản phẩm vào giỏ hàng!");
    } catch (error) {
      console.error("Lỗi thêm giỏ:", error.response?.data || error);
      alert("Có lỗi khi thêm vào giỏ hàng!");
    }
  };

  if (!product) return <p className="text-center mt-4">Đang tải...</p>;

  const totalReviews = reviews.length;
  const avgRating =
    totalReviews > 0
      ? (
          reviews.reduce((sum, r) => sum + r.rating, 0) / totalReviews
        ).toFixed(1)
      : 0;

  return (
    <div className="container mt-5">
      {/* Thông tin sản phẩm */}
      <div className="row mb-5">
        <div className="col-md-5">
          <img
            src={product.imageUrl}
            className="img-fluid rounded shadow-sm"
            alt={product.name}
          />
        </div>
        <div className="col-md-7 d-flex flex-column justify-content-center">
          <h2 className="fw-bold">{product.name}</h2>
          <p className="text-muted">{product.description}</p>
          <h3 className="text-danger fw-bold mb-3">${product.price}</h3>
          <button
            className="btn btn-primary btn-lg w-50"
            onClick={handleAddToCart}
          >
            🛒 Thêm vào giỏ
          </button>
        </div>
      </div>

      {/* Đánh giá */}
      <div className="card shadow-sm">
        <div className="card-body">
          <h4 className="mb-4">
            Đánh giá sản phẩm ({totalReviews}){" "}
            <span className="badge bg-warning text-dark">⭐ {avgRating}</span>
          </h4>

          {totalReviews > 0 ? (
            <ul className="list-group mb-4">
              {reviews.map((r) => (
                <li key={r.id} className="list-group-item">
                  <div className="d-flex justify-content-between">
                    <strong>{r.username}</strong>
                    <span className="text-warning">
                      {"⭐".repeat(r.rating)}
                    </span>
                  </div>
                  <p className="mb-1">{r.comment}</p>
                  <small className="text-muted">
                    {new Date(r.createdAt).toLocaleString("vi-VN")}
                  </small>
                </li>
              ))}
            </ul>
          ) : (
            <p className="text-muted">Chưa có đánh giá nào.</p>
          )}

          {/* Form gửi đánh giá */}
          <form onSubmit={handleSubmitReview}>
            <h5 className="mb-3">Gửi đánh giá của bạn</h5>
            <div className="mb-3">
              <label className="form-label">Chọn số sao</label>
              <select
                className="form-select w-auto"
                value={newReview.rating}
                onChange={(e) =>
                  setNewReview({ ...newReview, rating: Number(e.target.value) })
                }
              >
                {[1, 2, 3, 4, 5].map((s) => (
                  <option key={s} value={s}>
                    {s} sao
                  </option>
                ))}
              </select>
            </div>
            <div className="mb-3">
              <textarea
                className="form-control"
                placeholder="Nhập nhận xét..."
                value={newReview.comment}
                onChange={(e) =>
                  setNewReview({ ...newReview, comment: e.target.value })
                }
                required
              />
            </div>
            <button type="submit" className="btn btn-success">
              Gửi đánh giá
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default ProductDetail;
