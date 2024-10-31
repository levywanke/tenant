package com.backend.pharmacy.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@EnableAutoConfiguration
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "index"; 
    }
}
