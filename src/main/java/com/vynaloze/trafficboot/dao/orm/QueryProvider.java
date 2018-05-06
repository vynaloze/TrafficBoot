package com.vynaloze.trafficboot.dao.orm;

public interface QueryProvider {
    String createQuery(Object object);

    String readQuery(Class<?> clazz);

    String updateQuery(Object object);

    String deleteQuery(Class<?> clazz);
}
