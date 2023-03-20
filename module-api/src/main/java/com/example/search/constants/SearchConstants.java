package com.example.search.constants;

public class SearchConstants {
    public static final String SEARCH_CONDITION_SORT_ACCURACY_KAKAO = "accuracy";
    public static final String SEARCH_CONDITION_SORT_RECENCY_KAKAO = "recency";

    public static final String SEARCH_CONDITION_SORT_ACCURACY_NAVER = "sim";
    public static final String SEARCH_CONDITION_SORT_RECENCY_NAVER = "date";

    public enum SortCondition {
        ACCURACY,
        RECENCY
    }
}
