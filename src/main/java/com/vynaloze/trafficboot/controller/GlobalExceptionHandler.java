package com.vynaloze.trafficboot.controller;

import com.vynaloze.trafficboot.model.exception.DuplicateStopFoundException;
import com.vynaloze.trafficboot.model.exception.StopNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(StopNotFoundException.class)
    public ResponseEntity handleStopNotFound(Exception e) {
        logger.info(e);
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateStopFoundException.class)
    public ResponseEntity handleDuplicateStop(Exception e) {
        logger.info(e);
        return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
    }

}
