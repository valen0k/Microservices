package edu.school21.aggregatorapplication.controllers;

import org.json.JSONObject;
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
    public String getInfo(@PathVariable String country_name) {
        String covidUrl = "http://covid/covid-management/countries/" + country_name;
        String countryUrl = "http://countries/countries-management/countries/" + country_name;

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
