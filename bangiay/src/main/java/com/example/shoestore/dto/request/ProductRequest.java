package com.example.shoestore.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private double price;
    private String imageUrl;
    private int quantity;
    private String description;
    private Long categoryId; // chỉ gửi id category từ FE
}
