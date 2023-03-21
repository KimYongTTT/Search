package com.example.search.api.model;

import lombok.*;

@Data
@Builder
public class PagingMetadata {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalElements;
}
