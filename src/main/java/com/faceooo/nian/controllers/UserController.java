package com.faceooo.nian.controllers;

import com.faceooo.nian.model.UserinfoDTO;
import com.faceooo.nian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.faceooo.nian.utils.RestConstants;

/**
 * Created by yuanlin on 15/8/17.
 * 所有和用户有关的操作
 */


@Controller
public class UserController {

//    @Autowired
//    UserService userService;
//
//    @RequestMapping(value = RestConstants.USER_HOME)
//    public ModelAndView userhome(String userid,String userphone) {
//        ModelAndView mav = new ModelAndView();
//        UserinfoDTO userinfo= new UserinfoDTO();
//        userinfo.setId(userid);
//        userinfo.setUserphone(userphone);
//        mav.addObject("userHomeJson", userService.getUserHomeInfo(userinfo));
//        return mav;
//    }
//
//    @RequestMapping(value = RestConstants.USER_REG)
//    public ModelAndView getUserReg(UserinfoDTO userinfo) {
//        ModelAndView mav = new ModelAndView();
//        userService.insertUserinfo(userinfo);
//        mav.addObject("userHomeJson", userinfo.getDtoToJson());
//        return mav;
//    }
//
//
//    @RequestMapping(value = RestConstants.USER_LOGIN)
//    public ModelAndView userLogin(String userphone,String userpwd) {
//        ModelAndView mav = new ModelAndView();
//        UserinfoDTO userinfo= new UserinfoDTO();
//        userinfo.setUserpwd(userpwd);
//        userinfo.setUserphone(userphone);
//        userinfo= userService.userLogin(userinfo);
//        mav.addObject("userLoginInfoJson",userinfo.getDtoToJson());
//        return mav;
//    }


}
