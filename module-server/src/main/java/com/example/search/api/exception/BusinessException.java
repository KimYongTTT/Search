package com.example.search.api.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private String statusCode;

    public BusinessException(String statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
