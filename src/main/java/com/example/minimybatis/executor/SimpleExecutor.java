package com.example.minimybatis.executor;


import com.example.minimybatis.constants.Constant;
import com.example.minimybatis.executor.parameter.DefaultParameterHandler;
import com.example.minimybatis.executor.parameter.ParameterHandler;
import com.example.minimybatis.executor.resultset.DefaultResultSetHandler;
import com.example.minimybatis.executor.resultset.ResultSetHandler;
import com.example.minimybatis.executor.statement.SimpleStatementHandler;
import com.example.minimybatis.executor.statement.StatementHandler;
import com.example.minimybatis.mapping.MappedStatement;
import com.example.minimybatis.session.Configuration;

import java.sql.*;
import java.util.List;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: mybatis执行器
 * @date 2024/8/25 18:00
 */
public class SimpleExecutor implements Executor {

    /** 数据库连接 */
    private static Connection connection;

    private Configuration conf;

    static
    {
        initConnect();
    }
    /**
     * 静态初始化数据库连接
     *
     * @return
     */
    private static void initConnect()
    {

        String driver = Configuration.getProperty(Constant.DB_DRIVER_CONF);
        String url = Configuration.getProperty(Constant.DB_URL_CONF);
        String username = Configuration.getProperty(Constant.DB_USERNAME_CONF);
        String password = Configuration.getProperty(Constant.db_PASSWORD);

        try
        {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("数据库连接成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * 初始化方法
     *
     * @param configuration
     */
    public SimpleExecutor(Configuration configuration)
    {
        this.conf = configuration;
    }



    @Override
    public <E> List<E> doQuery(MappedStatement ms, Object parameter) {
        //1.获取数据库连接
        try {
            Connection connection = getConnect();
            //2.获取MappedStatement信息，里面有sql信息
            MappedStatement mappedStatement = conf.getMappedStatement(ms.getSqlId());
            //3.实例化StatementHandler对象，
            StatementHandler statementHandler = new SimpleStatementHandler(mappedStatement);

            //4.通过StatementHandler和connection获取PreparedStatement
            PreparedStatement preparedStatement = statementHandler.prepare(connection);
            //5.实例化ParameterHandler，将SQL语句中？参数化
            ParameterHandler parameterHandler = new DefaultParameterHandler(parameter);
            parameterHandler.setParameters(preparedStatement);

            //6.执行SQL，得到结果集ResultSet
            ResultSet resultSet = statementHandler.query(preparedStatement);
            //7.实例化ResultSetHandler，通过反射将ResultSet中结果设置到目标resultType对象中
            ResultSetHandler resultSetHandler = new DefaultResultSetHandler(mappedStatement);
            return resultSetHandler.handleResultSets(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * getConnect
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnect() throws SQLException
    {
        if (null != connection)
        {
            return connection;
        }
        else
        {
            throw new SQLException("无法连接数据库，请检查配置");
        }
    }


    /**
     * doUpdate
     *
     * @param ms
     * @param parameter
     */
    @Override
    public void doUpdate(MappedStatement ms, Object parameter) {
        try {
            //1.获取数据库连接
            Connection connection = getConnect();
            //2.获取MappedStatement信息，里面有sql信息
            MappedStatement mappedStatement = conf.getMappedStatement(ms.getSqlId());

            //3.实例化StatementHandler对象，
            StatementHandler statementHandler = new SimpleStatementHandler(mappedStatement);

            //4.通过StatementHandler和connection获取PreparedStatement
            PreparedStatement preparedStatement = statementHandler.prepare(connection);

            //5.实例化ParameterHandler，将SQL语句中？参数化
            ParameterHandler parameterHandler = new DefaultParameterHandler(parameter);
            parameterHandler.setParameters(preparedStatement);

            //6.执行SQL
            statementHandler.update(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
