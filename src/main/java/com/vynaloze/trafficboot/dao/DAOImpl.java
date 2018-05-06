package com.vynaloze.trafficboot.dao;

import com.vynaloze.trafficboot.dao.orm.GenericRowMapper;
import com.vynaloze.trafficboot.dao.orm.QueryProviderImpl;
import com.vynaloze.trafficboot.dao.orm.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DAOImpl<T> implements DAO<T> {
    @Autowired
    private QueryProviderImpl queryProvider;
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void create(T object) {
        String query = queryProvider.createQuery(object);
        jdbcTemplate.getJdbcOperations().update(query);
    }

    @Override
    public T find(Class<?> type, int id) {
        String query = queryProvider.readQuery(type);
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        try {
            return jdbcTemplate.queryForObject(query, namedParameters, new GenericRowMapper<T>(type));
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(type.getSimpleName(), id);
        }
    }

    @Override
    public List<T> findWithParameters(Class<?> type, String query) {
        return jdbcTemplate.getJdbcOperations().query(query, new GenericRowMapper<T>(type));
    }

    @Override
    public List<T> findWithParameters(Class<?> type, String query, Map<String, Object> namedParameters) {
        return jdbcTemplate.query(query, namedParameters, new GenericRowMapper<T>(type));
    }

    @Override
    public void update(T object) {

    }

    @Override
    public void delete(Class<?> type, int id) {
        String query = queryProvider.deleteQuery(type);
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        jdbcTemplate.update(query, namedParameters);
    }
}
