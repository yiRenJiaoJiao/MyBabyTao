package com.babyTao.app.controller.base;


import com.baby.common.response.Response;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SEELE on 2017/7/20.
 */
@Component
public abstract class BaseController {
    Logger logger = Logger.getLogger(BaseController.class);
    final String ERROR_PREFIX="error_";




    //取出UserId
    public Integer getStarUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Integer starUserId = (Integer) request.getSession().getAttribute("userId");
        return starUserId;
    }


/*
    //取出StarUser
    public StarUser getStarUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        StarUser starUser = (StarUser) request.getSession().getAttribute("starUser");
        return starUser;
    }
*/


    /**
     * 页面请求参数验证
     */
    public boolean validWebRequestParam(BindingResult bindingResult, ModelAndView modelAndView){
        boolean flag=false;
        if(bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError err : errors) {
                modelAndView.addObject(ERROR_PREFIX+err.getField(),err.getDefaultMessage());
                System.out.println("ObjectName:" + err.getObjectName() + "\tFieldName:" + err.getField()
                        + "\tFieldValue:" + err.getRejectedValue() + "\tMessage:" + err.getDefaultMessage() + "\tCode:");
            }
        }
        else{
            flag=true;
        }
        return flag;
    }
    /**
     * ajax请求参数验证
     */

    public boolean validAjaxRequestParam(BindingResult bindingResult, Response response){
        boolean flag=false;
        if(bindingResult.hasErrors()){
            Map<String,Object> map=new HashMap<String,Object>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError err : errors) {
                map.put(ERROR_PREFIX + err.getField(), err.getDefaultMessage());
                System.out.println("ObjectName:" + err.getObjectName() + "\tFieldName:" + err.getField()
                        + "\tFieldValue:" + err.getRejectedValue() + "\tMessage:" + err.getDefaultMessage() + "\tCode:");
            }
            response.setData(map);
        }
        else{
            flag=true;
        }
        return flag;
    }

}
