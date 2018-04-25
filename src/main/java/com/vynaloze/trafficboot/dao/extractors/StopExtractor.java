package com.vynaloze.trafficboot.dao.extractors;

import com.vynaloze.trafficboot.model.Stop;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StopExtractor implements ResultSetExtractor<List<Stop>> {
    @Override
    public List<Stop> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<Stop> stopList = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String address = resultSet.getString("address");
            stopList.add(new Stop(id, address));
        }
        return stopList;
    }
}
