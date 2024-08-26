package com.example.minimybatis.test;



import com.example.minimybatis.session.SqlSession;
import com.example.minimybatis.session.SqlSessionFactory;
import com.example.minimybatis.session.SqlSessionFactoryBuilder;
import com.example.minimybatis.test.bean.User;
import com.example.minimybatis.test.dao.UserMapper;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/25 15:38
 */
public class TestMain {
    public static void main(String[] args) {
        //在SqlSessionFactoryBuilder中加载properties文件配置返回一个默认SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build("conf.properties");
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.getUser("1");
        System.out.println("******* " + user);
        System.out.println("*******all " + userMapper.getAll());

//        userMapper.updateUser("1");
//        System.out.println("*******all " + userMapper.getAll());
    }


}
