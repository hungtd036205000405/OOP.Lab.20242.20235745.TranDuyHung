package com.example.shoestore.service;

import com.example.shoestore.entity.*;
import com.example.shoestore.enums.OrderStatus;
import com.example.shoestore.repository.CartRepository;
import com.example.shoestore.repository.OrderRepository;
import com.example.shoestore.repository.ProductRepository;
import com.example.shoestore.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository; // Dùng để lấy user nếu cần
    private final ProductRepository productRepository; // Lấy sản phẩm
    private final CartRepository cartRepository; // Lấy giỏ hàng của user

    @Transactional
    public Order placeOrder(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userId));
        Cart cart = user.getCart();

        if (cart == null || cart.getCartDetails().isEmpty()) {
            throw new RuntimeException("Giỏ hàng trống, không thể đặt hàng!");
        }

        Order order = Order.builder()
                .user(user)
                .createdAt(LocalDateTime.now())
                .status(OrderStatus.NEW)
                .build();

        var orderDetails = cart.getCartDetails().stream().map(cd -> {
            Product product = cd.getProduct();

            // Kiểm tra lại số lượng tồn kho
            if (product.getQuantity() < cd.getQuantity()) {
                throw new RuntimeException("Sản phẩm '" + product.getName() + "' không đủ số lượng!");
            }

            // Trừ stock
            product.setQuantity(product.getQuantity() - cd.getQuantity());
            product.setInStock(product.getQuantity() > 0);
            productRepository.save(product);

            return OrderDetail.builder()
                    .order(order)
                    .product(product)
                    .quantity(cd.getQuantity())
                    .price(product.getPrice())
                    .build();
        }).collect(Collectors.toList());

        order.setOrderDetails(orderDetails);

        Double total = orderDetails.stream()
                .mapToDouble(od -> od.getQuantity() * od.getPrice())
                .sum();
        order.setTotalPrice(total);

        Order savedOrder = orderRepository.save(order);

        // Clear cart
        cart.getCartDetails().clear();
        cartRepository.save(cart);

        return savedOrder;
    }
}