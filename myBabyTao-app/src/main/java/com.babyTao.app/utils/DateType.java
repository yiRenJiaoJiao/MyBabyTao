package com.babyTao.app.utils;

import com.baby.common.entity.wechat.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.Document;
import org.dom4j.Element;


import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Administrator on 2018/1/26.
 */
public class DateType {
    /**
     * 将xml转化为Map集合
     *
     * @param request
     * @return
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        InputStream ins = null;
        try {
            ins = request.getInputStream();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Document doc = null;
        try {
            doc = reader.read(ins);
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }
       Element root = doc.getRootElement();
        List<Element> list = root.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        try {
            ins.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return map;
    }

    public static String testMessageToxml(TextMessage testMessage){
        XStream xStream = new XStream();
        String s = xStream.toXML(testMessage);
        return s;
    }
}
