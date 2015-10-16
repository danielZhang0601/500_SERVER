package com.faceooo.nian.controllers;

import com.faceooo.nian.model.ImageDTO;
import com.faceooo.nian.service.QiniuService;
import com.faceooo.nian.service.SouvenirService;
import com.faceooo.nian.utils.RestConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanlin on 15/8/17.
 * 共享微信等功能
 */

@Controller
public class SnsController {

    @Autowired
    QiniuService qiniuService;

    @Autowired
    SouvenirService souvenirService;

    @RequestMapping(value = RestConstants.USER_WX)
    @ResponseBody
    public JSONObject shareWeixin(String userid,String souid){
        //TODO 共享微信
        JSONObject returnJson = new JSONObject();
        String  error = "false";
        //获取物品属性获取用户对物品的评论
        JSONObject souInfoJson =souvenirService.getSouvenirInfo(souid);
        if (souInfoJson!=null){
            //获取物品小图片list表
            Map paramMap = new HashMap<String ,String>();
            paramMap.put("souid",souid);
            paramMap.put("userid",userid);
            List<ImageDTO> souSmallImages = qiniuService.getSouSmallImagesList(paramMap);
            JSONArray souSmallImagesJson = new JSONArray();
            if(souSmallImages!=null){
                for(ImageDTO imageDTO : souSmallImages){
                    souSmallImagesJson.add(imageDTO.getDtoToJson());
                }
            }
            souInfoJson.put("souimagelist",souSmallImagesJson);
            souInfoJson.put("souimagelistcount",souSmallImagesJson.size());

        }else{
            error = "true";
            returnJson.put("msg", "藏品不存在！");
        }


        returnJson.put("error", error);
        returnJson.put("result", souInfoJson);
        return returnJson;
    }

}
