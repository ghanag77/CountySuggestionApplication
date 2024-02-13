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
    void suggestCounties_returnsCountiesByPartialName() {
        List<County> mockCounties = Arrays.asList(
                new County("20035", "KS", "Cowley"),
                new County("53015", "WA", "Cowlitz")
        );
        when(countyRepository.findAll()).thenReturn(mockCounties);

        List<County> result = countyService.suggestCounties("cowl");

        assertThat(result, hasSize(2));
        verify(countyRepository, times(1)).findAll();
    }

    @Test
    void suggestCounties_returnsCountiesByState() {
        List<County> mockCounties = Arrays.asList(
                new County("53001", "WA", "Adams"),
                new County("53003", "WA", "Asotin"),
                new County("21035", "KY", "Cowley") // Assuming KY has a county named Cowley for this example
        );
        when(countyRepository.findAll()).thenReturn(mockCounties);

        List<County> result = countyService.suggestCounties("WA");

        assertThat(result, hasSize(2));
        assertThat(result, everyItem(hasProperty("state", equalTo("WA"))));
        verify(countyRepository, times(1)).findAll();
    }

    @Test
    void suggestCounties_returnsCountyByNameAndState() {
        List<County> mockCounties = Arrays.asList(
                new County("53015", "WA", "Cowlitz"),
                new County("20035", "KS", "Cowley") // Additional county for diversity
        );
        when(countyRepository.findAll()).thenReturn(mockCounties);

        List<County> result = countyService.suggestCounties("Cowlitz, WA");

        assertThat(result, hasSize(1));
        assertThat(result.get(0).getName(), equalTo("Cowlitz"));
        assertThat(result.get(0).getState(), equalTo("WA"));
        verify(countyRepository, times(1)).findAll();
    }

}
