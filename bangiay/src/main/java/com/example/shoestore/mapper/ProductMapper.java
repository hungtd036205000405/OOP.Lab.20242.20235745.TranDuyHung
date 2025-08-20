package com.example.shoestore.mapper;

import com.example.shoestore.dto.response.CategoryResponse;
import com.example.shoestore.dto.response.ProductResponse;
import com.example.shoestore.entity.Category;
import com.example.shoestore.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .inStock(product.getQuantity() > 0)
                .category(product.getCategory() != null ? CategoryResponse.builder()
                        .id(product.getCategory().getId())
                        .name(product.getCategory().getName())
                        .description(product.getCategory().getDescription())
                        .build() : null) // 👉 Nếu category null thì trả về null
                .build();
    }

    public List<ProductResponse> toProductResponseList(List<Product> products) {
        return products.stream()
                .map(this::toProductResponse)
                .toList();
    }
}


