package com.example.search.data.service;

import com.example.search.data.entity.SearchHistory;
import com.example.search.data.entity.SearchWord;
import com.example.search.data.model.SearchWordCountMap;
import com.example.search.data.repository.SearchHistoryRepository;
import com.example.search.data.repository.SearchWordRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchHistoryService {
    private final SearchWordRepository searchWordRepository;
    private final SearchHistoryRepository searchHistoryRepository;

    public void saveNewHistory(SearchWord searchWord) {
        searchWord = searchWordRepository.findByKeyword(searchWord.getKeyword()).get();
        try {
            searchHistoryRepository.saveAndFlush(SearchHistory.by(searchWord));
        } catch (DataIntegrityViolationException ex) {
            log.info("Auto Inc id is already in use, retry....");
            searchHistoryRepository.save(SearchHistory.by(searchWord));
        }
    }

    @Transactional(readOnly = true)
    public List<SearchWordCountMap> getHistoriesBetweenDate(
            final LocalDateTime from, final LocalDateTime to) {
        return searchHistoryRepository.findAllByCreatedDatetimeBetween(from, to);
    }
}
