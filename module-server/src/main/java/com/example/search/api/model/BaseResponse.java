package com.example.search.api.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BaseResponse<T> {
    private Boolean isSuccess;
    private String message;
    private T data;
}
