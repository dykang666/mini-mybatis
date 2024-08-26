package com.example.minimybatis.executor.resultset;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/25 18:26
 */
public interface ResultSetHandler {

    /**
     * 处理查询结果
     *
     * @param resultSet
     * @return
     * @see
     */
    <E> List<E> handleResultSets(ResultSet resultSet);
}
