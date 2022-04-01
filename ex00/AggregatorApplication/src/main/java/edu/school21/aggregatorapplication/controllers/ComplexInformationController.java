package edu.school21.aggregatorapplication.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/information_management/countries")
public class ComplexInformationController {

    @Autowired
    private RestTemplate template;

    @GetMapping("/{country_name}")
    public JSONObject getIngo(@PathVariable String country_name) {
        Map<String,String> res = new HashMap<>();

        String covidUrl = "http://COVID/covid-management/countries/" + country_name;
        JSONObject covidData = template.getForObject(covidUrl, JSONObject.class);


//        String countryUrl = "http://COUNTRIES/countries-management/countries/" + country_name;
        return covidData;
    }

}
