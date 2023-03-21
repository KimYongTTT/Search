package com.example.search.data.repository;

import com.example.search.data.entity.SearchCountBatch;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchCountBatchRepository extends JpaRepository<SearchCountBatch, Long> {
    Optional<SearchCountBatch> findTopByOrderByIdDesc();
}
