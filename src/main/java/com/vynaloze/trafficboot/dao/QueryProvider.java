package com.vynaloze.trafficboot.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueryProvider {
    @Value("${trafficboot.dao.schema}")
    private String schema;

    private static final String insertStopQuery = "INSERT INTO !SCHEMA!.stops VALUES(:id, :address)";
    private static final String selectStopByIdQuery = "SELECT * FROM !SCHEMA!.stops WHERE id = :id";
    private static final String selectStopByAddressQuery = "SELECT * FROM !SCHEMA!.stops WHERE address LIKE :address";
    private static final String deleteStopsQuery = "DELETE FROM !SCHEMA!.stops WHERE id = :id";


    public String getInsertStopQuery() {
        return replaceSchema(insertStopQuery);
    }

    public String getSelectStopByIdQuery() {
        return replaceSchema(selectStopByIdQuery);
    }

    public String getSelectStopByAddressQuery() {
        return replaceSchema(selectStopByAddressQuery);
    }

    public String getDeleteStopsQuery() {
        return replaceSchema(deleteStopsQuery);
    }

    private String replaceSchema(String query) {
        return query.replaceAll("!SCHEMA!", schema);
    }
}
