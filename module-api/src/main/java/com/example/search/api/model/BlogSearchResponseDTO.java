package com.example.search.api.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.*;

@Data
@Builder
public class BlogSearchResponseDTO {
    private String title;
    private String url;
    private LocalDate datetime;
    private String resultFrom;

    public static BlogSearchResponseDTO from(KakaoBlogSearchResponseDTO.Document document) {
        return BlogSearchResponseDTO.builder()
                .title(document.getTitle())
                .url(document.getUrl())
                .datetime(document.getDatetime().toLocalDate())
                .resultFrom("KAKAO")
                .build();
    }

    public static BlogSearchResponseDTO from(NaverBlogSearchResponseDTO.Item item) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return BlogSearchResponseDTO.builder()
                .title(item.getTitle())
                .url(item.getLink())
                .datetime(LocalDate.parse(item.getPostDate(), formatter))
                .resultFrom("NAVER")
                .build();
    }
}
