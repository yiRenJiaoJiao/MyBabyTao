package com.babyTao.app.controller;

import com.baby.common.response.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import  java.util.Map;

/**
 * Created by Administrator on 2017/12/27.
 */
@Controller
@RequestMapping("/myBabyTaoController")
public class MyBabyTaoController {

    @RequestMapping("/findDate")
    @ResponseBody
    public Response findDate(){
        Map<String, Object> map = new HashMap<>();
        map.put("data","ok");
        return Response.addSuccessResponse("成功",map);
    }
}
