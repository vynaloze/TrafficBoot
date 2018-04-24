package com.vynaloze.trafficboot.dao;

import com.vynaloze.trafficboot.model.Stop;

public interface DAO {
    void insertStop(Stop stop);

    Stop getStopById(int id);

    void clearAll();
}
