package com.myBabyTao.api.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myBabyTao.api.enums.WechatMessgeTypeEnum;
import com.myBabyTao.api.vo.Article;
/*import net.sf.json.JSONArray;
import net.sf.json.JSONObject;*/

import java.util.List;

/**
 * Author:曾庆根
 * Created in 14:01 2017/12/11.
 */
public class ServerMessageBase {
    /**
     * 文本客服消息
     *
     * @param openId
     *            消息接收者openId
     * @param content
     *            文本消息内容
     * @return
     */
    public static String TextMsg(String openId, String content) {
        JSONObject jo = new JSONObject();
        jo.put("touser", openId);
        jo.put("msgtype", WechatMessgeTypeEnum.TEXT.getDesc());
        JSONObject joContent = new JSONObject();
        joContent.put("content", content);
        jo.put("text", joContent);
        return jo.toString();
    }

    /**
     * 图片客服消息
     *
     * @param openId
     *            消息接收者openId
     * @param mediaId
     *            媒体文件id
     * @return
     */
    public static String ImageMsg(String openId, String mediaId) {
        JSONObject jo = new JSONObject();
        jo.put("touser", openId);
        jo.put("msgtype", WechatMessgeTypeEnum.IMAGE.getDesc());
        JSONObject joMedia = new JSONObject();
        joMedia.put("media_id", mediaId);
        jo.put("image", joMedia);
        return jo.toString();
    }
    /**
     * 图文客服消息（非外转）
     *
     * @param openId
     *            消息接收者openId
     * @param mediaId
     *            媒体文件id
     * @return
     */
    public static String NewMsg(String openId, String mediaId) {
        JSONObject jo = new JSONObject();
        jo.put("touser", openId);
        jo.put("msgtype", WechatMessgeTypeEnum.MPNEWS.getDesc());
        JSONObject joMedia = new JSONObject();
        joMedia.put("media_id", mediaId);
        jo.put("mpnews", joMedia);
        return jo.toString();
    }

    /**
     * 语音客服消息
     *
     * @param openId
     *            消息接收者openId
     * @param mediaId
     *            媒体文件id
     * @return
     */
    public static String VoiceMsg(String openId, String mediaId) {
        JSONObject jo = new JSONObject();
        jo.put("touser", openId);
        jo.put("msgtype", WechatMessgeTypeEnum.VOICE.getDesc());
        JSONObject joMedia = new JSONObject();
        joMedia.put("media_id", mediaId);
        jo.put("voice", joMedia);
        return jo.toString();
    }

    /**
     *
     * 视频客服消息
     *
     * @param openId
     *            消息接收者openId
     * @param mediaId
     *            媒体文件id
     * @param thumb_media_id
     *            缩略图的媒体ID
     * @param title
     *            视频消息的标题(非必须)
     * @param description
     *            视频消息的描述(非必须)
     * @return
     */
    public static String VideoMsg(String openId, String mediaId,
                                  String thumb_media_id, String title, String description) {
        JSONObject jo = new JSONObject();
        jo.put("touser", openId);
        jo.put("msgtype", WechatMessgeTypeEnum.VIDEO.getDesc());
        JSONObject joMedia = new JSONObject();
        joMedia.put("media_id", mediaId);
        joMedia.put("thumb_media_id", thumb_media_id);
        joMedia.put("title", title);
        joMedia.put("description", description);
        jo.put("video", joMedia);
        return jo.toString();
    }

    /**
     *
     * 视频客服消息
     *
     * @param openId
     *            消息接收者openId
     * @param mediaId
     *            媒体文件id
     * @param thumb_media_id
     *            缩略图的媒体ID
     * @return
     */
    public static String VideoMsg(String openId, String mediaId,
                                  String thumb_media_id) {
        JSONObject jo = new JSONObject();
        jo.put("touser", openId);
        jo.put("msgtype", WechatMessgeTypeEnum.VIDEO.getDesc());
        JSONObject joMedia = new JSONObject();
        joMedia.put("media_id", mediaId);
        joMedia.put("thumb_media_id", thumb_media_id);
        joMedia.put("title", "");
        joMedia.put("description", "");
        jo.put("video", joMedia);
        return jo.toString();
    }

    /**
     *
     * 音乐客服消息
     *
     * @param openId
     *            消息接收者openId
     *            媒体文件id
     * @param thumb_media_id
     *            缩略图的媒体ID
     * @param title
     *            音乐消息的标题(非必须)
     * @param description
     *            音乐消息的描述(非必须)
     * @param musicurl
     *            音乐链接
     * @param hqmusicurl
     *            高品质音乐链接，wifi环境优先使用该链接播放音乐
     * @param thumb_media_id
     *            缩略图的媒体ID
     * @return
     */
    public static String MusicMsg(String openId, String title,
                                  String description, String musicurl, String hqmusicurl,
                                  String thumb_media_id) {
        JSONObject jo = new JSONObject();
        jo.put("touser", openId);
        jo.put("msgtype", WechatMessgeTypeEnum.MUSIC.getDesc());
        JSONObject joMedia = new JSONObject();
        joMedia.put("title", title);
        joMedia.put("description", description);
        joMedia.put("musicurl", musicurl);
        joMedia.put("hqmusicurl", hqmusicurl);
        joMedia.put("thumb_media_id", thumb_media_id);
        jo.put("music", joMedia);
        return jo.toString();
    }

    /**
     *
     * 音乐客服消息
     *
     * @param openId
     *            消息接收者openId
     *            媒体文件id
     * @param thumb_media_id
     *            缩略图的媒体ID
     * @param musicurl
     *            音乐链接
     * @param hqmusicurl
     *            高品质音乐链接，wifi环境优先使用该链接播放音乐
     * @param thumb_media_id
     *            缩略图的媒体ID
     * @return
     */
    public static String MusicMsg(String openId, String musicurl,
                                  String hqmusicurl, String thumb_media_id) {
        JSONObject jo = new JSONObject();
        jo.put("touser", openId);
        jo.put("msgtype", WechatMessgeTypeEnum.MUSIC.getDesc());
        JSONObject joMedia = new JSONObject();
        joMedia.put("title", "");
        joMedia.put("description", "");
        joMedia.put("musicurl", musicurl);
        joMedia.put("hqmusicurl", hqmusicurl);
        joMedia.put("thumb_media_id", thumb_media_id);
        jo.put("music", joMedia);
        return jo.toString();
    }

    /**
     * 发送图文客服消息
     *
     * @param openId
     *            消息接收者openId
     * @param articles
     *            图文消息列表
     * @return
     */
    public static String NewsMsg(String openId, List<Article> articles) {
        JSONObject jo = new JSONObject();
        jo.put("touser", openId);
        jo.put("msgtype", WechatMessgeTypeEnum.NEWS.getDesc());
        JSONObject joMedia = new JSONObject();
        JSONArray ja = new JSONArray();
        for (Article article : articles) {
            JSONObject joChild = new JSONObject();
            joChild.put("title", article.getTitle());
            joChild.put("description", article.getDescription());
            joChild.put("url", article.getUrl());
            joChild.put("picurl", article.getPicUrl());
            ja.add(joChild);
        }
        joMedia.put("articles", ja);
        jo.put("news", joMedia);
        return jo.toString();
    }
}
