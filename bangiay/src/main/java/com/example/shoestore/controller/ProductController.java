package com.example.shoestore.controller;

import com.example.shoestore.dto.response.ApiResponse;
import com.example.shoestore.dto.response.ProductResponse;
import com.example.shoestore.entity.Product;
import com.example.shoestore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // GET /products?page=0&size=10
    @GetMapping
    public ApiResponse<?> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.success(productService.getAllProducts(page, size));
    }


    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable Long id) {
        try {
            return ApiResponse.success(productService.getProductById(id));
        } catch (RuntimeException e) {
            return ApiResponse.error(410, "Product not found");
        }
    }
}
