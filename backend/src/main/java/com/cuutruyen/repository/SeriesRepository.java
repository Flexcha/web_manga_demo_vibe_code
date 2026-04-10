package com.cuutruyen.repository;

import com.cuutruyen.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Integer> {
    // Basic CRUD provided by JpaRepository
}
