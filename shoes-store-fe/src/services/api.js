import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080", // đã thêm /identity
  headers: {
    "Content-Type": "application/json",
  },
});
api.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");

  // Chỉ thêm token nếu không phải api request login(lần trước gặp lỗi khi phương thức nào cũng thêm token)
  if (token && !config.url.includes("/auth/token")) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
});

export default api;
