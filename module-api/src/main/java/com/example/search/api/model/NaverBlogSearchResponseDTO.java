package com.example.search.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@ToString
public class NaverBlogSearchResponseDTO {
    private String lastBuildDate;
    private Integer total;
    private Integer start;
    private Integer display;
    private List<Item> items;

    @Getter
    public static class Item {
        private String title;
        private String link;
        private String description;

        @JsonProperty(value = "bloggername")
        private String bloggerName;

        @JsonProperty(value = "bloggerLink")
        private String bloggerLink;

        @JsonProperty(value = "postdate")
        private String postDate;
    }
}
