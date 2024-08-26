package com.example.minimybatis.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/25 15:56
 */
public class CommonUtis {
    /**
     * list/set is not empty
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection)
    {
        return collection != null && !collection.isEmpty();
    }
    /**
     * map is not empty
     *
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map)
    {
        return map != null && !map.isEmpty();
    }

    /**
     * 数组不为空
     *
     * @param arr
     * @return
     * @see
     */
    public static boolean isNotEmpty(Object[] arr)
    {
        return arr != null && arr.length > 0;
    }

    /**
     * 对字符串去空白符和换行符等
     *
     * @return
     */
    public static String stringTrim(String src)
    {
        return (null != src) ? src.trim() : null;
    }

    /**
     * string is not empty
     *
     * @param src
     * @return
     */
    public static boolean isNotEmpty(String src)
    {
        return src != null && src.trim().length() > 0;
    }
}
