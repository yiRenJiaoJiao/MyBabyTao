package com.babyTao.app.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2018/1/26.
 */
public class PropertiesUtil {
    private String configPath = null;
    private Properties props = null;

    public PropertiesUtil() throws IOException {
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("META-INF/config/config.properties");
        this.props = new Properties();
        this.props.load(in);
        in.close();
    }

    public PropertiesUtil(String fileAddress) throws IOException {
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileAddress);
        this.props = new Properties();
        this.props.load(in);
        in.close();
    }

    public String readValue(String key) throws IOException {
        return this.props.getProperty(key);
    }

    public Map<String, String> readAllProperties() throws FileNotFoundException, IOException {
        HashMap map = new HashMap();
        Enumeration en = this.props.propertyNames();

        while(en.hasMoreElements()) {
            String key = (String)en.nextElement();
            String Property = this.props.getProperty(key);
            map.put(key, Property);
        }

        return map;
    }

    public void setValue(String key, String value) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(this.configPath);
        prop.load(fis);
        FileOutputStream fos = new FileOutputStream(this.configPath);
        prop.setProperty(key, value);
        prop.store(fos, "last update");
        fis.close();
        fos.close();
    }

    public static void main(String[] args) {
        try {
            PropertiesUtil p = new PropertiesUtil();
            System.out.println(p.readAllProperties());
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }
}
