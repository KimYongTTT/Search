package com.example.search.data.service;

import com.example.search.data.entity.SearchWord;
import com.example.search.data.model.SearchWordCountMap;
import com.example.search.data.model.SearchWordResponseDTO;
import com.example.search.data.repository.SearchWordRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchWordService {
    private final SearchWordRepository searchWordRepository;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public SearchWord saveSearchWord(final String keyword) {
        Optional<SearchWord> searchWord = searchWordRepository.findByKeyword(keyword);
        if (searchWord.isPresent()) {
            return searchWord.get();
        } else {
            SearchWord newSearchWord = SearchWord.newWord(keyword);
            return searchWordRepository.save(newSearchWord);
        }
    }

    @Transactional(readOnly = true)
    public List<SearchWordResponseDTO> getTop10SearchWords() {
        return searchWordRepository.findTop10ByOrderBySearchCountDesc().stream()
                .map(SearchWordResponseDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public Integer updateWordSearchCounts(List<SearchWordCountMap> searchCountsMap) {
        Integer afftectedRows = 0;
        for (SearchWordCountMap s : searchCountsMap) {
            afftectedRows +=
                    searchWordRepository.increaseWordSearchCount(s.getSearchWordId(), s.getCount());
        }
        return afftectedRows;
    }
}
