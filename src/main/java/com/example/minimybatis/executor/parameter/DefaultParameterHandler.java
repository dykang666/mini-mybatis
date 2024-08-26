package com.example.minimybatis.executor.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/25 18:09
 */
public class DefaultParameterHandler  implements ParameterHandler{
    private Object parameter;

    public DefaultParameterHandler(Object parameter)
    {
        this.parameter = parameter;
    }
    /**
     * 将SQL参数设置到PreparedStatement中
     *
     * @param paramPreparedStatement
     */
    @Override
    public void setParameters(PreparedStatement paramPreparedStatement) {
        try {
            if (null != parameter) {
                if (parameter.getClass().isArray()) {

                    Object[] params = (Object[]) parameter;
                    for (int i = 0; i < params.length; i++) {
                        //Mapper保证传入参数类型匹配，这里就不做类型转换了
                        paramPreparedStatement.setObject(i + 1, params[i]);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
