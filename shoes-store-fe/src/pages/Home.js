import { useEffect, useState } from "react";
import api from "../services/api"; 
import ProductCard from "../components/ProductCard";

function Home() {
  const [products, setProducts] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const pageSize = 8; // số sản phẩm mỗi trang

  // Hàm gọi API lấy sản phẩm theo page
  const fetchProducts = (page = 0) => {
    api
      .get(`/products?page=${page}&size=${pageSize}`)
      .then((res) => {
        const result = res.data.result;
        setProducts(result.content); // danh sách sp trong content
        setTotalPages(result.totalPages);
        setCurrentPage(result.number);
      })
      .catch((err) => {
        console.error("Lỗi khi gọi API:", err);
      });
  };

  // Gọi API khi component load lần đầu
  useEffect(() => {
    fetchProducts(0);
  }, []);

  // Lọc sản phẩm theo searchTerm
  const filteredProducts = products.filter((product) =>
    product.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="container mt-4">
      <h2 className="mb-4">Danh sách sản phẩm</h2>

      {/* Ô tìm kiếm */}
      <input
        type="text"
        className="form-control mb-4"
        placeholder="Tìm kiếm sản phẩm..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />

      {/* Danh sách sản phẩm */}
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

      {/* Phân trang */}
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

          {Array.from({ length: totalPages }, (_, index) => (
            <li
              key={index}
              className={`page-item ${index === currentPage ? "active" : ""}`}
            >
              <button
                className="page-link"
                onClick={() => fetchProducts(index)}
              >
                {index + 1}
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
    </div>
  );
}

export default Home;
