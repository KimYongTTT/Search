package com.example.search.model;

import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagingDataVO<T> {
    private List<T> data;
    private PagingMetaVO paging;
}
