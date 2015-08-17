package com.faceooo.nian.controllers;

import com.faceooo.nian.service.SouvenirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.RestConstants;

/**
 * Created by yuanlin on 15/8/17.
 */
@Controller
public class SouvenirController {

    @Autowired
    SouvenirService souvenirService;

    @RequestMapping(value = RestConstants.DELETE_SOU)
    public void deleteSou(String souid) {
       souvenirService.deleteSou(souid);
    }

    @RequestMapping(value = RestConstants.DELETE_SOUTYPE)
    public void deleteSoutype(String soutypeid) {
        souvenirService.deleteSoutype(soutypeid);
    }




}
