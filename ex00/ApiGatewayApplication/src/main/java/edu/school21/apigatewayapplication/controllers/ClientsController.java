package edu.school21.apigatewayapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientsController {

    @Autowired
    private RestTemplate template;

    @GetMapping("/{country_name}")
    public String getInfo(@PathVariable String country_name) {
        String requestUrl = "http://COVID/covid-management/countries/" + country_name;
        return template.getForObject(requestUrl, String.class);
    }

}