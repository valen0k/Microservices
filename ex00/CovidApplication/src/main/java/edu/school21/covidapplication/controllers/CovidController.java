package edu.school21.covidapplication.controllers;

import jdk.nashorn.api.scripting.JSObject;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/covid-management/countries")
public class CovidController {

    private final String COVID_URL = "http://corona-api.com/countries/";

    @Autowired
    private RestTemplate template;

    @GetMapping("/{country_name}")
    public JSONObject getCovidInfo(@PathVariable String country_name) throws IOException {
//        JSONObject jsonObject = template.getForObject(COVID_URL + country_name, JSONObject.class);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(COVID_URL + country_name);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        JSONObject jsonObject = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
//        JSONObject data = jsonObject.getJSONObject("data");
        return jsonObject;
    }

}
