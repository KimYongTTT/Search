package com.example.search.data.service;

import com.example.search.data.entity.SearchHistory;
import com.example.search.data.entity.SearchWord;
import com.example.search.data.model.SearchWordCountMap;
import com.example.search.data.repository.SearchHistoryRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchHistoryService {
    private final SearchHistoryRepository searchHistoryRepository;

    @Transactional
    public void saveNewHistory(final SearchWord searchWord) {
        searchHistoryRepository.save(SearchHistory.by(searchWord));
    }

    @Transactional(readOnly = true)
    public List<SearchWordCountMap> getHistoriesBetweenDate(
            final LocalDateTime from, final LocalDateTime to) {
        return searchHistoryRepository.findAllByCreatedDatetimeBetween(from, to);
    }
}
