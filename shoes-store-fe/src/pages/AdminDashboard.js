import React, { useEffect, useState } from "react";
import {
  LineChart, Line, CartesianGrid, XAxis, YAxis, Tooltip, ResponsiveContainer,
  BarChart, Bar, PieChart, Pie, Cell, Legend
} from "recharts";

const Dashboard = () => {
  const [totalRevenue, setTotalRevenue] = useState(0);
  const [revenueData, setRevenueData] = useState([]);
  const [topProducts, setTopProducts] = useState([]);
  const [productRevenue, setProductRevenue] = useState([]);
  const [loading, setLoading] = useState(false);

  // Tham số tùy chỉnh
  const [dateRange, setDateRange] = useState({
    start: "2025-08-01",
    end: "2025-08-19"
  });
  const [topProductsLimit, setTopProductsLimit] = useState(5);
  const [productRevenueLimit, setProductRevenueLimit] = useState(5);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    setLoading(true);
    try {
      // 1. Tổng doanh thu
      const resTotal = await fetch("http://localhost:8080/admin/statistics/revenue/total");
      const totalData = await resTotal.json();
      setTotalRevenue(totalData);

      // 2. Doanh thu theo từng ngày
      const resDaily = await fetch(
        `http://localhost:8080/admin/statistics/revenue/daily?start=${dateRange.start}&end=${dateRange.end}`
      );
      const dailyData = await resDaily.json();
      
      // Kiểm tra nếu API trả về dữ liệu
      if (dailyData && dailyData.length > 0) {
        // Format dữ liệu cho biểu đồ
        const formattedData = dailyData.map(item => ({
          date: new Date(item.date).toLocaleDateString('vi-VN', { 
            month: '2-digit', 
            day: '2-digit' 
          }),
          revenue: item.revenue || 0
        }));
        setRevenueData(formattedData);
      } else {
        // Nếu không có dữ liệu, hiển thị thông báo
        setRevenueData([{
          date: `${dateRange.start} - ${dateRange.end}`,
          revenue: 0
        }]);
      }

      // 3. Top sản phẩm bán chạy
      const resTop = await fetch(
        `http://localhost:8080/admin/statistics/products/top-selling?limit=${topProductsLimit}`
      );
      const topData = await resTop.json();
      setTopProducts(topData);

      // 4. Doanh thu theo sản phẩm
      const resProductRevenue = await fetch(
        `http://localhost:8080/admin/statistics/products/revenue?limit=${productRevenueLimit}`
      );
      const productRevenueData = await resProductRevenue.json();
      setProductRevenue(productRevenueData);
    } catch (error) {
      console.error("Lỗi fetch API:", error);
      alert("Có lỗi xảy ra khi tải dữ liệu. Vui lòng thử lại!");
    } finally {
      setLoading(false);
    }
  };

  const handleDateRangeChange = (field, value) => {
    setDateRange(prev => ({
      ...prev,
      [field]: value
    }));
  };

  const handleRefresh = () => {
    fetchData();
  };

  const COLORS = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042", "#8884d8", "#82ca9d"];

  return (
    <div className="container-fluid my-4">
      <div className="row">
        <div className="col-12">
          <h2 className="mb-4">📊 Admin Dashboard - Thống kê doanh thu</h2>
          
          {/* Panel điều khiển */}
          <div className="card mb-4">
            <div className="card-header">
              <h5 className="card-title mb-0">📅 Tùy chỉnh thống kê</h5>
            </div>
            <div className="card-body">
              <div className="row">
                <div className="col-md-3 mb-3">
                  <label className="form-label">Ngày bắt đầu</label>
                  <input
                    type="date"
                    className="form-control"
                    value={dateRange.start}
                    onChange={(e) => handleDateRangeChange('start', e.target.value)}
                  />
                </div>
                
                <div className="col-md-3 mb-3">
                  <label className="form-label">Ngày kết thúc</label>
                  <input
                    type="date"
                    className="form-control"
                    value={dateRange.end}
                    onChange={(e) => handleDateRangeChange('end', e.target.value)}
                  />
                </div>

                <div className="col-md-3 mb-3">
                  <label className="form-label">
                    Số top sản phẩm bán chạy: <strong>{topProductsLimit}</strong>
                  </label>
                  <input
                    type="range"
                    className="form-range"
                    min="3"
                    max="20"
                    value={topProductsLimit}
                    onChange={(e) => setTopProductsLimit(parseInt(e.target.value))}
                  />
                  <div className="d-flex justify-content-between">
                    <small>3</small>
                    <small>20</small>
                  </div>
                </div>

                <div className="col-md-3 mb-3">
                  <label className="form-label">
                    Số sản phẩm doanh thu: <strong>{productRevenueLimit}</strong>
                  </label>
                  <input
                    type="range"
                    className="form-range"
                    min="3"
                    max="15"
                    value={productRevenueLimit}
                    onChange={(e) => setProductRevenueLimit(parseInt(e.target.value))}
                  />
                  <div className="d-flex justify-content-between">
                    <small>3</small>
                    <small>15</small>
                  </div>
                </div>
              </div>

              <div className="row">
                <div className="col-12">
                  <button
                    onClick={handleRefresh}
                    disabled={loading}
                    className={`btn ${loading ? 'btn-secondary' : 'btn-primary'}`}
                  >
                    <span className="me-2">🔄</span>
                    {loading ? 'Đang tải dữ liệu...' : 'Cập nhật thống kê'}
                  </button>
                </div>
              </div>
            </div>
          </div>

          {/* Thông tin tổng quan */}
          <div className="row mb-4">
            <div className="col-md-3 mb-3">
              <div className="card text-center shadow-sm border-success">
                <div className="card-body">
                  <div className="d-flex justify-content-between align-items-center">
                    <div>
                      <h6 className="card-title text-muted">Tổng doanh thu</h6>
                      <h4 className="text-success mb-0">
                        {totalRevenue.toLocaleString()} đ
                      </h4>
                    </div>
                    <span style={{fontSize: '2rem'}}>💰</span>
                  </div>
                </div>
              </div>
            </div>

            <div className="col-md-3 mb-3">
              <div className="card text-center shadow-sm border-info">
                <div className="card-body">
                  <div className="d-flex justify-content-between align-items-center">
                    <div>
                      <h6 className="card-title text-muted">Khoảng thời gian</h6>
                      <small className="text-info">
                        {dateRange.start} - {dateRange.end}
                      </small>
                    </div>
                    <span style={{fontSize: '2rem'}}>📅</span>
                  </div>
                </div>
              </div>
            </div>

            <div className="col-md-3 mb-3">
              <div className="card text-center shadow-sm border-warning">
                <div className="card-body">
                  <div className="d-flex justify-content-between align-items-center">
                    <div>
                      <h6 className="card-title text-muted">Top sản phẩm</h6>
                      <h4 className="text-warning mb-0">{topProductsLimit}</h4>
                    </div>
                    <span style={{fontSize: '2rem'}}>📈</span>
                  </div>
                </div>
              </div>
            </div>

            <div className="col-md-3 mb-3">
              <div className="card text-center shadow-sm border-danger">
                <div className="card-body">
                  <div className="d-flex justify-content-between align-items-center">
                    <div>
                      <h6 className="card-title text-muted">Sản phẩm doanh thu</h6>
                      <h4 className="text-danger mb-0">{productRevenueLimit}</h4>
                    </div>
                    <span style={{fontSize: '2rem'}}>📦</span>
                  </div>
                </div>
              </div>
            </div>
          </div>

          {/* Biểu đồ doanh thu theo khoảng ngày */}
          <div className="row mb-4">
            <div className="col-md-6 mb-3">
              <div className="card shadow-sm">
                <div className="card-body">
                  <h5 className="card-title">📈 Doanh thu theo từng ngày</h5>
                  {revenueData.length > 0 && revenueData[0].revenue > 0 ? (
                    <ResponsiveContainer width="100%" height={300}>
                      <LineChart data={revenueData}>
                        <CartesianGrid strokeDasharray="3 3" />
                        <XAxis 
                          dataKey="date" 
                          angle={-45}
                          textAnchor="end"
                          height={70}
                        />
                        <YAxis />
                        <Tooltip 
                          formatter={(value) => [`${value.toLocaleString()} đ`, 'Doanh thu']} 
                        />
                        <Line 
                          type="monotone" 
                          dataKey="revenue" 
                          stroke="#0d6efd" 
                          strokeWidth={3}
                          dot={{ fill: '#0d6efd', strokeWidth: 2, r: 4 }}
                          activeDot={{ r: 6, stroke: '#0d6efd', strokeWidth: 2 }}
                        />
                      </LineChart>
                    </ResponsiveContainer>
                  ) : (
                    <div className="text-center py-5">
                      <p className="text-muted">Không có dữ liệu doanh thu trong khoảng thời gian này</p>
                      <small className="text-secondary">
                        Từ {dateRange.start} đến {dateRange.end}
                      </small>
                    </div>
                  )}
                </div>
              </div>
            </div>

            {/* Top sản phẩm bán chạy */}
            <div className="col-md-6 mb-3">
              <div className="card shadow-sm">
                <div className="card-body">
                  <h5 className="card-title">🏆 Top {topProductsLimit} sản phẩm bán chạy</h5>
                  <ResponsiveContainer width="100%" height={300}>
                    <BarChart data={topProducts}>
                      <CartesianGrid strokeDasharray="3 3" />
                      <XAxis 
                        dataKey="productName" 
                        angle={-45}
                        textAnchor="end"
                        height={100}
                      />
                      <YAxis />
                      <Tooltip />
                      <Bar dataKey="totalSold" fill="#198754" />
                    </BarChart>
                  </ResponsiveContainer>
                </div>
              </div>
            </div>
          </div>

          {/* Biểu đồ tròn doanh thu theo sản phẩm */}
          <div className="row mb-4">
            <div className="col-12">
              <div className="card shadow-sm">
                <div className="card-body">
                  <h5 className="card-title">🥧 Phân bổ doanh thu theo {productRevenueLimit} sản phẩm hàng đầu</h5>
                  <div className="row">
                    <div className="col-md-6">
                      <ResponsiveContainer width="100%" height={400}>
                        <PieChart>
                          <Pie
                            data={productRevenue}
                            dataKey="totalSold"
                            nameKey="productName"
                            cx="50%"
                            cy="50%"
                            outerRadius={120}
                            fill="#8884d8"
                            label={({percent}) => `${(percent * 100).toFixed(1)}%`}
                          >
                            {productRevenue.map((entry, index) => (
                              <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                            ))}
                          </Pie>
                          <Tooltip 
                            formatter={(value) => [`${value.toLocaleString()}`, 'Số lượng bán']} 
                          />
                          <Legend />
                        </PieChart>
                      </ResponsiveContainer>
                    </div>
                    
                    <div className="col-md-6">
                      <div className="mt-3">
                        <h6>Chi tiết sản phẩm:</h6>
                        {productRevenue.map((product, index) => (
                          <div key={index} className="d-flex justify-content-between align-items-center p-2 mb-2 bg-light rounded">
                            <div className="d-flex align-items-center">
                              <div 
                                style={{
                                  width: '16px',
                                  height: '16px',
                                  borderRadius: '50%',
                                  backgroundColor: COLORS[index % COLORS.length],
                                  marginRight: '10px'
                                }}
                              />
                              <span className="fw-medium">{product.productName}</span>
                            </div>
                            <span className="fw-bold text-primary">
                              {product.totalSold?.toLocaleString()} sản phẩm
                            </span>
                          </div>
                        ))}
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          {loading && (
            <div className="text-center py-4">
              <div className="spinner-border text-primary" role="status">
                <span className="visually-hidden">Đang tải...</span>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default Dashboard;