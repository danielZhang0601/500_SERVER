package com.faceooo.nian.controllers;

/**
 * Created by qin on 15/8/14.
 */
import com.faceooo.nian.model.UserinfoDTO;
import com.faceooo.nian.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    DemoService demoService;

    @RequestMapping(value = "/demo")
    public ModelAndView index(String username) {
        ModelAndView mav = new ModelAndView("test");
        String msg = "Running IndexController.index() method";
        UserinfoDTO demo = demoService.getDemo(username);
        msg = demo.getId();
        System.out.println("test mybatis: phone = " + demo.getUserphone());
        mav.addObject("msg", msg);
        return mav;
    }
}
