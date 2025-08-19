package com.example.shoestore.dto.request;

import lombok.Data;

@Data
public class UpdateCartRequest {
    private Long productId;
    private int quantity;
}
