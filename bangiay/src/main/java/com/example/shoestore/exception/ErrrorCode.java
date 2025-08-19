package com.example.shoestore.exception;

import lombok.Data;


public enum ErrrorCode {
    CART_NOT_FOUND(1007 ,"Giỏ hàng không tồn tại"),
    PRODUCT_NOT_IN_CART(1008, "Sản phẩm không có trong giỏ hàng"),
    UNAUTHENTICATED(1006, "Unauthenticated"),
    USER_NOT_EXISTED(1005, "User not existed"),
    INVALID_KEY(1001, "Uncategorized error"),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters"),
    USERNAME_INVALID(1003, "Username must be at least {min} characters"),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    USER_EXISTED(1002, "User existed")

    ;

    private int code;
    private String message;

    ErrrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}

