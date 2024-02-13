package com.example.counties.repository;

import com.example.counties.model.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountyRepository extends JpaRepository<County, String> {
    // Custom query methods can be defined here
}
