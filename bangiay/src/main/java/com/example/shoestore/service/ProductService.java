package com.example.shoestore.service;

import com.example.shoestore.dto.response.ProductResponse;
import com.example.shoestore.entity.User;
import com.example.shoestore.repository.ProductRepository;
import com.example.shoestore.dto.response.ProductResponse;
import com.example.shoestore.entity.Product;
import com.example.shoestore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }
    
    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
