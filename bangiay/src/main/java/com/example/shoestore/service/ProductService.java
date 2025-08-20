package com.example.shoestore.service;

import com.example.shoestore.dto.response.ProductResponse;
import com.example.shoestore.entity.Product;
import com.example.shoestore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // Hàm map entity -> response DTO
    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .inStock(product.getQuantity() > 0) // logic tự set inStock
                .build();
    }

    // Hàm phân trang
    public Page<ProductResponse> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);

        return productPage.map(this::mapToResponse);
    }

    // Lấy sản phẩm theo ID
    // Ví dụ: GET /products/{id}
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }


    private final com.example.shoestore.service.ProductMapper productMapper; // inject mapper
    // Lấy sản phẩm theo categoryId
    public List<ProductResponse> getByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(productMapper::toProductResponse) // dùng mapper
                .collect(Collectors.toList());
    }

    // Tìm kiếm sản phẩm theo tên, giá, categoryId
    public List<ProductResponse> search(String name, Double minPrice, Double maxPrice, Long categoryId) {
        return productRepository.searchProducts(name, minPrice, maxPrice, categoryId)
                .stream()
                .map(productMapper::toProductResponse) // dùng mapper
                .collect(Collectors.toList());
    }
}
