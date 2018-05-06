package com.vynaloze.trafficboot.service;

import com.vynaloze.trafficboot.dao.DAO;
import com.vynaloze.trafficboot.dao.orm.exception.EntityNotFoundException;
import com.vynaloze.trafficboot.model.Stop;
import com.vynaloze.trafficboot.model.exception.DuplicateStopFoundException;
import com.vynaloze.trafficboot.util.CustomQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CoreServiceImpl implements CoreService {
    @Autowired
    private DAO<Stop> dao;
    @Autowired
    private CustomQueryProvider queryProvider;

    @Override
    public void addStop(Stop stop) {
        try {
            dao.find(Stop.class, stop.getId());
            throw new DuplicateStopFoundException(stop.getId());
        } catch (EntityNotFoundException e) {
            dao.create(stop);
        }
    }

    @Override
    public Stop getStop(int id) {
        return dao.find(Stop.class, id);
    }

    @Override
    public List<Stop> listStops(String optionalName) {
        String query = queryProvider.getSelectStopByAddressQuery();
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("address", '%' + optionalName + '%');    //this is so stupid why I can't just put % in a query...
        return dao.findWithParameters(Stop.class, query, namedParameters);
    }

    @Override
    public Stop deleteStop(int id) {
        Stop stop = dao.find(Stop.class, id);
        dao.delete(Stop.class, id);
        return stop;
    }
}
