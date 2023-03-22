package com.example.search.data.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SearchCountBatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "started_datetime")
    private LocalDateTime startedDatetime;

    @Builder
    public SearchCountBatch(LocalDateTime startedDatetime) {
        this.startedDatetime = startedDatetime;
    }

    public static SearchCountBatch newBatch() {
        return SearchCountBatch.builder().startedDatetime(LocalDateTime.now()).build();
    }
}
