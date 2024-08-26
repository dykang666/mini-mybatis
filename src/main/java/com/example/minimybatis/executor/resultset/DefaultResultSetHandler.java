package com.example.minimybatis.executor.resultset;

import com.example.minimybatis.mapping.MappedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/25 18:27
 */
public class DefaultResultSetHandler implements ResultSetHandler{
    private final MappedStatement mappedStatement;
    public DefaultResultSetHandler(MappedStatement mappedStatement) {
        this.mappedStatement = mappedStatement;
    }

    /**
     * 处理查询结果，通过反射设置到返回的实体类
     *
     * @param resultSet
     * @return
     */
    @Override
    public <E> List<E> handleResultSets(ResultSet resultSet) {
        try {
            List<E> result = new ArrayList<>();
            if (null == resultSet)
            {
                return null;
            }
            while (resultSet.next()){
                // 通过反射实例化返回类
                Class<?> entityClass = Class.forName(mappedStatement.getResultType());
                // 通过反射实例化返回类
                E entity = (E)entityClass.newInstance();
                Field[] declaredFields = entityClass.getDeclaredFields();
                for (Field field : declaredFields){
                    // 对成员变量赋值
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();
                    // 目前只实现了string和int转换
                    if (String.class.equals(fieldType))
                    {
                        field.set(entity, resultSet.getString(field.getName()));
                    }
                    else if (int.class.equals(fieldType) || Integer.class.equals(fieldType))
                    {
                        field.set(entity, resultSet.getInt(field.getName()));
                    }
                    else
                    {
                        // 其他类型自己转换，这里就直接设置了
                        field.set(entity, resultSet.getObject(field.getName()));
                    }
                }
                    result.add(entity);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
