package com.faceooo.nian.controllers;

import com.faceooo.nian.service.ShaibaoService;
import com.faceooo.nian.utils.RestConstants;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/15.
 */
@Controller
public class ShaibaoController {

    @Autowired
    ShaibaoService shaibaoService;

    @RequestMapping(value = RestConstants.CREATE_SHAIBAO_LABEL)
    @ResponseBody
    public JSONObject createshaibaolabel(String labelname,String labelen){
        JSONObject returnJson = new JSONObject();
        Map<String ,String> paramMap = new HashMap<>();
        paramMap.put("labelname",labelname);
        paramMap.put("labelen",labelen);
        shaibaoService.createshaibaolabel(paramMap);
        return returnJson;
    }
}
