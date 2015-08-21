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
        //ModelAndView mav = new ModelAndView();
        UserinfoDTO userinfo= new UserinfoDTO();
        userinfo.setId(userid);
        userinfo.setUserphone(userphone);
        return  userService.getUserHomeInfo(userinfo);
    }

    @RequestMapping(value = RestConstants.USER_REG)
    @ResponseBody
    public JSONObject getUserReg(UserinfoDTO userinfo) {
        String msg = userService.insertUserinfo(userinfo);
        if(msg==null){
            return userinfo.getDtoToJson();
        }else{
            JSONObject msgJsp = new JSONObject();
            msgJsp.put("mag",msg);
            return msgJsp;
        }

    }


    @RequestMapping(value = RestConstants.USER_LOGIN)
    @ResponseBody
    public JSONObject userLogin(String userphone,String userpwd) {
        UserinfoDTO userinfo= new UserinfoDTO();
        userinfo.setUserpwd(userpwd);
        userinfo.setUserphone(userphone);
        userinfo= userService.userLogin(userinfo);
        if(userinfo!=null){
            return userinfo.getDtoToJson();
        }else{
            JSONObject msgJsp = new JSONObject();
            msgJsp.put("mag","该用户还未注册！");
            return msgJsp;
        }
    }


}
