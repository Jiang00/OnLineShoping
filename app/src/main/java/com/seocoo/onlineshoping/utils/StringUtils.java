package com.seocoo.onlineshoping.utils;

/**
 * desc: 字符串工具类
 *
 * @author Bian
 * create at 2018/12/18
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     *
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str) || str.trim().length() == 0;
    }

    /**
     * 判断str null,"","null" 均视为空.
     */
    public static boolean isNotEmpty(String str) {
        boolean bool = true;
        if (str == null || "null".equals(str) || "".equals(str) || str.length() == 0) {
            bool = false;
        }
        return bool;
    }


}
