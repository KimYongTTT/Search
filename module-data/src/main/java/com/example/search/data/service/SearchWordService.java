package com.example.search.data.service;

import com.example.search.data.repository.SearchWordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchWordService {
    private final SearchWordRepository searchWordRepository;

    @Transactional
    public void saveSearchWord(final String searchWord) {}

    @Transactional(readOnly = true)
    public void getTop10SearchWords() {}
}
