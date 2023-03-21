package com.example.search.api.model;

import java.util.List;
import lombok.*;

@Data
public class Paging<T> {
    private List<T> data;
    private PagingMetadata pagingMetadata;
}
