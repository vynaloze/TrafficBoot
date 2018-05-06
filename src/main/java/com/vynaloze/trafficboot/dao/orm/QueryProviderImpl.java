package com.vynaloze.trafficboot.dao.orm;

import com.vynaloze.trafficboot.dao.orm.entity.EntityAnalyser;
import com.vynaloze.trafficboot.dao.orm.entity.EntityInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class QueryProviderImpl implements QueryProvider {
    @Value("${trafficboot.dao.schema}")
    private String schema;
    private static final Logger logger = LogManager.getLogger(QueryProvider.class);


    @Override
    public String createQuery(@NotNull Object object) {
        List<EntityInfo> entityInfoList = EntityAnalyser.analyse(object);
        String query = buildCreateQuery(EntityAnalyser.getTableName(object.getClass()), entityInfoList);
        logger.debug("CreateQuery: " + query);
        return query;
    }

    @Override
    public String readQuery(Class<?> clazz) {
        String query = buildReadQuery(EntityAnalyser.getTableName(clazz));
        logger.debug("ReadQuery: " + query);
        return query;
    }

    @Override
    public String updateQuery(Object object) {
        return null;
    }

    @Override
    public String deleteQuery(Class<?> clazz) {
        String query = buildDeleteQuery(EntityAnalyser.getTableName(clazz));
        logger.debug("DeleteQuery: " + query);
        return query;
    }


    private String buildCreateQuery(String tableName, List<EntityInfo> entityInfoList) {
        String valuesPart = entityInfoList
                .stream()
                .map(info -> info.getValue().get())
                .map(value -> {
                    if (value instanceof Number) {
                        return value.toString();
                    } else {
                        return "'" + value.toString() + "'";
                    }
                })
                .collect(Collectors.joining(","));

        String insertPart = entityInfoList
                .stream()
                .map(EntityInfo::getFieldName)
                .collect(Collectors.joining(","));

        return "INSERT INTO " + schema + "." + tableName + "(" + insertPart + ") "
                + "VALUES (" + valuesPart + ")";
    }

    private String buildReadQuery(String tableName) {
        return "SELECT * FROM " + schema + "." + tableName + " WHERE id = :id";
    }

    private String buildUpdateQuery() {
        return null;
    }

    private String buildDeleteQuery(String tableName) {
        return "DELETE FROM " + schema + "." + tableName + " WHERE id = :id";
    }

}
