package com.example.shoestore.exception;

import lombok.Data;
@Data
public class AppException extends RuntimeException{
    private  ErrrorCode errorCode;
    public AppException(ErrrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
