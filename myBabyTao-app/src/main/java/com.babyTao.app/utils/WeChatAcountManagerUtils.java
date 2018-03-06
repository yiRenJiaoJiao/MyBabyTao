package com.babyTao.app.utils;

import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.baby.common.utils.httpclient.SSLClient;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/6.
 */
@Component
public class WeChatAcountManagerUtils {

    private static final String COMPONENT_ACCESS_TOKEN_URL ="https://api.weixin.qq.com/cgi-bin/component/api_component_token";

    private static final String PRE_AUTH_CODE_URL ="https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=componentAccessToken";

    private static final String COMPONENTAPPID = "wx1ed032225be020ab";

    private static final String COMPONENTAPPSECRET = "25dc6a9451d7a9fb858025ef3d4965b0";

    @Resource
    private  WechatConfigUtils wechatConfigUtils;

    /**
     * get请求
     * @param url
     * @return
     */
    public static JSONObject  doGetStr(String  url){
       // CloseableHttpClient defaultHttpClient= HttpClientFactory.createHttpClient();
        JSONObject jsonObject = null;
        try {
            SSLClient defaultHttpClient = new SSLClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = defaultHttpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity!=null){
                String result = EntityUtils.toString(entity, "utf-8");
                jsonObject = JSONObject.fromObject(result);
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
    public static JSONObject doPostStr(String url,String outStr) {
        JSONObject jsonObject = null;
        try {
            //CloseableHttpClient defaultHttpClient= HttpClientFactory.createHttpClient();
            SSLClient defaultHttpClient = new SSLClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(outStr,"utf-8"));
            HttpResponse response = defaultHttpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            jsonObject = JSONObject.fromObject(result);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return jsonObject;
    }
}
