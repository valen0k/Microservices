package edu.school21.countriesapplication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/countries-management/countries")
public class CountriesController {

    private final String COUNTRY_URL = "https://restcountries.eu/";

    @GetMapping("/{country_name}")
    public String getCountryInfo(@PathVariable String country_name) {
        return country_name;
    }

}
