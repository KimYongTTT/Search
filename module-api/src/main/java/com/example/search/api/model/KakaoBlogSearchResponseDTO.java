package com.example.search.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class KakaoBlogSearchResponseDTO {
    private Meta meta;
    private List<Document> documents;

    @Getter
    public static class Meta {
        @JsonProperty(value = "total_count")
        private Integer totalCount;

        @JsonProperty(value = "pageable_count")
        private Integer pageableCount;

        @JsonProperty(value = "is_end")
        private Boolean isEnd;
    }

    @Getter
    public static class Document {
        private String title;

        private String contents;

        private String url;

        @JsonProperty(value = "blogname")
        private String blogName;

        private String thumbnail;

        private OffsetDateTime datetime;
    }
}
