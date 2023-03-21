package com.example.search.api.utility;

import com.example.search.api.model.BasePagingResponse;
import com.example.search.api.model.BaseResponse;
import com.example.search.api.model.PagingMetaVO;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ResponseUtility {

    public ResponseEntity<BaseResponse> createGetSuccessResponse(Object data) {
        return new ResponseEntity<>(BaseResponse.successResponse(data), HttpStatus.OK);
    }

    public ResponseEntity<BasePagingResponse> createPagingGetSuccessResponse(
            Object data, PagingMetaVO paging) {
        return new ResponseEntity<>(
                BasePagingResponse.successPagingResponse(data, paging), HttpStatus.OK);
    }

    public ResponseEntity<BaseResponse> createFailResponse(String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(BaseResponse.failResponse(message), httpStatus);
    }
}
