package com.example.minimybatis.session;

import com.example.minimybatis.binding.MapperRegistry;
import com.example.minimybatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: mybatis核心配置类
 * @date 2024/8/25 15:50
 */
public class Configuration {
    /**配置项*/
    public static Properties PROPS = new Properties();

    /** mapper代理注册器 */
    protected final MapperRegistry mapperRegistry = new MapperRegistry();

    /** mapper文件的select/update语句的id和SQL语句属性 **/
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    /**
     * addMapper
     *
     * @param type
     */
    public <T> void addMapper(Class<T> type)
    {
        this.mapperRegistry.addMapper(type);
    }



    /**
     * getMapper
     *
     * @param type
     * @param sqlSession
     * @return
     * @see
     */
    public <T> T getMapper(Class<T> type, SqlSession sqlSession)
    {
        return this.mapperRegistry.getMapper(type, sqlSession);
    }

    /**
     * 获取字符型属性(默认值为空字符串)
     *
     * @param key
     * @return
     */
    public static String getProperty(String key)
    {
        return getProperty(key, "");
    }

    /**
     * 获取字符型属性(可指定默认值)
     *
     * @param key
     * @param defaultValue
     *            默认值
     * @return
     */
    public static String getProperty(String key, String defaultValue)
    {

        return PROPS.containsKey(key) ? PROPS.getProperty(key) : defaultValue;
    }

    /**
     * addMappedStatement
     *
     * @param key
     * @param mappedStatement
     */
    public void addMappedStatement(String key, MappedStatement mappedStatement)
    {
        this.mappedStatements.put(key, mappedStatement);
    }


    /**
     * 获取MappedStatement
     *
     * @param id xml文件标签的id属性
     * @return
     * @see
     */
    public MappedStatement getMappedStatement(String id)
    {
        return this.mappedStatements.get(id);
    }

}
