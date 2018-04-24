package com.vynaloze.trafficboot.dao;

import com.vynaloze.trafficboot.model.Stop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DatabaseDAO implements DAO {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final Logger logger = LogManager.getLogger(DatabaseDAO.class);

    @Override
    public void insertStop(Stop stop) {
        String query = "insert into test.stops values(:id, :address)"; //todo query provider + SCHEMA!

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", stop.getId());
        namedParameters.put("address", stop.getAddress());

        logger.info("Query: " + query);
        logger.info("Parameters: " + namedParameters);

        jdbcTemplate.update(query, namedParameters);
    }

    @Override
    public Stop getStopById(int id) {
        String query = "select * from test.stops where id = :id"; //todo query prov too

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);

        logger.info("Query: " + query);
        logger.info("Parameters: " + namedParameters);

        return jdbcTemplate.queryForObject(query, namedParameters,
                (rs, i) -> new Stop(rs.getInt("id"), rs.getString("address"))); //todo screw lambda?
    }

    @Override
    public void clearAll() {

    }
}
