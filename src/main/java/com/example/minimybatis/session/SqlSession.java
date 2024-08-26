package com.example.minimybatis.session;


import java.util.List;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/25 15:41
 */
public interface SqlSession {
    /**
     * 查询单条记录
     *
     * @param statement
     * @param parameter
     * @return
     * @see
     */
    <T> T selectOne(String statementId, Object parameter);



    /**
     * 查询多条记录
     *
     * @param statement
     * @param parameter
     * @return
     * @see
     */
    <E> List<E> selectList(String statementId, Object parameter);

    /**
     * update
     *
     * @param statement
     * @param parameter
     */
    void update(String statementId, Object parameter);


    /**
     * insert
     *
     * @param statementId
     * @param parameter
     */
    void insert(String statementId, Object parameter);


    /**
     * 获取mapper
     *
     * @param paramClass
     * @return
     * @see
     */
    <T> T getMapper(Class<T> paramClass);

    /**
     * 获取配置类
     *
     * @return
     * @see
     */
    Configuration getConfiguration();
}
