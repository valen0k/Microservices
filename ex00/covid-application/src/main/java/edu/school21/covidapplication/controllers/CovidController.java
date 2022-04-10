package edu.school21.covidapplication.controllers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/covid-management/countries")
public class CovidController {

    private final String COVID_URL = "http://corona-api.com/countries/";

    @GetMapping("/{country_name}")
    public String getCovidInfo(@PathVariable String country_name) throws UnirestException {
        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.get(COVID_URL + country_name).asJson();

        return jsonNodeHttpResponse.getBody().toString();
    }

}
