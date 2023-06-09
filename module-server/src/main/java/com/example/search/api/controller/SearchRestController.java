package com.example.search.api.controller;

import com.example.search.api.model.*;
import com.example.search.api.service.SearchService;
import com.example.search.api.utility.ResponseUtility;
import com.example.search.data.entity.SearchWord;
import com.example.search.data.model.SearchWordResponseDTO;
import com.example.search.data.service.SearchHistoryService;
import com.example.search.data.service.SearchWordService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SearchRestController {

    private final SearchService searchService;

    private final SearchWordService searchWordService;

    private final SearchHistoryService searchHistoryService;

    @GetMapping(path = "/v1/search/blog")
    public ResponseEntity<BasePagingResponse<List<BlogSearchResponseDTO>>> searchBlogs(
            @Valid SearchOptions searchOptions) {
        final String keyword = searchOptions.getQuery().trim();
        SearchWord savedSearchWord = searchWordService.saveSearchWord(keyword);
        searchHistoryService.saveNewHistory(savedSearchWord);

        searchOptions.setQuery(keyword);
        Paging<BlogSearchResponseDTO> res = searchService.searchByKeyword(searchOptions);

        return ResponseUtility.createPagingGetSuccessResponse(
                res.getData(), res.getPagingMetadata());
    }

    @GetMapping(path = "/v1/search/words")
    public ResponseEntity<BaseResponse<List<SearchWordResponseDTO>>> getTop10SearchWords() {
        return ResponseUtility.createGetSuccessResponse(searchWordService.getTop10SearchWords());
    }
}
