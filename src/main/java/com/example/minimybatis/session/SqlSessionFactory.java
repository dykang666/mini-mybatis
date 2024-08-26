package com.example.minimybatis.session;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/25 15:41
 */
public interface SqlSessionFactory {
    /**
     * 开启数据库会话
     *
     * @return
     * @see
     */
    SqlSession openSession();
}
