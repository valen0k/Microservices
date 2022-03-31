package edu.school21.countriesapplication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries-management/countries")
public class CountriesController {

    @GetMapping("/{country_name}")
    public String getCountryInfo(@PathVariable String country_name) {
        return country_name;
    }

}
