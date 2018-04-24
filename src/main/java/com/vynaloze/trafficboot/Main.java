package com.vynaloze.trafficboot;

import com.vynaloze.trafficboot.controller.Hello;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Hello.class, args);
    }
}
