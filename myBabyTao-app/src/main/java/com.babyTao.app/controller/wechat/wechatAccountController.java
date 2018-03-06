package com.babyTao.app.controller.wechat;


import com.baby.common.entity.wechat.TextMessage;
import com.baby.common.enums.WechatMessgeTypeEnum;
import com.baby.common.response.Response;


import com.baby.common.utils.data.DateType;
import com.baby.common.utils.wechat.CheckUtil;
import com.baby.common.utils.wechat.WechatMessageUtil;
import com.myBabyTao.api.service.WechatService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Administrator on 2018/1/25.
 */
@Controller
public class wechatAccountController {

    @Resource
    private WechatService wechatService;

    @Value("#{configProperties['token']}")
    String token;

    org.slf4j.Logger logger = LoggerFactory.getLogger(wechatAccountController.class);

    /**
     * 接收微信验证
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/wechat")
    public void wechat(HttpServletRequest request ,HttpServletResponse response) throws IOException {
        String method = request.getMethod();
        PrintWriter out = response.getWriter();
        if("GET".equals(method)){
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            if (CheckUtil.checkSignature(signature, timestamp, nonce,token)) {
                out.print(echostr);
            }
            return ;
        }
        if("POST".equals(method)){
            try {
                Map<String, String> map = DateType.xmlToMap(request);
                String responseMessage = wechatService.processRequest(map);
                out.print(responseMessage);
                out.flush();
            } catch (Exception e) {
                logger.info("回复消息出现异常,异常信息:{}",e);
            } finally {
                out.close();
            }
        }
    }

    /**
     * 获取公众号粉丝列表
     * @return
     */
    public Response getWechatFansInfo(){
        try {
            Map<String, Object> map = new HashMap<>();
            boolean isSuccess = wechatService.getWechatFansInfo();
            if(isSuccess){
                logger.info("获取粉丝列表成功");
                return Response.addSuccessResponse("获取粉丝列表成功",map);
            }else{
                logger.info("获取粉丝列表失败");
                return Response.addFailedResponse("获取粉丝列表失败");
            }
        } catch (Exception e) {
            logger.info("获取粉丝列表数据出现异常,异常信息:{}",e);
            return Response.addFailedResponse("获取粉丝列表出现异常,请稍后再试");
        }

    }

    /**
     * 接收来自微信发来的消息
     *
     * @param out
     * @param request
     * @param response
     */
    /*@RequestMapping(value = "/wechat", method = RequestMethod.POST)
    public void wechatServicePost( HttpServletRequest request, HttpServletResponse response) throws IOException {
      request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        try {
            String responseMessage = wechatService.processRequest(request);
            out.print(responseMessage);
            out.flush();
        } catch (Exception e) {
            logger.info("回复消息出现异常,异常信息:{}",e);
        } finally {
            out.close();
        }
    }*/


    /**
     * 发送客服消息，1：调用微信接口2：将消息存入数据库
     * content存列表的样式
     * description存聊天框的样式
     */
    @RequestMapping("sendServerMessage")
    @ResponseBody
    public Response sendServerMessage(@RequestParam(value="messageType", defaultValue="text")String messageType,
                                      @RequestParam(value="openId", defaultValue="")String openId,
                                      @RequestParam(value="content", defaultValue="")String content,
                                      @RequestParam(value="mediaId", defaultValue="")String mediaId,
                                      @RequestParam(value="wechatCountOpenId", defaultValue="")String wechatCountOpenId
    ){
       /* Integer userId=getStarUserId();
        if (userId==null|"".equals(userId)){
            Response.addFailedResponse("登录超时，请重新登录");
        }*/
        Integer userId = 133;
        String stringJsonObject=null;
        if (messageType==null|"".equals(messageType)){
            return Response.addFailedResponse("消息类型不能为空");
        }
        if (openId==null|"".equals(openId)){
            return Response.addFailedResponse("openid不能为空");
        }
        if (WechatMessgeTypeEnum.from(messageType)==null){
            return Response.addFailedResponse("消息类型不能为空");
        }
        if (content==null&mediaId==null){
            return Response.addFailedResponse("消息不能为空");
        }
        String flag= wechatService.sendWechatServerMessage(messageType, openId, content, mediaId, wechatCountOpenId,userId);
        if ("45047".equals(flag)){
            logger.info("您已连续发送 20 条消息，无法继续发送，请在用户回复后再尝试发送");
            return Response.addFailedResponse("您已连续发送 20 条消息，无法继续发送，请在用户回复后再尝试发送");
        }
        if("45015".equals(flag)){
            logger.info("用户发送的消息超过了48小时，无法回复！！");
            return Response.addFailedResponse("用户发送的消息超过了48小时，无法回复");
        }
        if (!"0".equals(flag)){
            return Response.addFailedResponse("发送客服消息失败");
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("wechatInteraction",flag);
        logger.info("客服消息保存成功");
        return Response.addSuccessResponse("客服消息发送成功",map);
    }
}
