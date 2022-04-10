package edu.school21.apigatewayapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/information_management/countries/{country_name}/overview")
    public String getView() {
        return "index";
    }

}
