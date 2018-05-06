package com.vynaloze.trafficboot.dao;

import java.util.List;
import java.util.Map;

public interface DAO<T> {
    void create(T object);

    T find(Class<?> type, int id);

    List<T> findWithParameters(Class<?> type, String query);

    List<T> findWithParameters(Class<?> type, String query, Map<String, Object> namedParameters);

    void update(T object);

    void delete(Class<?> type, int id);
}
