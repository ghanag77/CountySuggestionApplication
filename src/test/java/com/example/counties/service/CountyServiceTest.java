package com.example.counties.service;

import com.example.counties.model.County;
import com.example.counties.repository.CountyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

class CountyServiceTest {

    @Mock
    private CountyRepository countyRepository;

    @InjectMocks
    private CountyService countyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void suggestCounties_returnsFilteredCounties() {
        // Given
        List<County> mockCounties = Arrays.asList(
                new County("53015", "WA", "Cowlitz"),
                new County("20035", "KS", "Cowley")
        );
        when(countyRepository.findAll()).thenReturn(mockCounties);

        // When
        List<County> result = countyService.suggestCounties("cowl");

        // Then
        assertThat(result, hasSize(2));
        verify(countyRepository, times(1)).findAll();
    }

}
