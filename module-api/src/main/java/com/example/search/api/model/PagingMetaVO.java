package com.example.search.api.model;

import lombok.*;

@Builder
@Data
@ToString
public class PagingMetaVO {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalElements;
}
