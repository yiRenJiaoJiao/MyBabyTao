package com.baby.common.utils.collections;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by SEELE on 2017/8/30.
 */
public class LocalMapCahe {

    private static ConcurrentHashMap<String, Object> cacheMap = new ConcurrentHashMap<String, Object>();

    /**
     * 获取缓存的对象
     *
     * @param account
     * @return
     */
    public static Object getCache(String key) {
        return cacheMap.get(key);
    }

    /**
     * 移除缓存信息
     *
     * @param account
     */
    public static void removeCache(String key) {
        cacheMap.remove(key);
    }

    public static void put(String key,Object object) {
        cacheMap.put(key,object);
    }

}
