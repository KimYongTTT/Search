package com.example.search.data.service;

import com.example.search.data.entity.SearchWord;
import com.example.search.data.model.SearchWordResponseDTO;
import com.example.search.data.repository.SearchWordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchWordService {
    private final SearchWordRepository searchWordRepository;

    @Transactional
    public void saveSearchWord(final String searchWord) {
        if(!searchWordRepository.existsByKeyword(searchWord)) {
            SearchWord newSearchWord = SearchWord.newWord(searchWord);
            searchWordRepository.save(newSearchWord);
        }
    }

    @Transactional(readOnly = true)
    public List<SearchWordResponseDTO> getTop10SearchWords() {
        return searchWordRepository.findTop10ByOrderBySearchCountDesc().stream().map(SearchWordResponseDTO::from).collect(Collectors.toList());
    }
}
