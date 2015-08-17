package com.faceooo.nian.controllers;

import com.faceooo.nian.utils.RestConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yuanlin on 15/8/17.
 * 七牛图片云存储
 */
@Controller
public class QiniuController {

    @RequestMapping(value = RestConstants.CREATE_IMAGE)
    public void createimage(){
        //TODO 创建图片
    }

    @RequestMapping(value = RestConstants.DELETE_IMAGES)
    public void deleteimages(){
        //TODO 删除图片
    }

}
