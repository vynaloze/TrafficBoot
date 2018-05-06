package com.vynaloze.trafficboot.dao.orm.entity;

import com.vynaloze.trafficboot.dao.orm.entity.annotation.Entity;
import com.vynaloze.trafficboot.dao.orm.entity.annotation.EntityField;
import com.vynaloze.trafficboot.dao.orm.exception.ORMException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.atteo.evo.inflector.English;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityAnalyser {
    private static final Logger logger = LogManager.getLogger(EntityAnalyser.class);

    public static List<EntityInfo> analyse(Class<?> objectClass) {
        List<EntityInfo> list = new ArrayList<>();

        if (!objectClass.isAnnotationPresent(Entity.class)) {
            throw new ORMException("Object is not an Entity.");
        }

        for (Field field : objectClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(EntityField.class)) {
                field.setAccessible(true);
                EntityInfo info = new EntityInfo(getFieldName(field), field.getType(), Optional.empty());
                list.add(info);
                logger.debug(info);
            }
        }
        return list;
    }

    public static List<EntityInfo> analyse(Object object) {
        Class<?> objectClass = object.getClass();
        List<EntityInfo> list = new ArrayList<>();

        if (!objectClass.isAnnotationPresent(Entity.class)) {
            throw new ORMException("Object is not an Entity.");
        }

        for (Field field : objectClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(EntityField.class)) {
                field.setAccessible(true);
                try {
                    EntityInfo info = new EntityInfo(getFieldName(field), field.getType(), Optional.ofNullable(field.get(object)));
                    list.add(info);
                    logger.debug(info);
                } catch (IllegalAccessException e) {
                    logger.error(e);
                }
            }
        }
        return list;
    }

    public static String getTableName(Class<?> objectClass) {
        String annotationValue = objectClass.getAnnotation(Entity.class).tableName();
        if (annotationValue.isEmpty()) {
            return English.plural(objectClass.getSimpleName().toLowerCase());
        } else {
            return annotationValue;
        }
    }

    private static String getFieldName(Field field) {
        String annotationValue = field.getAnnotation(EntityField.class).value();
        if (annotationValue.isEmpty()) {
            return field.getName().toLowerCase();
        } else {
            return annotationValue;
        }
    }

    //todo cover more with unit tests
}
