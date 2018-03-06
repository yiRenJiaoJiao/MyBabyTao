package com.baby.common.utils.wechat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/1/26.
 */
@Component("checkUtil")
public class CheckUtil {
    //配置微信公众号时填写的Token
   // private static final String token = "chenggaowei";

    public static boolean checkSignature(String signature, String timestamp, String nonce,String token) {

        // 拼接字符串
        String[] arr = new String[] { token, timestamp, nonce };
        // 排序
        Arrays.sort(arr);
        // 生成字符串
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        // SHA1加密
        String tmp = DecriptUtil.SHA1(content.toString());
        return tmp.equals(signature);
    }
}
