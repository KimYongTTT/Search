package com.example.search.service;

import com.example.search.model.PagingDataVO;
import com.example.search.model.SearchOptions;

public interface SearchService<T> {
    PagingDataVO<T> searchByKeyword(SearchOptions searchOptions);
}
