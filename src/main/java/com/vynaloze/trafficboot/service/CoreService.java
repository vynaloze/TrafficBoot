package com.vynaloze.trafficboot.service;

import com.vynaloze.trafficboot.model.Stop;

import java.util.List;

public interface CoreService {
    void addStop(Stop stop);

    Stop getStop(int id);

    List<Stop> listStops(String optionalName);

    Stop deleteStop(int id);
}
