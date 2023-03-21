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
public class SearchService {
    private final KakaoApiService kakaoApiService;

    private final NaverApiService naverApiService;

    public Paging<BlogSearchResponseDTO> searchByKeyword(SearchOptions searchOptions) {
        Paging<BlogSearchResponseDTO> searchResult;
        Paging<BlogSearchResponseDTO> kakaoBlogSearchResult = searchByKakao(searchOptions);

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

    private Paging<BlogSearchResponseDTO> searchByKakao(SearchOptions searchOptions) {
        KakaoBlogSearchResponseDTO kakaoBlogSearchResponseDTO =
                kakaoApiService.searchBlog(searchOptions);

        if (kakaoBlogSearchResponseDTO == null) return null;

        Paging<BlogSearchResponseDTO> pagingVO = new Paging<>();
        List<BlogSearchResponseDTO> data =
                kakaoBlogSearchResponseDTO.getDocuments().stream()
                        .map(BlogSearchResponseDTO::from)
                        .collect(Collectors.toList());
        PagingMetadata pagingMetadata =
                PagingMetadata.builder()
                        .pageNumber(searchOptions.getPageNumber())
                        .pageSize(searchOptions.getPageSize())
                        .totalElements(kakaoBlogSearchResponseDTO.getMeta().getTotalCount())
                        .build();

        pagingVO.setData(data);
        pagingVO.setPagingMetadata(pagingMetadata);

        return pagingVO;
    }

    private Paging<BlogSearchResponseDTO> searchByNaver(SearchOptions searchOptions) {
        NaverBlogSearchResponseDTO naverBlogSearchResponseDTO =
                naverApiService.searchBlog(searchOptions);

        if (naverBlogSearchResponseDTO == null) return null;

        Paging<BlogSearchResponseDTO> pagingVO = new Paging<>();
        List<BlogSearchResponseDTO> data =
                naverBlogSearchResponseDTO.getItems().stream()
                        .map(BlogSearchResponseDTO::from)
                        .collect(Collectors.toList());
        PagingMetadata pagingMetadata =
                PagingMetadata.builder()
                        .pageNumber(searchOptions.getPageNumber())
                        .pageSize(searchOptions.getPageSize())
                        .totalElements(naverBlogSearchResponseDTO.getTotal())
                        .build();

        pagingVO.setData(data);
        pagingVO.setPagingMetadata(pagingMetadata);

        return pagingVO;
    }
}
