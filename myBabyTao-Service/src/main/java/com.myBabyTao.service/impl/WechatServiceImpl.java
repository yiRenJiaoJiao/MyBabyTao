package com.myBabyTao.service.impl;

import com.alibaba.rocketmq.client.producer.SendResult;
import com.baby.common.entity.wechat.TextMessage;
import com.baby.common.utils.data.DateType;
import com.baby.common.utils.wechat.WechatMessageUtil;
import com.myBabyTao.api.enums.WechatMessgeTypeEnum;
import com.myBabyTao.api.service.WechatService;
import com.myBabyTao.api.utils.ServerMessageBase;
import com.myBabyTao.service.utils.RedisCacheManagerTool;
import com.myBabyTao.service.utils.WeChatAcountManagerUtils;
import com.myBabyTao.service.utils.WechatConfigUtils;
import net.sf.ehcache.search.impl.BaseResult;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Administrator on 2018/1/25.
 */
@Service("wechatService")
public class WechatServiceImpl implements WechatService {

    @Autowired
    private RedisCacheManagerTool redisCacheManagerTool;

    @Autowired
    private WechatConfigUtils wechatConfigUtils;

    org.slf4j.Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    @Override
    public String getAccessToken() {
        Object accessToken = redisCacheManagerTool.get("accessToken");
        if(accessToken != null){
            return (String)accessToken;
        }
        String getAccessTokenUrl = wechatConfigUtils.getGetAccessTokenUrl();
         JSONObject jsonObject = WeChatAcountManagerUtils.doGetStr(getAccessTokenUrl);
        if(jsonObject == null || jsonObject.containsKey("errcode")){
            if(jsonObject !=null){
                logger.info("连接微信端获取公众号的token出现异常,异常信息错误码:{},错误信息:{}",jsonObject.get("errcode"),jsonObject.get("errmsg"));
            }else{
                logger.info("连接微信端获取公众号的token出现异常,微信返回的数据为null");
            }
           return null;
        }
        String access_token = jsonObject.getString("access_token");
        Integer expires_in = jsonObject.getInt("expires_in");
        redisCacheManagerTool.set("accessToken",access_token,expires_in);
        return access_token;
    }

    @Override
    public String processRequest(Map<String ,String> map) {
        logger.info("解析微信推送的数据:{}",map);
        String fromUserName = map.get("FromUserName");// 发送方帐号（一个OpenID）
        String toUserName = map.get("ToUserName");// 开发者微信号
        String msgType = map.get("MsgType"); // 消息类型
        String responseMessage = "success";// 默认回复一个"success"
        String content = map.get("Content");
        // 对消息进行处理
        if (WechatMessageUtil.MESSAGE_TEXT.equals(msgType)) {// 文本消息
            responseMessage = getTextMessage(fromUserName,toUserName,content);
            logger.info("返回文本消息,消息体:{}",responseMessage);
        }
        logger.info(responseMessage);
        return responseMessage;
    }

    /**
     * 生成回复消息xml字符串格式
     * @param fromUserName
     * @param toUserName
     * @return
     */
    private String getTextMessage(String fromUserName, String toUserName,String content) {
        String responseMessage = null;
        TextMessage textMessage = new TextMessage();
        textMessage.setMsgType(WechatMessageUtil.MESSAGE_TEXT);
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(System.currentTimeMillis());
        if("你好".equals(content)){
            textMessage.setContent("你好!有什么可以帮助你的吗?");
        }else{
            textMessage.setContent("客服暂时在忙,一会给你回复");
        }
        responseMessage = WechatMessageUtil.textMessageToXml(textMessage);
        return responseMessage;
    }



