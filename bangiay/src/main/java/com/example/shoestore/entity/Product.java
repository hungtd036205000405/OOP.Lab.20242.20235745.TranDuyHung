package com.example.shoestore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // hoặc AUTO
    private Long id;


    private String name;
    private Double price;
    private String imageUrl;

    // Thêm các trường mới
    @Column(nullable = false)// Không cho phép giá trị null
    private int quantity;  // Số lượng

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;  // Mô tả chi tiết sản phẩm


    @Column(nullable = false)
    private boolean inStock;  // Còn hàng hay không (tính từ quantity)
}
