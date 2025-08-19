package com.example.shoestore.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddToCartRequest {
    private Long productId;  // ID sản phẩm
    private int quantity;    // Số lượng muốn thêm
}
