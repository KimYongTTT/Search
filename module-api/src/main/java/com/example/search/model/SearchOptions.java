package com.example.search.model;

import com.example.search.constants.SearchConstants.SortCondition;
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

    private SortCondition sort = SortCondition.ACCURACY;

    @Min(value = 1, message = "page number is less than min(1)")
    private int pageNumber = 1;

    @Max(value = 50, message = "page size is more than max(50)")
    @Min(value = 1, message = "page size is less than min(1)")
    private int pageSize = 10;
}
