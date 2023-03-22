package com.example.search.api.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BasePagingResponse<T> extends BaseResponse<T> {
    private PagingMetadata paging;
}
