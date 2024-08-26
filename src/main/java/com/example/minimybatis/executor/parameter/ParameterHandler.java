package com.example.minimybatis.executor.parameter;

import java.sql.PreparedStatement;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/25 18:08
 */
public interface ParameterHandler {
    /**
     * 设置参数
     *
     * @param paramPreparedStatement
     * @see
     */
    void setParameters(PreparedStatement paramPreparedStatement);
}
