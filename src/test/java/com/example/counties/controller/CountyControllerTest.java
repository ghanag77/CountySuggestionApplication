package com.example.counties.controller;

import com.example.counties.model.County;
import com.example.counties.service.CountyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CountyController.class)
class CountyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountyService countyService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void suggestCounties_returnsCountySuggestions() throws Exception {
        // Given
        List<County> counties = Arrays.asList(new County("53015", "WA", "Cowlitz"));
        given(countyService.suggestCounties("Cowlitz")).willReturn(counties);

        // When & Then
        mockMvc.perform(get("/suggest?q=Cowlitz")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Cowlitz")));
    }

    // Additional tests can be written to cover different query inputs and edge cases
}
