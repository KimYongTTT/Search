package com.example.search.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    private HttpStatus code;

    public BusinessException(HttpStatus code, String message) {
        super(message);
        this.code = code;
    };

    public HttpStatus getCode() {
        return this.code;
    }
}
