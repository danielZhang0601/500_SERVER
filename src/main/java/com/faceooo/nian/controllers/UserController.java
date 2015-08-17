package com.faceooo.nian.controllers;

import com.faceooo.nian.model.UserinfoDTO;
import com.faceooo.nian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import utils.RestConstants;

/**
 * Created by yuanlin on 15/8/17.
 */


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = RestConstants.USER_HOME)
    public ModelAndView userhome(String userid,String userphone) {
        ModelAndView mav = new ModelAndView();
        UserinfoDTO userinfo= new UserinfoDTO();
        userinfo.setId(userid);
        userinfo.setUserphone(userphone);
        mav.addObject("userhomejson", userService.getUserHomeInfo(userinfo));
        return mav;
    }



}
