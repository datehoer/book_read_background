package com.datehoer.bookapi.common;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.annotation.TableField;

import java.lang.reflect.Field;

public class QueryWrapperUtil {

    public static <T> QueryWrapper<T> buildQueryWrapper(T obj) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (obj == null) {
            return queryWrapper;
        }

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value != null) {
                    String columnName = getColumnName(field);
                    if (field.getType().equals(String.class) && !columnName.equals("id")) {
                        queryWrapper.like(columnName, value);
                    } else {
                        queryWrapper.eq(columnName, value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return queryWrapper;
    }

    private static String getColumnName(Field field) {
        TableField tableField = field.getAnnotation(TableField.class);
        if (tableField != null) {
            return tableField.value();
        }
        return field.getName();
    }
}
