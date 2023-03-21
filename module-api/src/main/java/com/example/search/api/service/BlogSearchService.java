package com.example.search.api.service;

import com.example.search.api.exception.BusinessException;
import com.example.search.api.model.*;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogSearchService implements SearchService<BlogSearchResponseDTO> {
    private final KakaoApiService kakaoApiService;

    private final NaverApiService naverApiService;

    @Override
    public PagingDataVO<BlogSearchResponseDTO> searchByKeyword(SearchOptions searchOptions) {
        PagingDataVO<BlogSearchResponseDTO> searchResult;
        PagingDataVO<BlogSearchResponseDTO> kakaoBlogSearchResult = searchByKakao(searchOptions);

        if (kakaoBlogSearchResult == null) {
            searchResult = searchByNaver(searchOptions);
        } else {
            searchResult = kakaoBlogSearchResult;
        }

        if (searchResult == null) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "There are no search results");
        }

        return searchResult;
    }

    private PagingDataVO<BlogSearchResponseDTO> searchByKakao(SearchOptions searchOptions) {
        KakaoBlogSearchResponseDTO kakaoBlogSearchResponseDTO =
                kakaoApiService.searchBlog(searchOptions);

        if (kakaoBlogSearchResponseDTO == null) return null;

        PagingDataVO<BlogSearchResponseDTO> pagingDataVO = new PagingDataVO<>();
        List<BlogSearchResponseDTO> data =
                kakaoBlogSearchResponseDTO.getDocuments().stream()
                        .map(BlogSearchResponseDTO::from)
                        .collect(Collectors.toList());
        PagingMetaVO pagingMetaVO =
                PagingMetaVO.builder()
                        .pageNumber(searchOptions.getPageNumber())
                        .pageSize(searchOptions.getPageSize())
                        .totalElements(kakaoBlogSearchResponseDTO.getMeta().getTotalCount())
                        .build();

        pagingDataVO.setData(data);
        pagingDataVO.setPaging(pagingMetaVO);

        return pagingDataVO;
    }

    private PagingDataVO<BlogSearchResponseDTO> searchByNaver(SearchOptions searchOptions) {
        NaverBlogSearchResponseDTO naverBlogSearchResponseDTO =
                naverApiService.searchBlog(searchOptions);

        if (naverBlogSearchResponseDTO == null) return null;

        PagingDataVO<BlogSearchResponseDTO> pagingDataVO = new PagingDataVO<>();
        List<BlogSearchResponseDTO> data =
                naverBlogSearchResponseDTO.getItems().stream()
                        .map(BlogSearchResponseDTO::from)
                        .collect(Collectors.toList());
        PagingMetaVO pagingMetaVO =
                PagingMetaVO.builder()
                        .pageNumber(searchOptions.getPageNumber())
                        .pageSize(searchOptions.getPageSize())
                        .totalElements(naverBlogSearchResponseDTO.getTotal())
                        .build();

        pagingDataVO.setData(data);
        pagingDataVO.setPaging(pagingMetaVO);

        return pagingDataVO;
    }
}