    @Override
    public String sendWechatServerMessage(
            String messageType,
            String openId,
            String content,
            String mediaId,
            String wechatCountOpenId,
            Integer userId
    ){
        logger.info("开始进来发送客服消息[messageType:"+messageType+",openId:"+openId+",content:"+content+",mediaId:"+mediaId+",wechatConuntOpenId:"+wechatCountOpenId+",userId:"+userId);
        String stringJsonObject = null;
        switch (messageType) {
            case "text":
                content= StringEscapeUtils.unescapeHtml4(content);
                String contentRe= content.replace("<br>","\n");
                logger.info("发送文本消息:{}",contentRe);
                stringJsonObject = ServerMessageBase.TextMsg(openId, contentRe);
                break;
            case "image":
                stringJsonObject = ServerMessageBase.ImageMsg(openId, mediaId);
                break;
            case "voice":
                stringJsonObject = ServerMessageBase.VoiceMsg(openId, mediaId);
                break;
            case "news":
                stringJsonObject = ServerMessageBase.NewMsg(openId, mediaId);
                break;
            case "mpnews":
                stringJsonObject = ServerMessageBase.NewMsg(openId, mediaId);
                break;
        }
//        Integer userId= wechatAccountService.findUserIdByUserName(wechatCountOpenId);
//       Integer userId=1361;
        if (userId==null){
            logger.info("发送客服消息没有找到对应的userid");
            return  "发送客服消息没有找到对应的userid";
        }
        String accessToken = getAccessToken();
        //String accessToken=wechatAccountService.getAuthorizerAccessToken(userId);
//       String accessToken="5_f2LTM2FZzq0xH0ajmh-Bcf4P9wEJlQWnORsSw58n4BN7_doM6dMu-NOeF2qnuVyL8OPkAVHx2Xyh8F7_SjKjuGRVY51LPk70MwoIbGgRxDZ9mkTzDvz9QefiHCUm-AOiA7ufkC0eSNUz3Q_cATWbAHAHAD";
        if (accessToken==null){
            logger.info("获取accessTOken失败。。。。。");
            return "获取accessTOken失败。。。。。";
        }
        logger.info("开始发送客服消息");
        BaseResult result = null;
        String errorCode=null;
        try {
            String gsendCustomMessage = wechatConfigUtils.getSendCustomMessageUrl()+accessToken;
            JSONObject jsonObject = WeChatAcountManagerUtils.doPostStr(gsendCustomMessage, stringJsonObject);
            if(jsonObject == null || jsonObject.containsKey("errcode")){
                logger.info("发送客服消息时微信推送过来的数据为null");
            }else if(jsonObject != null && jsonObject.getInt("errcode")!=0 ){
                logger.info("发送客服消息时出现异常,异常码:{},异常消息:{}",jsonObject.get("errcode"),jsonObject.get("errmsg"));
            }
            logger.info("发送客服消息结果"+result);
            boolean flag=false;

            if (null != result) {
                errorCode = jsonObject.getString("errcode");
                String errorMsg = jsonObject.getString("errmsg");
               /* errorCode = result.getErrcode();
                String errorMsg = result.getErrmsg();*/
                if (Integer.parseInt(errorCode) == 0) {
                    flag=true;
                    logger.info("发送客服消息成功errorCode:{" + errorCode + "},errmsg:{" + errorMsg + "}");
                } else {
                    logger.info("发送客服消息失败errorCode:{" + errorCode + "},errmsg:{" + errorMsg + "}");
                }
            }
            //发送客服消息后存消息到数据库
            if(flag){
              //  saveWechatMessage( messageType, openId, content, mediaId, null, wechatCountOpenId, null, userId,flag);
            }
            if (!flag){
                logger.info("发送客服消息失败");
                return errorCode;
            }
            logger.info("发送客服消息结束，errorCode:{}",errorCode);
        } catch (Exception e) {
            logger.info("调用发送客服消息的接口出错了");
            logger.error("listWechatContactByWechatName()有误 [Message：{},StackTrace:{}]", e.getMessage(), e
                    .getStackTrace());
        }
        return errorCode;
    }

    @Override
    public boolean getWechatFansInfo() {

       return false;
    }

