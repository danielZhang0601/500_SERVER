package com.faceooo.nian.controllers;import com.faceooo.nian.model.ImageDTO;import com.faceooo.nian.service.QiniuService;import com.faceooo.nian.service.SouvenirService;import com.faceooo.nian.utils.RestConstants;import com.google.gson.JsonObject;import jdk.nashorn.internal.ir.LiteralNode;import org.apache.commons.lang3.ArrayUtils;import org.apache.commons.lang3.StringUtils;import org.json.simple.JSONArray;import org.json.simple.JSONObject;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.ResponseBody;import org.springframework.web.multipart.MultipartFile;import org.springframework.web.multipart.MultipartHttpServletRequest;import org.springframework.web.multipart.commons.CommonsMultipartResolver;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import java.io.IOException;import java.util.HashMap;import java.util.Iterator;import java.util.List;import java.util.Map;/** * Created by yuanlin on 15/8/17. * 七牛图片云存储图片的名称为   userid-souid-imageid.jpg */@Controllerpublic class QiniuController {    @Autowired    QiniuService qiniuService;    @Autowired    SouvenirService souvenirService;    @RequestMapping(value = RestConstants.CREATE_IMAGE)    public void createimage(HttpServletRequest request,HttpServletResponse response) throws IOException {        String userid= request.getParameter(RestConstants.USER_ID);        String souid = request.getParameter(RestConstants.SOUVENIR_ID);        //创建一个通用的多部分解析器        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());        //判断 request 是否有文件上传,即多部分请求        if(multipartResolver.isMultipart(request)){            //转换成多部分request            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;            //取得request中的所有文件名            Iterator<String> iter = multiRequest.getFileNames();            while(iter.hasNext()){                //记录上传过程起始时的时间，用来计算上传时间                int pre = (int) System.currentTimeMillis();                //取得上传文件                MultipartFile file = multiRequest.getFile(iter.next());                if(file != null){                    //取得当前上传文件的文件名称                    String myFileName = file.getOriginalFilename();                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在                    if(StringUtils.isNotEmpty(myFileName)){                        //先在本地数据库创建记录，生成上传的图片名称：userid-sounenirid-imageid                        String imageid=souvenirService.createImages(userid,souid);                        if(StringUtils.isNotEmpty(imageid)){                            String qiniuImageName = userid+"-"+souid+"-"+imageid;                            qiniuService.uploadToQiniu(file.getBytes(),qiniuImageName);                        }                    }                }            }        }    }    @RequestMapping(value = RestConstants.GET_IMAGE_URL)    @ResponseBody    public JSONArray getSouSmallImagesList(String souid,String userid){        Map paramMap = new HashMap<String ,String>();        paramMap.put("souid",souid);        paramMap.put("userid",userid);        List<ImageDTO> souSmallImages = qiniuService.getSouSmallImagesList(paramMap);        JSONArray souSmallImagesJson = new JSONArray();        if(souSmallImages!=null){            for(ImageDTO imageDTO : souSmallImages){                souSmallImagesJson.add(imageDTO.getDtoToJson());            }        }        return souSmallImagesJson;    }}