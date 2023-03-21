package com.example.search.api.service;

import com.example.search.api.model.PagingDataVO;
import com.example.search.api.model.SearchOptions;

public interface SearchService<T> {
    PagingDataVO<T> searchByKeyword(SearchOptions searchOptions);
}
