package com.example.shoestore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mỗi user có 1 giỏ hàng
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Liên kết với danh sách cart detail
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)//mappedBy = "cart" = nói với Hibernate rằng foreign key nằm ở bảng cart_detail, chứ không phải ở cart
    private List<CartDetail> cartDetails;
}
