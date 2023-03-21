package com.example.search.data.repository;

import com.example.search.data.entity.SearchCountBatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SearchCountBatchRepository extends JpaRepository<SearchCountBatch, Long> {
    Optional<SearchCountBatch> findTopByOrderByIdDesc();
}
