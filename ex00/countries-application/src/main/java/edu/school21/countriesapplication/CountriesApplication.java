package edu.school21.countriesapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CountriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountriesApplication.class, args);
    }

}
