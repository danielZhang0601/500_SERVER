package com.faceooo.nian.controllers;

/**
 * Created by qin on 15/8/14.
 */
import com.faceooo.nian.model.DemoDTO;
import com.faceooo.nian.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    DemoService demoService;

    @RequestMapping(value = "/demo")
    public ModelAndView index(@RequestParam(value="uname", defaultValue = "ql", required = false) String username) {
        ModelAndView mav = new ModelAndView("test");
        String msg = "";
        DemoDTO demo = demoService.getDemo(username);
        msg = demo.getPhone();
        System.out.println("test mybatis: phone = " + demo.getNick());
        mav.addObject("msg", msg);
        return mav;
    }
}
