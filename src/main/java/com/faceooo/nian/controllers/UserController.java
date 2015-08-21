package com.faceooo.nian.controllers;

import com.faceooo.nian.model.UserinfoDTO;
import com.faceooo.nian.service.UserService;
import com.faceooo.nian.utils.RestConstants;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yuanlin on 15/8/17.
 * 所有和用户有关的操作
 */


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = RestConstants.USER_HOME)
    @ResponseBody
    public JSONObject userhome(String userid,String userphone) {
        JSONObject returnJson = new JSONObject();
        String error= "";
        JSONObject result = new JSONObject();

        UserinfoDTO userinfo= new UserinfoDTO();
        userinfo.setId(userid);
        userinfo.setUserphone(userphone);

        if(userService.checkUserIsExists(userinfo)){
            error="true";
            result.put("msg","用户不存在！");
        }else{
            result =userService.getUserHomeInfo(userinfo);
            error="false";
        }

        returnJson.put("error",error);
        returnJson.put("result",result);
        return returnJson;
    }

    @RequestMapping(value = RestConstants.USER_REG)
    @ResponseBody
    public JSONObject getUserReg(UserinfoDTO userinfo) {
        JSONObject returnJson = new JSONObject();
        String error= "";
        JSONObject result = new JSONObject();
        String msg = userService.insertUserinfo(userinfo);
        if(msg==null){
            error= "false";
            returnJson=userinfo.getDtoToJson();
        }else{
            result.put("msg",msg);
            error= "true";
        }
        returnJson.put("error",error);
        returnJson.put("result",result);
        return returnJson;
    }


    @RequestMapping(value = RestConstants.USER_LOGIN)
    @ResponseBody
    public JSONObject userLogin(String userphone,String userpwd) {
        JSONObject returnJson = new JSONObject();
        String error= "";
        JSONObject result = new JSONObject();
        UserinfoDTO userinfo= new UserinfoDTO();
        userinfo.setUserpwd(userpwd);
        userinfo.setUserphone(userphone);
        userinfo= userService.userLogin(userinfo);
        if(userinfo!=null){
            error= "false";
            result=userinfo.getDtoToJson();
        }else{
            result.put("msg","该用户还未注册！");
            error= "true";
        }
        returnJson.put("error",error);
        returnJson.put("result",result);
        return returnJson;
    }


}
