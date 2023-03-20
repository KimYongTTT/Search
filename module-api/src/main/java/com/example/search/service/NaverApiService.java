package com.example.search.service;

import com.example.search.client.NaverClient;
import com.example.search.constants.SearchConstants;
import com.example.search.constants.SearchConstants.SortCondition;
import com.example.search.model.NaverBlogSearchResponseDTO;
import com.example.search.model.SearchOptions;
import feign.FeignException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NaverApiService {
    private final NaverClient naverClient;

    public NaverBlogSearchResponseDTO searchBlog(SearchOptions searchOptions) {
        final String encodedQuery = encodeQuery(searchOptions.getQuery());
        final String sort = createSortCondition(searchOptions.getSort());
        NaverBlogSearchResponseDTO searchResult = null;
        try {
            searchResult =
                    naverClient.searchBlogs(
                            encodedQuery,
                            sort,
                            searchOptions.getPageNumber(),
                            searchOptions.getPageSize());
        } catch (FeignException e) {
            log.error(e.getMessage());
        }
        return searchResult;
    }

    private String createSortCondition(SortCondition sort) {
        String sortString;
        switch (sort) {
            case RECENCY:
                sortString = SearchConstants.SEARCH_CONDITION_SORT_RECENCY_NAVER;
                break;
            case ACCURACY:
            default:
                sortString = SearchConstants.SEARCH_CONDITION_SORT_ACCURACY_NAVER;
        }
        return sortString;
    }

    private String encodeQuery(String query) {
        String encoded = query;
        try {
            encoded = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("query parameter encoding failed");
        }
        return encoded;
    }
}
