package com.example.minimybatis.test.bean;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/25 16:49
 */
public class User {
    /** the id */
    private String id;

    /** the name */
    private String name;

    /**
     * @return Returns the id.
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * toString
     *
     * @return
     */
    @Override
    public String toString()
    {
        return "User [id=" + id + ", name=" + name + "]";
    }
}
