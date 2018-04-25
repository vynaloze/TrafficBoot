package com.vynaloze.trafficboot.dao;

import com.vynaloze.trafficboot.model.Stop;

import java.util.List;

public interface DAO {
    void insertStop(Stop stop);

    Stop getStopById(int id);

    List<Stop> getStopsByAddress(String address);
}
