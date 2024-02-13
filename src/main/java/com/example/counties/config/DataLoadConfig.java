package com.example.counties.config;

import com.example.counties.model.County;
import com.example.counties.repository.CountyRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Configuration
public class DataLoadConfig {

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void loadData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<County> counties = mapper.readValue(resourceLoader.getResource("classpath:data.json").getInputStream(), new TypeReference<List<County>>() {});
        countyRepository.saveAll(counties);
    }
}
