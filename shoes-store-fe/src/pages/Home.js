import { useEffect, useRef, useState } from "react";
import api from "../services/api";
import ProductCard from "../components/ProductCard";
import "./Home.css"; // 👉 import CSS

function Home() {
  const [products, setProducts] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(false);

  const defaultCategories = [
    { id: 1, name: "Sneakers" },
    { id: 2, name: "Running Shoes" },
    { id: 3, name: "Casual Shoes" },
    { id: 4, name: "Boots" },
    { id: 5, name: "Formal Shoes" }
  ];

  const [advName, setAdvName] = useState("");
  const [minPrice, setMinPrice] = useState("");
  const [maxPrice, setMaxPrice] = useState("");
  const [categoryId, setCategoryId] = useState("");

  const pageSize = 8;

  const fetchProducts = (page = 0) => {
    api
      .get(`/products?page=${page}&size=${pageSize}`)
      .then((res) => {
        const result = res.data.result;
        setProducts(result.content);
        setTotalPages(result.totalPages);
        setCurrentPage(result.number);
      })
      .catch((err) => console.error("Lỗi khi gọi API:", err));
  };

  const fetchCategories = () => {
    api
      .get(`/categories`)
      .then((res) => setCategories(res.data.result || defaultCategories))
      .catch((err) => {
        console.error("Lỗi categories:", err);
        setCategories(defaultCategories);
      });
  };

  const handleAdvancedSearch = async () => {
    try {
      setLoading(true);
      const res = await api.get("/products/search", {
        params: {
          name: advName || undefined,
          minPrice: minPrice || undefined,
          maxPrice: maxPrice || undefined,
          categoryId: categoryId || undefined
        }
      });
      setProducts(res.data.result || []);
      setTotalPages(0);
      setCurrentPage(0);
    } catch (err) {
      console.error("Lỗi tìm kiếm:", err);
      setProducts([]);
    } finally {
      setLoading(false);
    }
  };

  const resetToOriginalList = () => {
    if (loading) return;
    setAdvName("");
    setMinPrice("");
    setMaxPrice("");
    setCategoryId("");
    setSearchTerm("");
    fetchProducts(0);
  };

  useEffect(() => {
    fetchProducts(0);
    fetchCategories();
    if (categories.length === 0) {
      setCategories(defaultCategories);
    }
  }, []);

  const filteredProducts = products.filter((p) =>
    p.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  // 🟢 Hiệu ứng xoay 3D cho hình giày
  const [rotate, setRotate] = useState({ x: 0, y: 0 });
  const wrapperRef = useRef(null);
  const heroImages = [
    "https://file.hstatic.net/200000355547/article/image1_48b9450172d846419178187db77489f3.jpg",
    "https://file.hstatic.net/1000230642/collection/3_da9a91027cd0488581c18e767bd6e453.jpg",
    "https://bluewind.vn/wp-content/uploads/2025/04/1.png",
    "https://png.pngtree.com/template/20220330/ourlarge/pngtree-nike-new-sports-shoes-e-commerce-taobao-poster-image_904559.jpg"
  ];
  const [currentSlide, setCurrentSlide] = useState(0);
  const [paused, setPaused] = useState(false);

  const handleMouseMove = (e) => {
    const rect = wrapperRef.current?.getBoundingClientRect();
    if (!rect) return;
    const relativeX = e.clientX - rect.left;
    const centerX = rect.width / 2;
    const maxRotation = 10; // độ nghiêng tối đa, chỉ theo trục Y
    const yRotation = ((relativeX - centerX) / centerX) * maxRotation; // trái(-) → phải(+)
    setRotate({ x: 0, y: yRotation });
  };

  const handleMouseLeave = () => {
    setRotate({ x: 0, y: 0 });
    setPaused(false);
  };
  const handleMouseEnter = () => setPaused(true);

  // Auto-advance slides every 1s when not paused
  useEffect(() => {
    if (paused) return;
    const id = setInterval(() => {
      setCurrentSlide((i) => (i + 1) % heroImages.length);
    }, 1000);
    return () => clearInterval(id);
  }, [paused]);

  return (
    <div>
      {/* 🟢 Hero Section */}
      <section className="py-5 hero-section">
        <div className="container">
          <div className="row align-items-center">
            {/* Text content */}
            <div className="col-md-6 reveal-up">
              <span className="badge bg-danger mb-2 hero-badge">Bộ sưu tập mới 2025</span>
              <h1 className="display-5 fw-bold hero-title">
                Nâng tầm phong cách <br />
                <span className="text-gradient">Vượt mọi giới hạn</span>
              </h1>
              <p className="text-muted mb-4">
                Khám phá bộ sưu tập giày thể thao mới nhất với công nghệ tiên
                tiến, thiết kế hiện đại và sự thoải mái vượt trội.
              </p>
              <div className="d-flex gap-3 mb-4">
                <button className="btn btn-danger btn-lg btn-cta btn-cta-primary">Mua ngay</button>
                <button className="btn btn-outline-dark btn-lg btn-cta btn-cta-secondary">
                  Khám phá bộ sưu tập
                </button>
              </div>
              <div className="d-flex gap-4">
                <div className="metric">
                  <h4 className="fw-bold">500+</h4>
                  <p className="text-muted">Mẫu giày</p>
                </div>
                <div className="metric">
                  <h4 className="fw-bold">100+</h4>
                  <p className="text-muted">Thương hiệu</p>
                </div>
                <div className="metric">
                  <h4 className="fw-bold">50k+</h4>
                  <p className="text-muted">Khách hàng</p>
                </div>
              </div>
            </div>

            {/* Image với hiệu ứng nghiêng + slideshow */}
            <div className="col-md-6 text-center reveal-up">
              <div
                className="shoe-wrapper float-anim"
                onMouseMove={handleMouseMove}
                onMouseEnter={handleMouseEnter}
                onMouseLeave={handleMouseLeave}
                ref={wrapperRef}
              >
                <div className="slide-stack">
                  {heroImages.map((img, idx) => (
                    <img
                      key={idx}
                      src={img}
                      alt={`Slide ${idx + 1}`}
                      className={`slide-image ${idx === currentSlide ? "active" : ""}`}
                      style={{
                        transform: `translate(-50%, -50%) rotateY(${rotate.y}deg) ${idx === currentSlide ? "scale(1)" : "scale(0.98)"}`
                      }}
                    />
                  ))}
                </div>
                <div className="ground-shadow" aria-hidden="true" />
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* 🟢 Danh sách sản phẩm */}
      <div className="container mt-5">
        <h2 className="mb-4 text-center">Sản phẩm nổi bật</h2>

        <input
          type="text"
          className="form-control mb-4"
          placeholder="Tìm kiếm nhanh theo tên..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />

        <div className="card p-3 mb-4 shadow-sm">
          <h5 className="mb-3">🔍 Tìm kiếm nâng cao</h5>
          <div className="row g-2">
            <div className="col-md-3">
              <input
                type="text"
                className="form-control"
                placeholder="Tên sản phẩm"
                value={advName}
                onChange={(e) => setAdvName(e.target.value)}
              />
            </div>
            <div className="col-md-2">
              <input
                type="number"
                className="form-control"
                placeholder="Giá từ"
                value={minPrice}
                onChange={(e) => setMinPrice(e.target.value)}
              />
            </div>
            <div className="col-md-2">
              <input
                type="number"
                className="form-control"
                placeholder="Giá đến"
                value={maxPrice}
                onChange={(e) => setMaxPrice(e.target.value)}
              />
            </div>
            <div className="col-md-3">
              <select
                className="form-select"
                value={categoryId}
                onChange={(e) => setCategoryId(e.target.value)}
              >
                <option value="">-- Chọn danh mục --</option>
                {categories.map((cat) => (
                  <option key={cat.id} value={cat.id}>
                    {cat.name}
                  </option>
                ))}
              </select>
            </div>
            <div className="col-md-2 d-flex gap-2">
              <button
                className="btn btn-primary w-100"
                onClick={handleAdvancedSearch}
                disabled={loading}
              >
                {loading ? "Đang tìm..." : "Tìm kiếm"}
              </button>
              <button
                className="btn btn-secondary w-100"
                onClick={resetToOriginalList}
                disabled={loading}
              >
                Reset
              </button>
            </div>
          </div>
        </div>

        <div className="row">
          {filteredProducts.length > 0 ? (
            filteredProducts.map((product) => (
              <div className="col-md-3 mb-4" key={product.id}>
                <ProductCard product={product} />
              </div>
            ))
          ) : (
            <p className="text-muted">Không tìm thấy sản phẩm nào.</p>
          )}
        </div>

        {totalPages > 1 && (
          <nav>
            <ul className="pagination justify-content-center">
              <li className={`page-item ${currentPage === 0 ? "disabled" : ""}`}>
                <button
                  className="page-link"
                  onClick={() => fetchProducts(currentPage - 1)}
                >
                  Trang trước
                </button>
              </li>
              {Array.from({ length: totalPages }, (_, i) => (
                <li
                  key={i}
                  className={`page-item ${i === currentPage ? "active" : ""}`}
                >
                  <button
                    className="page-link"
                    onClick={() => fetchProducts(i)}
                  >
                    {i + 1}
                  </button>
                </li>
              ))}
              <li
                className={`page-item ${
                  currentPage === totalPages - 1 ? "disabled" : ""
                }`}
              >
                <button
                  className="page-link"
                  onClick={() => fetchProducts(currentPage + 1)}
                >
                  Trang sau
                </button>
              </li>
            </ul>
          </nav>
        )}
      </div>
    </div>
  );
}

export default Home;
