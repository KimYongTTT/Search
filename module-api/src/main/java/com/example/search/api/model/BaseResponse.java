package com.example.search.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@ToString
@SuperBuilder
public class BaseResponse<T> {
    private Boolean isSuccess;
    private String message;
    private T data;
}
