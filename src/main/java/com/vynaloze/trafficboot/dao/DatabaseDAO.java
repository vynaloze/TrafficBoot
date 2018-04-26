package com.vynaloze.trafficboot.dao;

import com.vynaloze.trafficboot.dao.extractors.StopExtractor;
import com.vynaloze.trafficboot.model.Stop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DatabaseDAO implements DAO {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private QueryProvider queryProvider;

    private static final Logger logger = LogManager.getLogger(DatabaseDAO.class);

    @Override
    public void insertStop(Stop stop) {
        String query = queryProvider.getInsertStopQuery();

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", stop.getId());
        namedParameters.put("address", stop.getAddress());

        logger.info("Query: " + query);
        logger.info("Parameters: " + namedParameters);

        jdbcTemplate.update(query, namedParameters);
    }

    @Override
    public Stop getStopById(int id) {
        String query = queryProvider.getSelectStopByIdQuery();

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);

        logger.info("Query: " + query);
        logger.info("Parameters: " + namedParameters);

        return jdbcTemplate.queryForObject(query, namedParameters,
                (rs, i) -> new Stop(rs.getInt("id"), rs.getString("address"))); //todo ? extract to class ?
    }

    @Override
    public List<Stop> getStopsByAddress(String address) {
        String query = queryProvider.getSelectStopByAddressQuery();

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("address", '%' + address + '%');    //this is so stupid why I can't just put % in a query...

        logger.info("Query: " + query);
        logger.info("Parameters: " + namedParameters);

        return jdbcTemplate.query(query, namedParameters, new StopExtractor());
    }

    @Override
    public void deleteStops(int id) {
        String query = queryProvider.getDeleteStopsQuery();

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);

        logger.info("Query: " + query);
        logger.info("Parameters: " + namedParameters);

        jdbcTemplate.update(query, namedParameters);
    }
}
