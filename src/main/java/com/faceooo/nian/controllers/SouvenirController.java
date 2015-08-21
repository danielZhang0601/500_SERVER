package com.faceooo.nian.controllers;

import com.faceooo.nian.model.RecordinfoDTO;
import com.faceooo.nian.model.SouvenirDTO;
import com.faceooo.nian.model.SouvenirtypeDTO;
import com.faceooo.nian.service.SouvenirService;
import com.faceooo.nian.utils.RestConstants;
import com.faceooo.nian.utils.SysUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuanlin on 15/8/17.
 * 所有和藏品有关的操作
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

    @RequestMapping(value = RestConstants.UPDATE_SOUTYPE)
    @ResponseBody
    public JSONObject updateSoutype(SouvenirtypeDTO soutypedto) {
        JSONObject returnJson = new JSONObject();
        String error= "";
        JSONObject result = new JSONObject();

        if(souvenirService.updateSoutype(soutypedto)){
            error="false";
        }else{
            error="true";
        }
        result=soutypedto.getDtoToJson();

        returnJson.put("error",error);
        returnJson.put("result",result);
        return returnJson;
    }

    @RequestMapping(value = RestConstants.CREATE_SOUTYPE)
    @ResponseBody
    public JSONObject creatSouTypes(String soutypename,String userid) {
        JSONObject returnJson = new JSONObject();
        String error= "";
        JSONObject result = new JSONObject();
        SouvenirtypeDTO soutypedto = new SouvenirtypeDTO();
        soutypedto.setUserid(userid);
        soutypedto.setTypename(soutypename);
        souvenirService.creatSouTypes(soutypedto);
        if(soutypedto.getId()!=null){
            error="false";
            result=soutypedto.getDtoToJson();
        }else{
            error="true";
            result.put("msg","物品分类已经存在！");
        }
        returnJson.put("error",error);
        returnJson.put("result",result);
        return returnJson;
    }

    @RequestMapping(value = RestConstants.SOU_SEARCH)
    public ModelAndView getSouvenirSearch(String souvenircode,String userid) {
        ModelAndView mav = new ModelAndView();
        Map<String,String> paramMap= new HashMap<String,String>();
        paramMap.put("souvenircode", souvenircode);
        paramMap.put("userid", userid);
        mav.addObject("souSearchInfoJson", souvenirService.getSouvenirSearch(paramMap));
        return mav;
    }

    @RequestMapping(value = RestConstants.SOUCHARGE_TYPE)
    public void chargeSoutype(String soutypeid,String souid){
        SouvenirDTO soudto = new SouvenirDTO();
        soudto.setId(souid);
        soudto.setSouvenirtypeid(soutypeid);
        SouvenirDTO oldsouinfo=souvenirService.querySouBaseInfoForID(soudto);
        if(!soutypeid.equals(oldsouinfo.getSouvenirtypeid())){
            souvenirService.chargeSoutype(soudto);
        }
    }

    @RequestMapping (value = RestConstants.UPDATE_SOUBASEINFO)
    @ResponseBody
    public JSONObject  updateSouBaseInfo(SouvenirDTO soudto){
        JSONObject returnJson = new JSONObject();
        String error= "";
        JSONObject result = new JSONObject();
        if(souvenirService.updateSouBaseInfo(soudto)){
            error = "false";
            result=soudto.getDtoToJson();
        }else{
            error = "true";
            result.put("msg","更新信息失败！");
        }

        returnJson.put("error",error);
        returnJson.put("result", result);
        return returnJson;
    }

    @RequestMapping(value = RestConstants.CREATE_RECORD)
    public void createSouRecord(String souid,String recordcontent){
        RecordinfoDTO recorddto = new RecordinfoDTO();
        recorddto.setId(SysUtils.getrecordid());
        recorddto.setSouvenirid(souid);
        recorddto.setTimerecord(SysUtils.getNowTimeStr());
        recorddto.setContent(recordcontent);
        souvenirService.createSouRecord(recorddto);
    }

    @RequestMapping(value = RestConstants.SOU_INFO)
    public ModelAndView getSouvenirInfo(String souvenirid) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("souInfoJson", souvenirService.getSouvenirInfo(souvenirid));
        return mav;
    }

    @RequestMapping(value = RestConstants.DELETE_RECORD)
    public void  deleteSouRecord(String recordid){
        RecordinfoDTO recorddto = new RecordinfoDTO();
        recorddto.setId(recordid);
        souvenirService.deleteSouRecord(recorddto);
    }

    @RequestMapping(value = RestConstants.UPDATE_RECORD)
    public void  updateSouRecord(String recordid,String recordcontent){
        RecordinfoDTO recorddto = new RecordinfoDTO();
        recorddto.setId(recordid);
        recorddto.setTimerecord(SysUtils.getNowTimeStr());
        recorddto.setContent(recordcontent);
        souvenirService.updateSouRecord(recorddto);
    }

    @RequestMapping(value = RestConstants.CREATE_SOU)
    @ResponseBody
    public JSONObject createSou(String userid){
        JSONObject returnJson = new JSONObject();
        String error= "";
        JSONObject result = new JSONObject();
        SouvenirDTO soudto = new SouvenirDTO();
        soudto.setUserid(userid);

        if(souvenirService.createSouvenir(soudto)){
            error="false";
            result=soudto.getDtoToJson();
        }else{
            error="true";
            result.put("msg","新增物品失败！");
        }

        returnJson.put("error",error);
        returnJson.put("result",result);
        return returnJson;
    }

    @RequestMapping(value = RestConstants.DELETE_IMAGES)
    public void deleteImages(String imageId){//在image表删除记录，在clearqiniu表增加记录
        souvenirService.deleteImage(imageId);
    }

    @RequestMapping(value = RestConstants.SOU_LIST_TYPE)
    public ModelAndView souListByType(String souTypeID,String userid){
        ModelAndView mav = new ModelAndView();
        SouvenirDTO soudto = new SouvenirDTO();
        soudto.setUserid(userid);
        soudto.setSouvenirtypeid(souTypeID);
        mav.addObject("souListJson", souvenirService.getSouvenirListForType(soudto));
        return mav;
    }

}
