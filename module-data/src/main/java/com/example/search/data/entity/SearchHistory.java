package com.example.search.data.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@NoArgsConstructor
public class SearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "search_word_id")
    private SearchWord searchWord;

    @CreationTimestamp
    @Column(name = "created_datetime")
    private LocalDateTime createdDatetime;

    @Builder
    public SearchHistory(SearchWord searchWord) {
        this.searchWord = searchWord;
    }

    public static SearchHistory by(SearchWord searchWord) {
        return SearchHistory.builder().searchWord(searchWord).build();
    }
}
