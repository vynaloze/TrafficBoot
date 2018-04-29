package com.vynaloze.trafficboot.controller;

import com.vynaloze.trafficboot.model.Stop;
import com.vynaloze.trafficboot.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private CoreService service;

    @GetMapping("/stops")
    List<Stop> getStops(@RequestParam(value = "name", defaultValue = "") String name) {
        return service.listStops(name);
    }

    @GetMapping("/stops/{id}")
    Stop getStop(@PathVariable("id") int id) {
        return service.getStop(id);
    }

    @PostMapping("/stops")
    ResponseEntity addStop(@RequestBody Stop stop) {
        service.addStop(stop);
        return new ResponseEntity(stop, HttpStatus.OK);
    }

    @DeleteMapping("/stops/{id}")
    ResponseEntity deleteStop(@PathVariable int id) {
        Stop stop = service.deleteStop(id);
        return new ResponseEntity(stop, HttpStatus.OK);
    }

}
