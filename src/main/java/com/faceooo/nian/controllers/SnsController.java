package com.faceooo.nian.controllers;

import com.faceooo.nian.utils.RestConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yuanlin on 15/8/17.
 * 共享微信等功能
 */

@Controller
public class SnsController {

    @RequestMapping(value = RestConstants.USER_WX)
    public ModelAndView shareWeixin(){
        //TODO 共享微信
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", "shareWeixin");
        return mav;
    }

}
