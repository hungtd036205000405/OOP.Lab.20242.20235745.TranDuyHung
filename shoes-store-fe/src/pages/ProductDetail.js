// ProductDetail.jsx
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import api from "../services/api";
import { Container, Row, Col, Button, Badge, Alert } from "react-bootstrap";
import { StarFill, CartPlus } from "react-bootstrap-icons";

function ProductDetail() {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [addedToCart, setAddedToCart] = useState(false);

  useEffect(() => {
    api
      .get(`/products/${id}`)
      .then((res) => {
        // Đảm bảo price là number, tránh lỗi khi API trả về string/null
        const fixedData = {
          ...res.data,
          price: res.data.price ? Number(res.data.price) : 0,
        };
        setProduct(fixedData);
      })
      .catch((err) => {
        console.error("Lỗi khi gọi API:", err);
        if (err.response?.status === 401) {
          setError("Vui lòng đăng nhập để xem sản phẩm");
        } else {
          setError("Không thể tải thông tin sản phẩm");
        }
      })
      .finally(() => {
        setLoading(false);
      });
  }, [id]);

  const handleAddToCart = () => {
    if (!product) return;
    console.log("Add to cart:", product.id);
    setAddedToCart(true);
    setTimeout(() => setAddedToCart(false), 3000);
  };

  if (loading) {
    return (
      <Container className="mt-5 text-center">
        <div className="spinner-border text-primary" role="status">
          <span className="visually-hidden">Loading...</span>
        </div>
        <p className="mt-2">Đang tải thông tin sản phẩm...</p>
      </Container>
    );
  }

  if (error) {
    return (
      <Container className="mt-5">
        <Alert variant="danger" className="text-center">
          {error}
        </Alert>
      </Container>
    );
  }

  if (!product) {
    return (
      <Container className="mt-5 text-center">
        <Alert variant="warning">Sản phẩm không tồn tại</Alert>
      </Container>
    );
  }

  return (
    <Container className="my-5">
      {addedToCart && (
        <Alert
          variant="success"
          onClose={() => setAddedToCart(false)}
          dismissible
        >
          Đã thêm {product.name} vào giỏ hàng!
        </Alert>
      )}

      <Row className="g-4">
        <Col md={6}>
          <div className="border rounded p-3 bg-light">
            <img
              src={product.imageUrl || "https://via.placeholder.com/500x500"}
              alt={product.name || "Sản phẩm"}
              className="img-fluid rounded"
              style={{
                maxHeight: "500px",
                width: "100%",
                objectFit: "contain",
              }}
            />
          </div>
        </Col>

        <Col md={6}>
          <div className="product-details">
            <h2 className="mb-3">{product.name || "Tên sản phẩm"}</h2>

            <div className="mb-3">
              <Badge bg={product.inStock ? "success" : "danger"} className="me-2">
                {product.inStock ? "Còn hàng" : "Hết hàng"}
              </Badge>
              <Badge bg="info">Số lượng: {product.quantity ?? 0}</Badge>
            </div>

            <div className="price-section mb-4">
              <h3 className="text-danger fw-bold">
                {product.price
                  ? product.price.toLocaleString("vi-VN") + "₫"
                  : "Liên hệ"}
              </h3>
              {product.price > 2000000 && (
                <small className="text-muted text-decoration-line-through">
                  {(product.price * 1.2).toLocaleString("vi-VN")}₫
                </small>
              )}
            </div>

            <div className="mb-4">
              <h5>Mô tả sản phẩm</h5>
              <p className="text-muted">
                {product.description || "Chưa có mô tả"}
              </p>
            </div>

            <div className="rating mb-4">
              <StarFill className="text-warning" />
              <StarFill className="text-warning" />
              <StarFill className="text-warning" />
              <StarFill className="text-warning" />
              <StarFill className="text-secondary" />
              <span className="ms-2">(15 đánh giá)</span>
            </div>

            <Button
              variant="success"
              size="lg"
              className="me-3"
              onClick={handleAddToCart}
              disabled={!product.inStock}
            >
              <CartPlus className="me-2" />
              Thêm vào giỏ hàng
            </Button>

            <Button variant="outline-primary" size="lg">
              Mua ngay
            </Button>
          </div>
        </Col>
      </Row>

      {/* Thông tin bổ sung */}
      <Row className="mt-5">
        <Col>
          <div className="border-top pt-4">
            <h4>Thông tin chi tiết</h4>
            <ul className="list-unstyled">
              <li>
                <strong>Thương hiệu:</strong>{" "}
                {product.name ? product.name.split(" ")[0] : "N/A"}
              </li>
              <li>
                <strong>Chất liệu:</strong> Da cao cấp
              </li>
              <li>
                <strong>Màu sắc:</strong> Trắng/Đen
              </li>
              <li>
                <strong>Bảo hành:</strong> 6 tháng
              </li>
            </ul>
          </div>
        </Col>
      </Row>
    </Container>
  );
}

export default ProductDetail;
