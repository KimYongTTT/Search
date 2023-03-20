package com.example.search.service;

import com.example.search.client.KakaoClient;
import com.example.search.constants.SearchConstants;
import com.example.search.constants.SearchConstants.SortCondition;
import com.example.search.model.KakaoBlogSearchResponseDTO;
import com.example.search.model.SearchOptions;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoApiService {
    private final KakaoClient kakaoClient;

    public KakaoBlogSearchResponseDTO searchBlog(SearchOptions searchOptions) {
        final String sort = createSortCondition(searchOptions.getSort());
        KakaoBlogSearchResponseDTO searchResult = null;

        try {
            searchResult =
                    kakaoClient.searchBlogs(
                            searchOptions.getQuery(),
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
                sortString = SearchConstants.SEARCH_CONDITION_SORT_RECENCY_KAKAO;
                break;
            case ACCURACY:
            default:
                sortString = SearchConstants.SEARCH_CONDITION_SORT_ACCURACY_KAKAO;
        }
        return sortString;
    }
}
