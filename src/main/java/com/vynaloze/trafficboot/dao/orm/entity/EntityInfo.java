package com.vynaloze.trafficboot.dao.orm.entity;

import java.util.Optional;

public class EntityInfo {
    private String fieldName;
    private Class<?> type;
    private Optional<Object> value;

    public EntityInfo(String fieldName, Class<?> type, Optional<Object> value) {
        this.fieldName = fieldName;
        this.type = type;
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public Optional<Object> getValue() {
        return value;
    }

    public void setValue(Optional<Object> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "EntityInfo{" +
                "fieldName='" + fieldName + '\'' +
                ", type=" + type +
                ", value=" + value +
                '}';
    }
}
