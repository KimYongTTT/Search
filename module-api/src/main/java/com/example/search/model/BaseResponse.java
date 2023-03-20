package com.example.search.model;

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

    public static BaseResponse successResponse(Object data) {
        return BaseResponse.builder().isSuccess(true).message("").data(data).build();
    }

    public static BaseResponse failResponse(String message) {
        return BaseResponse.builder().isSuccess(false).message(message).build();
    }
}
