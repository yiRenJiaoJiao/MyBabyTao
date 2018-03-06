package com.babyTao.app.controller;

import com.baby.common.response.Response;
import com.myBabyTao.api.service.MyTestService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkItemGetRequest;
import com.taobao.api.response.TbkItemGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Administrator on 2017/12/26.
 */
@Controller
@RequestMapping("/test")
public class MyTestController {
    @Resource
    private MyTestService myTestService;

    @RequestMapping("/getHellow")
    @ResponseBody
    public Map getHellw(){
        Map<String, Object> map = new HashMap<>();
        String val= myTestService.getHellow();
        map.put("hellow",val);
        return map;
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/myFirstJsp/UserLogin";
    }

    @RequestMapping("/html5Test")
    public String html5Test(){
        return "/myFirstJsp/html5Test";
    }
    @RequestMapping("/toUserPage")
    public String toUserPage(){
        return "/myFirstJsp/test";
    }
    @RequestMapping("/getTaoTao")
    @ResponseBody
    public Response getTaoTao() throws ApiException {
       // String url = "http://gw.api.tbsandbox.com/router/rest";
        String url ="http://gw.api.taobao.com/router/rest";
        String appkey = "24790972";
        String secret = "8395003d4650b00e4a16863264d74615";
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkItemGetRequest req = new TbkItemGetRequest();
        req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick");
        req.setQ("女装");
        req.setCat("16,18,19,20");
        req.setItemloc("上海");
        req.setSort("tk_rate_des");
        req.setIsTmall(false);
        req.setIsOverseas(false);
        req.setStartPrice(0L);
        req.setEndPrice(100L);
        req.setStartTkRate(200L);
        req.setEndTkRate(500L);
        req.setPlatform(1L);
        req.setPageNo(123L);
        req.setPageSize(20L);
        TbkItemGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
        Map<String, Object> map = new HashMap<>();
        map.put("taobao",rsp.getBody());
        return Response.addSuccessResponse("ok",map);
    }
}
