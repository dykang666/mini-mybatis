package com.example.minimybatis.test.dao;


import com.example.minimybatis.test.bean.User;

import java.util.List;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/25 16:52
 */
public interface UserMapper {
    /**
     * 获取单个user
     *
     * @param id
     * @return
     * @see
     */
    User getUser(String id);

    /**
     * 获取所有用户
     *
     * @return
     * @see
     */
    List<User> getAll();

    /**
     * 更新用户（功能未完成）
     *
     * @param id
     */
    void updateUser(String id);
}
