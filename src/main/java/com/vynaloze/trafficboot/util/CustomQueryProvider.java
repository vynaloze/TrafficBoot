package com.vynaloze.trafficboot.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomQueryProvider {
    @Value("${trafficboot.dao.schema}")
    private String schema;

    private static final String selectStopByAddressQuery = "SELECT * FROM !SCHEMA!.stops WHERE address LIKE :address";

    public String getSelectStopByAddressQuery() {
        return replaceSchema(selectStopByAddressQuery);
    }

    private String replaceSchema(String query) {
        return query.replaceAll("!SCHEMA!", schema);
    }

}
