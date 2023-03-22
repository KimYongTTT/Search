package com.example.search.data.repository;

import com.example.search.data.entity.SearchWord;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SearchWordRepository extends JpaRepository<SearchWord, Long> {
    List<SearchWord> findTop10ByOrderBySearchCountDesc();

    Optional<SearchWord> findByKeyword(String keyword);

    @Modifying
    @Query(
            value =
                    "UPDATE SearchWord sw SET sw.searchCount = sw.searchCount + :inc WHERE sw.id = :id")
    Integer increaseWordSearchCount(@Param(value = "id") Long id, @Param(value = "inc") Long inc);
}
