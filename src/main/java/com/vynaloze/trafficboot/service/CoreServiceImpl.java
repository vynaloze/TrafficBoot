package com.vynaloze.trafficboot.service;

import com.vynaloze.trafficboot.dao.DAO;
import com.vynaloze.trafficboot.model.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoreServiceImpl implements CoreService {
    @Autowired
    private DAO dao;

    @Override
    public void addStop(Stop stop) {
        dao.insertStop(stop);
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
    public void deleteStop(int id) {
        dao.deleteStops(id);
    }
}
