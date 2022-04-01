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
//        String requestUrl = "http://AGGREGATOR/information_management/countries/" + country_name;

        String covidUrl = "http://COVID/covid-management/countries/" + country_name;
        String countryUrl = "http://COUNTRIES/countries-management/countries/" + country_name;

//        Map<String, String> map = new HashMap<>();


        String s1 = template.getForObject(covidUrl, String.class);
        String s2 = template.getForObject(countryUrl, String.class);

        JSONObject jsonObject = new JSONObject(s1);
        JSONObject jsonObject2 = new JSONObject(s2);
        JSONObject jsonObjectWRAP = new JSONObject();
        jsonObjectWRAP.put("covid", jsonObject);
        jsonObjectWRAP.put("countries", jsonObject2);

        return JSONObject.valueToString(jsonObjectWRAP);
    }

}
