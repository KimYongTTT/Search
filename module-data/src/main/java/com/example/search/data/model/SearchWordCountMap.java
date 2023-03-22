package com.example.search.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchWordCountMap {
    private Long searchWordId;
    private Long count;
}
