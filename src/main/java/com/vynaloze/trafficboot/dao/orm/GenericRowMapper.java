package com.vynaloze.trafficboot.dao.orm;

import com.vynaloze.trafficboot.dao.orm.entity.EntityAnalyser;
import com.vynaloze.trafficboot.dao.orm.entity.EntityInfo;
import com.vynaloze.trafficboot.dao.orm.exception.ORMException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GenericRowMapper<T> implements RowMapper<T> {
    private Class<?> objectClass;
    private static final Logger logger = LogManager.getLogger(GenericRowMapper.class);

    public GenericRowMapper(Class<?> objectClass) {
        this.objectClass = objectClass;
    }

    @Override
    public T mapRow(ResultSet resultSet, int i) throws SQLException {
        List<EntityInfo> entityInfoList = EntityAnalyser.analyse(objectClass);
        getFieldValues(resultSet, entityInfoList);
        return createNewObject(entityInfoList);
    }

    private void getFieldValues(ResultSet resultSet, List<EntityInfo> entityInfoList) {
        entityInfoList.forEach(info -> {
                    try {
                        info.setValue(Optional.ofNullable(resultSet.getObject(info.getFieldName())));
                    } catch (SQLException e) {
                        logger.error(e);
                    }
                }
        );
    }

    @SuppressWarnings("unchecked")
    private T createNewObject(List<EntityInfo> entityInfoList) {
        try {
            Class<?>[] params = entityInfoList.stream().map(EntityInfo::getType).toArray(Class<?>[]::new);
            Constructor<T> constructor = (Constructor<T>) objectClass.getConstructor(params);
            Object[] args = entityInfoList.stream().map(e -> e.getValue().get()).toArray(Object[]::new);
            return constructor.newInstance(args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new ORMException(e);
        }
    }
}
