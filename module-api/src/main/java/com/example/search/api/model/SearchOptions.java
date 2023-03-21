package com.example.search.api.model;

import com.example.search.api.constants.SearchConstants;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SearchOptions {
    @NotBlank(message = "query parameter is required")
    private String query;

    private SearchConstants.SortCondition sort = SearchConstants.SortCondition.ACCURACY;

    @Min(value = 1, message = "page number is less than min(1)")
    private int pageNumber = 1;

    @Max(value = 50, message = "page size is more than max(50)")
    @Min(value = 1, message = "page size is less than min(1)")
    private int pageSize = 10;
}
