package com.vynaloze.trafficboot.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class Hello {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

}
