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
        String nameQuery = "";
        String stateQuery = "";

        if (query.contains(",")) {
            String[] parts = query.split(",", 2);
            nameQuery = parts[0].trim().toLowerCase();
            if (parts.length > 1) {
                stateQuery = parts[1].trim().toLowerCase();
            }
        } else if (query.trim().length() == 2) {
            stateQuery = query.trim().toLowerCase();
        } else {
            nameQuery = query.trim().toLowerCase();
        }

        final String finalNameQuery = nameQuery;
        final String finalStateQuery = stateQuery;

        List<County> allCounties = countyRepository.findAll();
        return allCounties.stream()
                .filter(county -> matchesQuery(county, finalNameQuery, finalStateQuery))
                .limit(5)
                .collect(Collectors.toList());
    }

    private boolean matchesQuery(County county, String nameQuery, String stateQuery) {
        boolean matchesName = nameQuery.isEmpty() || county.getName().toLowerCase().contains(nameQuery);
        boolean matchesState = stateQuery.isEmpty() || county.getState().toLowerCase().equals(stateQuery);

        return matchesName && matchesState;
    }
}
