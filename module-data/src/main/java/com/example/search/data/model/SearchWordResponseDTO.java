package com.example.search.data.model;

import com.example.search.data.entity.SearchWord;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchWordResponseDTO {
    private String searchWord;

    private Long searchCount;

    public static SearchWordResponseDTO from(SearchWord searchWord) {
        return SearchWordResponseDTO.builder()
                .searchWord(searchWord.getKeyword())
                .searchCount(searchWord.getSearchCount())
                .build();
    }
}
