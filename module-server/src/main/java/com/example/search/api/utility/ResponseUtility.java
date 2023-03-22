package com.example.search.api.utility;

import com.example.search.api.model.BasePagingResponse;
import com.example.search.api.model.BaseResponse;
import com.example.search.api.model.PagingMetadata;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ResponseUtility {

    public static <T> ResponseEntity<BaseResponse<T>> createGetSuccessResponse(T data) {
        return new ResponseEntity<>(successResponse(data), HttpStatus.OK);
    }

    public static ResponseEntity<BaseResponse> createGetSuccessResponse(String message) {
        return new ResponseEntity<>(successResponse(message), HttpStatus.OK);
    }

    public static <T> ResponseEntity<BasePagingResponse<T>> createPagingGetSuccessResponse(
            T data, PagingMetadata paging) {
        return new ResponseEntity<>(successPagingResponse(data, paging), HttpStatus.OK);
    }

    public static ResponseEntity<BaseResponse> createFailResponse(
            String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(failResponse(message), httpStatus);
    }

    public static <T> BaseResponse<T> successResponse(T data) {
        return BaseResponse.<T>builder().isSuccess(true).message("").data(data).build();
    }

    public static <T> BaseResponse<T> successResponse(String message) {
        return BaseResponse.<T>builder().isSuccess(true).message(message).build();
    }

    public static BaseResponse failResponse(String message) {
        return BaseResponse.builder().isSuccess(false).message(message).build();
    }

    public static <T> BasePagingResponse<T> successPagingResponse(T data, PagingMetadata paging) {
        return BasePagingResponse.<T>builder()
                .isSuccess(true)
                .message("")
                .data(data)
                .paging(paging)
                .build();
    }
}
