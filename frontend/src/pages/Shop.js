import React, { useState, useEffect, useRef } from "react";
import "./Shop.css";
import ProductList from "../components/ProductList";

function Shop() {
  const [scrolled, setScrolled] = useState(0);
  const [activeSlide, setActiveSlide] = useState(0);
  const carouselRef = useRef(null);

  const heroSlides = [
  {
    id: 1,
    title: "TĂNG TỐC MỌI CUNG ĐƯỜNG",
    subtitle: "Giày thể thao siêu nhẹ với khả năng bứt phá tốc độ vượt trội",
    image: "https://sdmntprwestus3.oaiusercontent.com/files/00000000-66c8-61fd-893e-704a0a235d82/raw?se=2025-08-21T11%3A25%3A33Z&sp=r&sv=2024-08-04&sr=b&scid=7076f934-b660-519a-bfdf-c27ce03717a0&skoid=60f2aa1f-3685-43ee-be37-d8c8d08d5a64&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-08-20T21%3A52%3A13Z&ske=2025-08-21T21%3A52%3A13Z&sks=b&skv=2024-08-04&sig=HGxVZToLqdKxC4S9bmQXfKJ5ZsH19hXoxyUKpYAMymc%3D",
    
  },
  {
    id: 2,
    title: "ÊM MƯỢT TỪNG BƯỚC CHÂN",
    subtitle: "Công nghệ đệm tiên tiến cho cảm giác thoải mái suốt cả ngày",
    image: "https://sdmntprwestus3.oaiusercontent.com/files/00000000-1960-61fd-a804-1abcc82df12d/raw?se=2025-08-21T11%3A35%3A08Z&sp=r&sv=2024-08-04&sr=b&scid=a891412e-e758-5602-878c-89d711b201f8&skoid=60f2aa1f-3685-43ee-be37-d8c8d08d5a64&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-08-20T21%3A51%3A55Z&ske=2025-08-21T21%3A51%3A55Z&sks=b&skv=2024-08-04&sig=LE/prGt4jjuMD0SeHGVI7c2uRhExXkW9fjr7hXEmflo%3D",
    cta: "Khám phá"
  },
  {
    id: 3,
    title: "PHONG CÁCH KHÔNG GIỚI HẠN",
    subtitle: "Thiết kế thời thượng, tái hiện biểu tượng kinh điển cho 2025",
    image: "https://img.pikbest.com/origin/10/00/55/34mpIkbEsTI9M.jpg!w700wp",
    cta: "Xem bộ sưu tập"
  },
  {
    id: 4,
    title: "ĐỘ BỀN VƯỢT THỜI GIAN",
    subtitle: "Chất liệu cao cấp, đồng hành cùng bạn qua mọi thử thách",
    image: "https://sdmntprnorthcentralus.oaiusercontent.com/files/00000000-b934-622f-9232-6f2e6289f3ba/raw?se=2025-08-21T11%3A32%3A18Z&sp=r&sv=2024-08-04&sr=b&scid=19ec0c2f-076b-5cdb-9161-59d31bf85c28&skoid=60f2aa1f-3685-43ee-be37-d8c8d08d5a64&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-08-20T21%3A53%3A50Z&ske=2025-08-21T21%3A53%3A50Z&sks=b&skv=2024-08-04&sig=J3t4EvTHYXxrgsGuMMdW6FGDiluE62r/yILGMsK65rA%3D",
    cta: "Khám phá ngay"
  },
  {
    id: 5,
    title: "TỰ DO KHẲNG ĐỊNH BẢN THÂN",
    subtitle: "Gam màu độc đáo, thiết kế cá tính – đậm dấu ấn riêng",
    image: "https://cdn2.fptshop.com.vn/unsafe/Uploads/images/tin-tuc/177480/Originals/poster-giay-1.jpg",
    cta: "Mua sắm liền tay"
  }
];



  useEffect(() => {
    const handleScroll = () => {
      const scrollPercent = (window.scrollY / (document.documentElement.scrollHeight - window.innerHeight)) * 100;
      setScrolled(scrollPercent);
    };

    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  useEffect(() => {
    const timer = setInterval(() => {
      setActiveSlide((s) => (s + 1) % heroSlides.length);
    }, 4500);
    return () => clearInterval(timer);
  }, []);

  const handlePrev = () => setActiveSlide((s) => (s - 1 + heroSlides.length) % heroSlides.length);
  const handleNext = () => setActiveSlide((s) => (s + 1) % heroSlides.length);

  return (
    <div className="shop-container">
      {/* Progress bar */}
      <div 
        className="scroll-progress-bar" 
        style={{ width: `${scrolled}%` }}
      />

      {/* New Hero UI (replaces Poster) */}
      <header className="hero-new">
        <div className="hero-inner container">
          <div className="hero-left">
            <div className="label">Bứt phá thời trang – Dẫn đầu xu hướng</div>
            
            <h1 className="hero-new-title">Lumos Sneaker</h1>
            <p className="hero-lead">Khám phá đẳng cấp mới cùng Lumos Sneaker — nơi thiết kế tối giản gặp công nghệ hiện đại. Mỗi bước chân không chỉ là di chuyển, mà còn là tuyên ngôn phong cách. Hãy chọn ngay đôi giày khiến bạn khác biệt và dẫn đầu xu hướng.</p>
            <div className="hero-actions">
              <button className="cta primary">Mua ngay</button>
              <button className="cta ghost">Xem thêm</button>
            </div>
            <ul className="trust-list">
              <li>🚚 Freeship toàn quốc</li>
              <li>🔄 Đổi trả 30 ngày</li>
              <li>🏆 Cam kết chính hãng</li>
            </ul>
          </div>

          <div className="hero-right">
            <div className="carousel" ref={carouselRef}>
              {heroSlides.map((slide, idx) => (
                <div
                  key={slide.id}
                  className={`carousel-slide ${idx === activeSlide ? 'active' : ''}`}
                  style={{ backgroundImage: `linear-gradient(180deg, rgba(0,0,0,0.2), rgba(0,0,0,0.35)), url(${slide.image})` }}
                >
                  <div className="slide-info">
                    <h3>{slide.title}</h3>
                    <p>{slide.subtitle}</p>
                    <button className="cta small">{slide.cta}</button>
                  </div>
                </div>
              ))}
            </div>

            <div className="carousel-controls">
              <button className="nav prev" onClick={handlePrev} aria-label="Previous">‹</button>
              <div className="dots">
                {heroSlides.map((_, i) => (
                  <button key={i} className={`dot ${i === activeSlide ? 'active' : ''}`} onClick={() => setActiveSlide(i)} />
                ))}
              </div>
              <button className="nav next" onClick={handleNext} aria-label="Next">›</button>
            </div>
          </div>
        </div>
      </header>

      {/* Product List Section */}
      <section className="products-section">
        <div className="container">
          <ProductList />
        </div>
      </section>
    </div>
  );
}

export default Shop;
