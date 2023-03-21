package com.example.search.api.controller;

import com.example.search.api.model.*;
import com.example.search.api.service.BlogSearchService;
import com.example.search.api.utility.ResponseUtility;
import com.example.search.data.service.SearchWordService;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SearchRestController {

    private final BlogSearchService blogSearchService;

    private final SearchWordService searchWordService;

    @GetMapping(path = "/v1/search/blog")
    public ResponseEntity<BasePagingResponse> searchBlogs(@Valid SearchOptions searchOptions) {
        searchOptions.setQuery(searchOptions.getQuery().trim());
        searchWordService.saveSearchWord(searchOptions.getQuery());
        PagingDataVO<BlogSearchResponseDTO> res = blogSearchService.searchByKeyword(searchOptions);

        return ResponseUtility.createPagingGetSuccessResponse(res.getData(), res.getPaging());
    }

    @GetMapping(path = "/v1/search/words")
    public ResponseEntity<BaseResponse> getTop10SearchWords() {
        return ResponseUtility.createGetSuccessResponse(null);
    }
}
