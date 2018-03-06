package com.baby.common.utils.Ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import java.net.URL;

/**
 * Created by Administrator on 2017-10-16.
 */
public class EhcacheUtil {

    private static final String path = "/ehcache.xml";

    private URL url;

    private CacheManager manager;

    private static EhcacheUtil ehCache;

    /**
     *
     * @param path
     */
    private EhcacheUtil(String path) {
        url = getClass().getResource(path);
        manager = CacheManager.create(url);
        //System.out.println(manager.getActiveConfigurationText());
    }

    /**
     * 获得Ehcache实例
     * @return
     */
    public static EhcacheUtil getInstance() {
        if (ehCache== null) {
            ehCache= new EhcacheUtil(path);
        }
        return ehCache;
    }

    /**
     * 将数据插入缓存
     * @param cacheName 缓存名字
     * @param key 对应key值
     * @param value 对应value信息
     */
    public void put(String cacheName, String key, Object value) {
        Cache cache = manager.getCache(cacheName);
        Element element = new Element(key, value);
        cache.put(element);
    }

    /**
     * 将数据插入缓存
     * @param cacheName 缓存名字
     * @param key 对应key值
     * @param value 对应value信息
     * @param second 缓存多少秒
     */
    public void put(String cacheName, String key, Object value,int second) {
        Cache cache = manager.getCache(cacheName);
        Element element = new Element(key, value);
        element.setTimeToLive(second);//以创建时间为基准开始计算的超时时长；
        //timeToIdleSeconds :在创建时间和最近访问时间中取出离现在最近的时间作为基准计算的超时时长；
        cache.put(element);
    }

    /**
     * 获取缓存中对应key值的value
     * @param cacheName 缓存名称
     * @param key 缓存key值
     * @return 对应的value值，结果为object
     */
    public Object get(String cacheName, String key) {
        Cache cache = manager.getCache(cacheName);
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    /**
     * 获取对应缓存名称的cache信息
     * @param cacheName 缓存名称
     * @return
     */
    public Cache get(String cacheName) {
        return manager.getCache(cacheName);
    }

    /**
     * 更新缓存中的信息
     * @param cacheName 缓存名称
     * @param key
     * @param value
     */
    public void update(String cacheName, String key, Object value)
    {
        Cache cache = manager.getCache(cacheName);
        //当添加元素的时候，如果缓存中已经存在相同key的元素则会将后者覆盖前者
        cache.put(new Element(key, value));
    }

    /**
     * 删除对应缓存名称中的key数据
     * @param cacheName 缓存名称
     * @param key
     */
    public void remove(String cacheName, String key) {
        Cache cache = manager.getCache(cacheName);
        cache.remove(key);
    }

}
