package com.vynaloze.trafficboot.dao.orm.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String type, int id) {
        super(type + " not found with id = " + id);
    }
}
