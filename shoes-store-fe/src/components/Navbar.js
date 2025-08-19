// src/components/Navbar.js
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar() {
  const [isOpen, setIsOpen] = useState(false);

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  return (
    <nav className="navbar">
      <div className="nav-logo">
        <Link to="/">Shoes Store</Link>
      </div>
      <div className={`nav-links ${isOpen ? 'open' : ''}`}>
        <Link to="/" onClick={() => setIsOpen(false)}>Home</Link>
        <Link to="/cart" onClick={() => setIsOpen(false)}>Cart</Link>
        <Link to="/login" onClick={() => setIsOpen(false)}>Login</Link>
        <Link to="/admin" onClick={() => setIsOpen(false)}>Admin</Link>
      </div>
      <div className="nav-toggle" onClick={toggleMenu}>
        ☰
      </div>
    </nav>
  );
}

export default Navbar;
