package com.example.search.data.service;

import com.example.search.data.entity.SearchCountBatch;
import com.example.search.data.repository.SearchCountBatchRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchCountBatchService {
    private final SearchCountBatchRepository searchCountBatchRepository;

    @Transactional
    public SearchCountBatch saveNew() {
        SearchCountBatch searchCountBatch = SearchCountBatch.newBatch();
        return searchCountBatchRepository.save(searchCountBatch);
    }

    @Transactional(readOnly = true)
    public SearchCountBatch getRecentCompletedBatch() {
        Optional<SearchCountBatch> recentCompletedBatch =
                searchCountBatchRepository.findTopByOrderByIdDesc();
        if (recentCompletedBatch.isEmpty()) {
            return null;
        } else {
            return searchCountBatchRepository.findTopByOrderByIdDesc().get();
        }
    }
}
