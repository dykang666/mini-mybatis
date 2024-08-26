package com.example.minimybatis.session;


import com.example.minimybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/25 15:39
 */
public class SqlSessionFactoryBuilder {
    /**
     * build
     *
     * @param fileName
     * @return
     * @see
     */
    public SqlSessionFactory build(String fileName)
    {

        InputStream inputStream = SqlSessionFactoryBuilder.class.getClassLoader().getResourceAsStream(fileName);

        return build(inputStream);
    }

    /**
     * build
     *
     * @param inputStream
     * @return
     * @see
     */
    public SqlSessionFactory build(InputStream inputStream)
    {
        try
        {
            Configuration.PROPS.load(inputStream);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return new DefaultSqlSessionFactory(new Configuration());
    }
}

