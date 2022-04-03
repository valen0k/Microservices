package edu.school21.countriesapplication.controllers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries-management/countries")
public class CountriesController {

    private final String COUNTRY_URL = "https://restcountries.com/v2/alpha/";

    @GetMapping("/{country_name}")
    public String getCountryInfo(@PathVariable String country_name) throws UnirestException {
        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.get(COUNTRY_URL + country_name).asJson();

        return jsonNodeHttpResponse.getBody().toString();
    }

}
