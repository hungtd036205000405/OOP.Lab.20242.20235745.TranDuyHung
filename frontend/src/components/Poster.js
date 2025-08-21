import { useEffect, useRef, useState } from "react";
import "./Poster.css";

function Poster() {
  // Hiệu ứng nghiêng theo chuột + slideshow
  const [rotate, setRotate] = useState({ x: 0, y: 0 });
  const wrapperRef = useRef(null);
  const heroImages = [
    "https://sdmntprwestus2.oaiusercontent.com/files/00000000-138c-61f8-9b5b-b8e55b4db52c/raw?se=2025-08-21T11%3A39%3A05Z&sp=r&sv=2024-08-04&sr=b&scid=6cac8e63-16bb-5eaa-ab2c-60db259433af&skoid=60f2aa1f-3685-43ee-be37-d8c8d08d5a64&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-08-20T17%3A59%3A12Z&ske=2025-08-21T17%3A59%3A12Z&sks=b&skv=2024-08-04&sig=keS9HcuUBehDrvzGrpEegK/lq%2B1y2MukanBN/blHoRM%3D",
    "https://sdmntprnorthcentralus.oaiusercontent.com/files/00000000-0074-622f-a3d8-b29a46962bf6/raw?se=2025-08-21T11%3A28%3A59Z&sp=r&sv=2024-08-04&sr=b&scid=39da5fba-21e8-5b80-ac9b-586fdae90a1c&skoid=60f2aa1f-3685-43ee-be37-d8c8d08d5a64&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-08-20T17%3A59%3A20Z&ske=2025-08-21T17%3A59%3A20Z&sks=b&skv=2024-08-04&sig=xQelF4IwIoMCSfBRECm0GI%2BC2jxO8aHHBUBHcHh8Ld8%3D",
    "https://sdmntprcentralus.oaiusercontent.com/files/00000000-e188-61f5-9227-bab00e2ce137/raw?se=2025-08-21T11%3A51%3A02Z&sp=r&sv=2024-08-04&sr=b&scid=5d06e544-bfd8-558e-99a6-89e953fa23d9&skoid=60f2aa1f-3685-43ee-be37-d8c8d08d5a64&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-08-20T21%3A53%3A37Z&ske=2025-08-21T21%3A53%3A37Z&sks=b&skv=2024-08-04&sig=I02Jkrq5PXqhT8shubPmZV0zReif4vqXl1t4fsRfrz4%3D"
  ];
  const [currentSlide, setCurrentSlide] = useState(0);
  const [paused, setPaused] = useState(false);

  const handleMouseMove = (e) => {
    const rect = wrapperRef.current?.getBoundingClientRect();
    if (!rect) return;
    const relativeX = e.clientX - rect.left;
    const centerX = rect.width / 2;
    const maxRotation = 10;
    const yRotation = ((relativeX - centerX) / centerX) * maxRotation;
    setRotate({ x: 0, y: yRotation });
  };

  const handleMouseLeave = () => {
    setRotate({ x: 0, y: 0 });
    setPaused(false);
  };
  const handleMouseEnter = () => setPaused(true);

  useEffect(() => {
    if (paused) return;
    const id = setInterval(() => {
      setCurrentSlide((i) => (i + 1) % heroImages.length);
    }, 1000);
    return () => clearInterval(id);
  }, [paused]);

  return (
    <section className="py-5 hero-section">
      <div className="container">
        <div className="row align-items-center">
          {/* Text content */}
          <div className="col-md-6 reveal-up">
            <span className="badge bg-danger mb-2 hero-badge">Bộ sưu tập mới 2025</span>
            <h1 className="display-5 fw-bold hero-title text-gradient">
              <span className="hero-line">Nâng tầm phong cách</span>
              <span className="hero-line">Vượt mọi giới hạn</span>
            </h1>
            <p className="text-muted mb-4">
              Khám phá bộ sưu tập giày thể thao mới nhất với công nghệ tiên tiến,
              thiết kế hiện đại và sự thoải mái vượt trội.
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
                      transform: `translate(-50%, -50%) rotateY(${rotate.y}deg) ${
                        idx === currentSlide ? "scale(1)" : "scale(0.98)"
                      }`,
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
  );
}

export default Poster;

