package edu.school21.apigatewayapplication.controllers;

import com.mashape.unirest.http.JsonNode;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ClientsController {

    @Autowired
    private RestTemplate template;

    @GetMapping("/{country_name}")
    @ResponseBody
    public String getInfo(@PathVariable String country_name) {
        String requestUrl = "http://AGGREGATOR/information_management/countries/" + country_name;
        return template.getForObject(requestUrl, String.class);
    }

}
