package com.example.search.batch.task;

import com.example.search.data.entity.SearchCountBatch;
import com.example.search.data.model.SearchWordCountMap;
import com.example.search.data.service.SearchCountBatchService;
import com.example.search.data.service.SearchHistoryService;
import com.example.search.data.service.SearchWordService;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchCountBatchTask {
    private final SearchCountBatchService searchCountBatchService;
    private final SearchHistoryService searchHistoryService;
    private final SearchWordService searchWordService;

    @Scheduled(fixedRate = 60000)
    public void searchCountAggregateTask() {
        long batchStartTime = System.currentTimeMillis();
        log.info("searchCountAggregateTask batch start");

        SearchCountBatch prevBatch = searchCountBatchService.getRecentCompletedBatch();
        SearchCountBatch newBatch = searchCountBatchService.saveNew();
        final LocalDateTime newBatchStartedTime = newBatch.getStartedDatetime();
        final LocalDateTime prevBatchStartedTime =
                prevBatch == null
                        ? newBatchStartedTime.minus(1, ChronoUnit.MINUTES)
                        : prevBatch.getStartedDatetime();

        List<SearchWordCountMap> searchHistories =
                searchHistoryService.getHistoriesBetweenDate(
                        prevBatchStartedTime, newBatchStartedTime);
        log.info(searchHistories.toString());
        Integer afftectedRows = searchWordService.updateWordSearchCounts(searchHistories);

        log.info("{} words' search count is updated", afftectedRows);

        long batchEndTime = System.currentTimeMillis();
        log.info("searchCountAggregateTask batch end, elapsedTime : {} ", batchEndTime - batchStartTime);
    }
}
