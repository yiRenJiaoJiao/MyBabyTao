package com.myBabyTao.service.utils;


import com.baby.common.utils.httpclient.SSLClient;
import net.sf.json.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/6.
 */
@Component
public class WeChatAcountManagerUtils {


    @Resource
    private  WechatConfigUtils wechatConfigUtils;

    /**
     * get请求
     * @param url
     * @return
     */
    public static JSONObject doGetStr(String  url){
        // CloseableHttpClient defaultHttpClient= HttpClientFactory.createHttpClient();
        net.sf.json.JSONObject jsonObject = null;
        try {
            SSLClient defaultHttpClient = new SSLClient();
            HttpGet httpGet = new HttpGet(url);
            org.apache.http.HttpResponse response = defaultHttpClient.execute(httpGet);
            org.apache.http.HttpEntity entity = response.getEntity();
            if(entity!=null){
                String result = EntityUtils.toString(entity, "utf-8");
                jsonObject = net.sf.json.JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // logger.info("微信请求获取数据出现异常,异常信息");
        }
        return jsonObject;
    }


    /**
     * post请求
     * @param url
     * @param outStr
     * @return
     */
    public static JSONObject doPostStr(String url,String outStr){
        JSONObject jsonObject = null;
        try {
            //CloseableHttpClient defaultHttpClient= HttpClientFactory.createHttpClient();
            SSLClient defaultHttpClient = new SSLClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(outStr,"utf-8"));
            org.apache.http.HttpResponse response = defaultHttpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            jsonObject = JSONObject.fromObject(result);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return jsonObject;
    }
}
