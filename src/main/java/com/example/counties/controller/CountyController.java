package com.example.counties.controller;

import com.example.counties.model.County;
import com.example.counties.service.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/suggest")
public class CountyController {

    @Autowired
    private CountyService countyService;

    @GetMapping
    public List<County> suggestCounties(@RequestParam(value = "q", required = true) String q) {
        return countyService.suggestCounties(q);
    }
}
