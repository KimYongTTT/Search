package com.example.search.data.repository;

import com.example.search.data.entity.SearchWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchWordRepository extends JpaRepository<SearchWord, Long> {
    List<SearchWord> findTop10ByOrderBySearchCountDesc();

    Boolean existsByKeyword(String keyword);
}
