package com.example.search.data.repository;

import com.example.search.data.entity.SearchWord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchWordRepository extends JpaRepository<SearchWord, Long> {
    List<SearchWord> findTop10ByOrderBySearchCountDesc();

    Boolean existsByKeyword(String keyword);
}
