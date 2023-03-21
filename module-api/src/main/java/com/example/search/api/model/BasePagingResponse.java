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
}
