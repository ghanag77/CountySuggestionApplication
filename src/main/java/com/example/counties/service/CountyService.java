package com.example.counties.service;

import com.example.counties.model.County;
import com.example.counties.repository.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountyService {

    @Autowired
    private CountyRepository countyRepository;

    public List<County> suggestCounties(String query) {
        List<County> allCounties = countyRepository.findAll();
        return allCounties.stream()
                .filter(county -> county.getName().toLowerCase().contains(query.toLowerCase()) || county.getState().toLowerCase().equals(query.toLowerCase()))
                .limit(5)
                .collect(Collectors.toList());
    }
}
