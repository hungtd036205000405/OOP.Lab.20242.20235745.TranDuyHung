import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Navbar.css';

function Navbar() {
  const [isOpen, setIsOpen] = useState(false);
  const [username, setUsername] = useState(null);
  const navigate = useNavigate();

  // Kiểm tra localStorage khi component mount
  useEffect(() => {
    const storedUser = localStorage.getItem("username");
    if (storedUser) {
      setUsername(storedUser);
    }
  }, []);

  // Listen cho storage events để update real-time
  useEffect(() => {
    const handleStorageChange = () => {
      const storedUser = localStorage.getItem("username");
      setUsername(storedUser);
    };

    // Listen cho storage events
    window.addEventListener('storage', handleStorageChange);
    
    return () => window.removeEventListener('storage', handleStorageChange);
  }, []);

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  const handleLogout = () => {
    localStorage.removeItem("token");      // Xoá token
    localStorage.removeItem("username");   // Xoá username
    localStorage.removeItem("userId");     // Xoá userId nếu có
    setUsername(null);
    navigate("/login"); // quay lại login
  };

  return (
    <nav className="navbar">
      <div className="nav-logo">
        <Link to="/">Shoes Store</Link>
      </div>
      <div className={`nav-links ${isOpen ? 'open' : ''}`}>
        <Link to="/" onClick={() => setIsOpen(false)}>Home</Link>
        <Link to="/cart" onClick={() => setIsOpen(false)}>Cart</Link>
        <Link to="/admin" onClick={() => setIsOpen(false)}>Admin</Link>
        
        {!username ? (
          <>
            <Link to="/login" onClick={() => setIsOpen(false)}>Login</Link>
            <Link to="/register" onClick={() => setIsOpen(false)}>Register</Link>
          </>
        ) : (
          <span className="nav-user">
            Xin chào, <strong>{username}</strong> | 
            <button onClick={handleLogout} className="btn-logout">Logout</button>
          </span>
        )}
      </div>
      <div className="nav-toggle" onClick={toggleMenu}>
        ☰
      </div>
    </nav>
  );
}

export default Navbar;