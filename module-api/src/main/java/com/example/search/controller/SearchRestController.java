package com.example.search.controller;

import com.example.search.model.BaseResponse;
import com.example.search.model.BlogSearchResponseDTO;
import com.example.search.model.PagingDataVO;
import com.example.search.model.SearchOptions;
import com.example.search.service.BlogSearchService;
import com.example.search.utility.ResponseUtility;
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

    @GetMapping(path = "/v1/search/blog")
    public ResponseEntity<BaseResponse> searchBlogs(@Valid SearchOptions searchOptions) {
        searchOptions.setQuery(searchOptions.getQuery().trim());
        log.info(String.valueOf(searchOptions));

        PagingDataVO<BlogSearchResponseDTO> res = blogSearchService.searchByKeyword(searchOptions);

        return ResponseUtility.createPagingGetSuccessResponse(res.getData(), res.getPaging());
    }
}
