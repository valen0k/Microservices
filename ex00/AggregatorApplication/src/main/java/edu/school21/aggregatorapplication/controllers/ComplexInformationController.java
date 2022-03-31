package edu.school21.aggregatorapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/information_management/countries")
public class ComplexInformationController {

    @Autowired
    private RestTemplate template;

    @GetMapping("/{country_name}")
    public String getIngo(@PathVariable String country_name) {
        String covidUrl = "http://COVID/covid-management/countries/" + country_name;
        String countryUrl = "http://COUNTRIES/countries-management/countries/" + country_name;
        String res = "Covid -> " + template.getForObject(covidUrl, String.class) + "\nCountry -> " + template.getForObject(countryUrl, String.class);
        return res;
    }

}
