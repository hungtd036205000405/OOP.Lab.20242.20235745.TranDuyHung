package com.example.shoestore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CartDetailResponse {
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
}

