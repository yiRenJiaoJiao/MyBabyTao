package com.baby.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * Created by Administrator on 2017/4/19.
 */
public class GuidUtils {

    public static String getGuid(){

        // 创建 GUID 对象
        UUID uuid = UUID.randomUUID();

        // 得到对象产生的ID
        String guid = uuid.toString();

        // 转换为大写

        //guid = guid.toUpperCase();

        // 替换 -
        // guid = guid.replaceAll("-", "");

        return  guid;
    }

    private static String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 生成随机字符串
     * @param length  字符串长度
     * @return
     */
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++)
            sb.append(base.charAt(random.nextInt(62)));
        return sb.toString();
    }
}
