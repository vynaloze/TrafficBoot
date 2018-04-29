package com.vynaloze.trafficboot.model.exception;

public class DuplicateStopFoundException extends RuntimeException {
    public DuplicateStopFoundException(int id) {
        super("DuplicateStopFound with id = " + id);
    }
}
