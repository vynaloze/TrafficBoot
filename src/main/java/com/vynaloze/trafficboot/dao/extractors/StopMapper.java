package com.vynaloze.trafficboot.dao.extractors;

import com.vynaloze.trafficboot.model.Stop;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StopMapper implements RowMapper<Stop> {
    @Override
    public Stop mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Stop(resultSet.getInt("id"), resultSet.getString("address"));
    }
}