    /**
     * 微信客服发送和消息存入数据库
     *
     * @return
     */
    /*@Override
    public WechatInteraction saveWechatMessage(String messageType,
                                               String openId,
                                               String content,
                                               String mediaId,
                                               String MsgID,
                                               String wechatCountOpenId,
                                               String imageUrl,
                                               Integer userId,
                                               boolean sendMessageResult) {
        logger.info("开始进来保存粉丝或客服发送的消息,参数[messageType:{},openId:{},content:{},mediaId:{},MsgId:{},wechatCountOpenId:{},imageUrl:{},userId:{},sendMessageResult:{}]"
                ,messageType,openId,content,mediaId,MsgID,wechatCountOpenId,imageUrl,userId,sendMessageResult);
        WechatInteraction wechatInteraction=new WechatInteraction();
        wechatInteraction.setOpenid(openId);
        if (WechatMessgeTypeEnum.from(messageType) != null) {
            wechatInteraction.setMsgType(WechatMessgeTypeEnum.from(messageType).getValue());
        }
        List<Integer> contactIdList = contactService.getContactByWechatOpenId(openId, userId);
        //根据openid查询contactid
        Integer contactId = null;
        if (contactIdList != null) {
            if (contactIdList.size() > 0) {
                contactId = contactIdList.get(0);
                wechatInteraction.setContactId(contactId);
            }
        }
        if (contactId == null) {
            logger.info("发送客服消息没有查询到对应的联系人id");
            return null;
        }
        //设置保存粉丝发送的消息参数
        if (MsgID!=null){
            logger.info("开始保存粉丝消息");
            wechatInteraction.setMsgid(MsgID);
            if (WechatMessgeTypeEnum.TEXT.getDesc().equals(messageType)) {
                wechatInteraction.setContent(content);
            }
            if (WechatMessgeTypeEnum.IMAGE.getDesc().equals(messageType)) {
                wechatInteraction.setContent(imageUrl);
            }
            wechatInteraction.setMsgid(MsgID);
            wechatInteraction.setInteractionType(WechatInteractionMessageTypeEnum.CONTACT_MESSAGE.getValue());
            //设置保存客服发送的消息参数
        }else{
            logger.info("开始保存客服消息");
            if (!sendMessageResult){
                wechatInteraction.setStatus(-1);
            }
            if (WechatMessgeTypeEnum.TEXT.getDesc().equals(messageType)) {
                wechatInteraction.setContent(content);
            }
            if (WechatMessgeTypeEnum.IMAGE.getDesc().equals(messageType)) {
                wechatInteraction.setContent(mediaId);
            }
            if (WechatMessgeTypeEnum.MPNEWS.getDesc().equals(messageType)) {
                wechatInteraction.setContent(mediaId);
            }
            if (WechatMessgeTypeEnum.NEWS.getDesc().equals(messageType)) {
                wechatInteraction.setContent(mediaId);
            }
            wechatInteraction.setIsRead(WechatInteractionEnum.IS_READ.getVal());
            wechatInteraction.setInteractionType(WechatInteractionMessageTypeEnum.SERVER_MESSAGE.getValue());
        }
        wechatInteraction.getContent().replace("'", "\'");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("wechatInteraction", wechatInteraction);
        int flag=wechatInteractionMapper.insertSelective(map);
        logger.info("mybatis返回的交互表的id:{}",map.get("id"));
        String id=map.get("id").toString();
        int wechatInteractionId= Integer.parseInt(id);
        logger.info("新增的交互表的wechatInteractionId为："+wechatInteractionId);
        if (flag<1){
            logger.info("保存消息失败");
            return null;
        }
        if (MsgID!=null) {
            //用户发消息添加用户轨迹
            //link:"/interaction/toWechatMessageList?selectType=2&id="
            //调用轨迹的公共方法 点击邮件链接：xxxx。com   by ： 打开邮件 ：常规邮件任务名称/自动化邮件任务名称
            if(WechatMessgeTypeEnum.TEXT.getDesc().equals(messageType)){
                contactsOperationToolService.executeContactsOne(userId, contactId, OperationTypeEnum.WECHAT_SEND_TEXT_MESSAGES.getValue(),  wechatInteractionId, "点击查看", null,null, null);
            }
            if (WechatMessgeTypeEnum.IMAGE.getDesc().equals(messageType)) {
                contactsOperationToolService.executeContactsOne(userId, contactId, OperationTypeEnum.WECHAT_SEND_IMAGE_MESSAGES.getValue(),  wechatInteractionId, "点击查看", null,null, null);
            }
        }
        logger.info("保存消息结束wechatInteraction:{}",wechatInteraction);
        return wechatInteraction;
    }
*/

}
