package com.babyTao.app.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/12/7.
 */
@Component("wechatConfigUtils")
public class WechatConfigUtils {

    @Value("#{configProperties['getAccessTokenUrl']}")
    String getAccessTokenUrl;
    @Value("#{configProperties['appid']}")
    String appid;
    @Value("#{configProperties['secrect']}")
    String secrect;


    public String getSecrect() {
        return secrect;
    }

    public String getAppid() {
        return appid;
    }

    public String getGetAccessTokenUrl() {
        return getAccessTokenUrl;
    }
}
