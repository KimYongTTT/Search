package com.example.search.api.model;

import com.example.search.api.constants.SearchConstants.SortCondition;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class SearchOptions {
    @NotBlank(message = "query parameter is required")
    @Size(min = 1, max = 30, message = "query parameter's length must be between 1 and 30")
    private String query;

    private SortCondition sort = SortCondition.ACCURACY;

    @Min(value = 1, message = "page number is less than min(1)")
    private int pageNumber = 1;

    @Max(value = 50, message = "page size is more than max(50)")
    @Min(value = 1, message = "page size is less than min(1)")
    private int pageSize = 10;
}
