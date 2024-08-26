package com.example.minimybatis.session.defaults;

import com.example.minimybatis.executor.Executor;
import com.example.minimybatis.executor.SimpleExecutor;
import com.example.minimybatis.mapping.MappedStatement;
import com.example.minimybatis.session.Configuration;
import com.example.minimybatis.session.SqlSession;
import com.example.minimybatis.utils.CommonUtis;

import java.util.List;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/25 17:58
 */
public class DefaultSqlSession  implements SqlSession {
    private final Configuration configuration;

    private final Executor executor;
    /**
     * 默认构造方法
     *
     * @param configuration
     */
    public DefaultSqlSession(Configuration configuration)
    {
        this.configuration = configuration;
        this.executor = new SimpleExecutor(configuration);

    }

    @Override
    public <T> T selectOne(String statementId, Object parameter) {
        List<T> results = this.<T> selectList(statementId, parameter);

        return CommonUtis.isNotEmpty(results) ? results.get(0) : null;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object parameter) {
        MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
        return this.executor.<E> doQuery(mappedStatement, parameter);
    }
    /**
     * 更新
     *
     * @param statementId
     * @param parameter
     */
    @Override
    public void update(String statementId, Object parameter) {
        MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
        this.executor.doUpdate(mappedStatement, parameter);
    }

    @Override
    public void insert(String statementId, Object parameter) {

    }
    /**
     * 获取Mapper
     *
     * @param type
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.<T> getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }
}
