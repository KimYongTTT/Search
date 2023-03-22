package com.example.search.api.utility;

import static com.example.search.api.constants.StatusCodeConstants.DEFAULT_FAIL_CODE;
import static com.example.search.api.constants.StatusCodeConstants.DEFAULT_SUCCESS_CODE;

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

    public static <T> ResponseEntity<BaseResponse<T>> createGetSuccessResponse(
            String statusCode, T data) {
        return new ResponseEntity<>(successResponse(statusCode, data), HttpStatus.OK);
    }

    public static <T> ResponseEntity<BasePagingResponse<T>> createPagingGetSuccessResponse(
            T data, PagingMetadata paging) {
        return new ResponseEntity<>(successPagingResponse(data, paging), HttpStatus.OK);
    }

    public static ResponseEntity<BaseResponse<String>> createFailResponse(
            String statusCode, String message, HttpStatus status) {
        return new ResponseEntity<>(failResponse(statusCode, message), status);
    }

    public static ResponseEntity<BaseResponse<String>> createFailResponse(
            String statusCode, String message) {
        return new ResponseEntity<>(failResponse(statusCode, message), HttpStatus.OK);
    }

    public static ResponseEntity<BaseResponse<String>> createFailResponse(
            String message, HttpStatus code) {
        return new ResponseEntity<>(failResponse(message), code);
    }

    public static <T> BaseResponse<T> successResponse(T data) {
        return BaseResponse.<T>builder()
                .isSuccess(true)
                .statusCode(DEFAULT_SUCCESS_CODE)
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> successResponse(String statusCode, T data) {
        return BaseResponse.<T>builder().isSuccess(true).statusCode(statusCode).data(data).build();
    }

    public static BaseResponse failResponse(String statusCode, String message) {
        return BaseResponse.builder().isSuccess(false).statusCode(statusCode).data(message).build();
    }

    public static BaseResponse failResponse(String message) {
        return BaseResponse.builder()
                .isSuccess(false)
                .statusCode(DEFAULT_FAIL_CODE)
                .data(message)
                .build();
    }

    public static <T> BasePagingResponse<T> successPagingResponse(T data, PagingMetadata paging) {
        return BasePagingResponse.<T>builder()
                .isSuccess(true)
                .statusCode(DEFAULT_SUCCESS_CODE)
                .data(data)
                .paging(paging)
                .build();
    }
}
