package com.vynaloze.trafficboot.service;

import com.vynaloze.trafficboot.dao.DAO;
import com.vynaloze.trafficboot.model.Stop;
import com.vynaloze.trafficboot.model.exception.DuplicateStopFoundException;
import com.vynaloze.trafficboot.model.exception.StopNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoreServiceImpl implements CoreService {
    @Autowired
    private DAO dao;

    @Override
    public void addStop(Stop stop) {
        try {
            dao.getStopById(stop.getId());
            throw new DuplicateStopFoundException(stop.getId());
        } catch (StopNotFoundException e) {
            dao.insertStop(stop);
        }
    }

    @Override
    public Stop getStop(int id) {
        return dao.getStopById(id);
    }

    @Override
    public List<Stop> listStops(String optionalName) {
        return dao.getStopsByAddress(optionalName);
    }

    @Override
    public Stop deleteStop(int id) {
        Stop stop = dao.getStopById(id);
        dao.deleteStops(id);
        return stop;
    }
}
