package com.example.search.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@ToString
@SuperBuilder
public class BasePagingResponse<T> extends BaseResponse<T> {
    private PagingMetaVO paging;

    public static BasePagingResponse successPagingResponse(Object data, PagingMetaVO paging) {
        return BasePagingResponse.builder()
                .isSuccess(true)
                .message("")
                .data(data)
                .paging(paging)
                .build();
    }
}
