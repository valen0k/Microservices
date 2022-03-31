package edu.school21.covidapplication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/covid-management/countries")
public class CovidController {

    @GetMapping("/{country_name}")
    public String getCovidInfo(@PathVariable String country_name) {
        return country_name;
    }

}
