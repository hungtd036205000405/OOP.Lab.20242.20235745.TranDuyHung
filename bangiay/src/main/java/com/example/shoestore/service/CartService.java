package com.example.shoestore.service;

import com.example.shoestore.dto.request.AddToCartRequest;
import com.example.shoestore.dto.request.UpdateCartRequest;
import com.example.shoestore.dto.response.CartDetailResponse;
import com.example.shoestore.dto.response.CartResponse;
import com.example.shoestore.entity.Cart;
import com.example.shoestore.entity.CartDetail;
import com.example.shoestore.entity.Product;
import com.example.shoestore.exception.AppException;
import com.example.shoestore.exception.ErrrorCode;
import com.example.shoestore.repository.CartRepository;
import com.example.shoestore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    // ✅ Thêm sản phẩm vào giỏ
    public CartResponse addToCart(Long userId, AddToCartRequest request) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + request.getProductId()));

        if (!product.isInStock() || product.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Sản phẩm không đủ số lượng trong kho!");
        }

        Optional<CartDetail> existingDetail = cart.getCartDetails().stream()
                .filter(cd -> cd.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingDetail.isPresent()) {
            CartDetail detail = existingDetail.get();
            detail.setQuantity(detail.getQuantity() + request.getQuantity());
        } else {
            CartDetail detail = CartDetail.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.getQuantity())
                    .build();
            cart.getCartDetails().add(detail);
        }

        Cart savedCart = cartRepository.save(cart);
        return mapToResponse(savedCart);
    }

    // ✅ Lấy giỏ hàng
    public CartResponse getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));

        return mapToResponse(cart);
    }

    // ✅ Cập nhật số lượng sản phẩm trong giỏ
    public CartResponse updateCartItem(Long userId, UpdateCartRequest request) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));

        CartDetail cartDetail = cart.getCartDetails().stream()
                .filter(cd -> cd.getProduct().getId().equals(request.getProductId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart: " + request.getProductId()));

        if (request.getQuantity() <= 0) {
            // Nếu số lượng <= 0 thì coi như xoá sản phẩm khỏi giỏ
            cart.getCartDetails().remove(cartDetail);
        } else {
            cartDetail.setQuantity(request.getQuantity());
        }

        Cart savedCart = cartRepository.save(cart);
        return mapToResponse(savedCart);
    }

    // ✅ Xoá 1 sản phẩm khỏi giỏ
    public CartResponse removeCartItem(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrrorCode.CART_NOT_FOUND));

        boolean removed = cart.getCartDetails().removeIf(cd -> cd.getProduct().getId().equals(productId));

        if (!removed) {
            throw new AppException(ErrrorCode.PRODUCT_NOT_IN_CART);
        }

        return mapToResponse(cartRepository.save(cart));
    }

    // ✅ Xoá toàn bộ giỏ
    public CartResponse clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));

        cart.getCartDetails().clear();

        Cart savedCart = cartRepository.save(cart);
        return mapToResponse(savedCart);
    }

    // ✅ Helper method: map entity → response
    private CartResponse mapToResponse(Cart cart) {
        List<CartDetailResponse> detailResponses = cart.getCartDetails().stream()
                .map(cd -> new CartDetailResponse(
                        cd.getProduct().getId(),
                        cd.getProduct().getName(),
                        cd.getProduct().getPrice(),
                        cd.getQuantity()
                ))
                .collect(Collectors.toList());

        return CartResponse.builder()
                .cartId(cart.getId())
                .userid(cart.getUser().getId())
                .cartDetails(detailResponses)
                .build();
    }
}