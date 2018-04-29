package com.vynaloze.trafficboot.model.exception;

public class StopNotFoundException extends RuntimeException {
    public StopNotFoundException(int id) {
        super("StopNotFound with id = " + id);
    }

    public StopNotFoundException(String name) {
        super("StopNotFound with name = " + name);
    }
}
