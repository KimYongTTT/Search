package com.example.search.data.repository;

import com.example.search.data.entity.SearchHistory;
import com.example.search.data.model.SearchWordCountMap;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    @Query(
            value =
                    "SELECT new com.example.search.data.model.SearchWordCountMap(sh.searchWord.id, COUNT (sh)) FROM SearchHistory sh WHERE sh.createdDatetime BETWEEN :from AND :to GROUP BY sh.searchWord")
    List<SearchWordCountMap> findAllByCreatedDatetimeBetween(
            @Param(value = "from") LocalDateTime from, @Param(value = "to") LocalDateTime to);
}
