package com.example.search.data.entity;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SearchWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "search_count")
    private Long searchCount;

    @Builder
    public SearchWord(String keyword, Long searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }

    public static SearchWord newWord(String keyword) {
        return SearchWord.builder().keyword(keyword).searchCount(1L).build();
    }
}
