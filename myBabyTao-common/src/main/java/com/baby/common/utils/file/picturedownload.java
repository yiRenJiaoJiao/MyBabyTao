package com.baby.common.utils.file;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2017-12-15.
 * 用户图片下载
 */
public class picturedownload {

    /**
     * 下载文件到本地
     * @param urlString 被下载的文件地址
     * @param filename 本地文件名
     * @throws Exception 各种异常
     */
    public static void download(String urlString, String filename) throws Exception {
        URL url = new URL(urlString);// 构造URL
        URLConnection con = url.openConnection();// 打开连接
        InputStream is = con.getInputStream();// 输入流
        OutputStream os = new FileOutputStream(filename); // 输出的文件流
        try{
            byte[] bs = new byte[1024];// 1K的数据缓冲
            int len;// 读取到的数据长度
            while ((len = is.read(bs)) != -1) {// 开始读取
                os.write(bs, 0, len);
            }
        }catch (Exception ex){

            throw ex;
        }finally {
            os.close();// 完毕，关闭所有链接
            is.close();
        }
    }

    /**
     * 微信素材名称确认
     * @param url
     * @param fileName
     * @return
     */
    public  static String getImgName(String url,String fileName,String guid){
        String fileNewName=fileName.toLowerCase();
        if(fileNewName.indexOf(".jpg")>0)
            return guid+".jpg";
        if(fileNewName.indexOf(".png")>0)
            return guid+".png";
        if(fileNewName.indexOf(".gif")>0)
            return guid+".gif";
        if(fileNewName.indexOf(".jpeg")>0)
            return guid+".jpeg";
        url=url.toLowerCase();

        if (url.indexOf("wx_fmt=")>0)
            return guid+"."+url.toLowerCase().substring(url.indexOf("wx_fmt=")+7);

        return guid+".png";
    }

    public static void main(String[] args) throws Exception {
        download("http://mmbiz.qpic.cn/mmbiz_jpg/KiakWXLCBKDQicwDibOZ2Opu0BO48a3q4JAxuX9ajibia37iaStrbhkia8hasJXCSXJb9nCxOzeP2ADDjJU3mw6OtCn3A/0?wx_fmt=jpeg", "D:\\weixin.jpg");
    }

}
