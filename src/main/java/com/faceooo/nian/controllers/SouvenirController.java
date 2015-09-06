package com.faceooo.nian.controllers;

import com.faceooo.nian.model.RecordinfoDTO;
import com.faceooo.nian.model.SouvenirDTO;
import com.faceooo.nian.model.SouvenirtypeDTO;
import com.faceooo.nian.service.SouvenirService;
import com.faceooo.nian.utils.RestConstants;
import com.faceooo.nian.utils.SysUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public JSONObject deleteSou(String souid,String userid) {
        JSONObject returnJson = new JSONObject();
        String error = "";
        JSONObject result = new JSONObject();
        if(souvenirService.deleteSou(souid,userid)){
            error= "false";
            result.put("msg","删除成功");
        }else{
            error= "true";
            result.put("msg","删除失败");
        }
        returnJson.put("error", error);
        returnJson.put("result", result);
        return returnJson;
    }

    @RequestMapping(value = RestConstants.DELETE_SOUTYPE)
    @ResponseBody
    public JSONObject deleteSoutype(String soutypeid) {
        JSONObject returnJson = new JSONObject();
        String error = "";
        JSONObject result = new JSONObject();
        if(souvenirService.deleteSoutype(soutypeid)){
            error= "false";
            result.put("msg","删除成功");
        }else{
            error= "true";
            result.put("msg","删除失败");
        }
        returnJson.put("error", error);
        returnJson.put("result", result);
        return returnJson;
    }

    @RequestMapping(value = RestConstants.UPDATE_SOUTYPE)
    @ResponseBody
    public JSONObject updateSoutype(SouvenirtypeDTO soutypedto) {
        JSONObject returnJson = new JSONObject();
        String error = "";
        JSONObject result = new JSONObject();

        if (souvenirService.updateSoutype(soutypedto)) {
            error = "false";
        } else {
            error = "true";
        }
        result = soutypedto.getDtoToJson();

        returnJson.put("error", error);
        returnJson.put("result", result);
        return returnJson;
    }

    @RequestMapping(value = RestConstants.CREATE_SOUTYPE)
    @ResponseBody
    public JSONObject creatSouTypes(String soutypename, String userid) {
        JSONObject returnJson = new JSONObject();
        String error = "";
        JSONObject result = new JSONObject();
        SouvenirtypeDTO soutypedto = new SouvenirtypeDTO();
        soutypedto.setUserid(userid);
        soutypedto.setTypename(soutypename);
        souvenirService.creatSouTypes(soutypedto);
        if (soutypedto.getId() != null) {
            error = "false";
            result = soutypedto.getDtoToJson();
        } else {
            error = "true";
            result.put("msg", "物品分类已经存在！");
        }
        returnJson.put("error", error);
        returnJson.put("result", result);
        return returnJson;
    }

    @RequestMapping(value = RestConstants.SOU_SEARCH)
    @ResponseBody
    public JSONObject getSouvenirSearch(String soucode, String userid) {
        JSONObject returnJson = new JSONObject();
        String error = "";
        JSONObject result = new JSONObject();
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("souvenircode", soucode);
        paramMap.put("userid", userid);

        result = souvenirService.getSouvenirSearch(paramMap);
        error = "false";

        returnJson.put("error", error);
        returnJson.put("result", result);
        return returnJson;
    }

    @RequestMapping(value = RestConstants.SOUCHARGE_TYPE)
    @ResponseBody
    public JSONObject chargeSoutype(SouvenirDTO soudto) {
        JSONObject returnJson = new JSONObject();
        String error = "";
        JSONObject result = new JSONObject();

        SouvenirDTO oldsouinfo = souvenirService.querySouBaseInfoForID(soudto);
        if (!soudto.getSouvenirtypeid().equals(oldsouinfo.getSouvenirtypeid())) {
            if (souvenirService.chargeSoutype(soudto)) {
                oldsouinfo.setSouvenirtypeid(soudto.getSouvenirtypeid());
                error = "false";
                result = oldsouinfo.getDtoToJson();
            } else {
                error = "true";
                result.put("msg", "更新分类失败");
            }
        } else {
            error = "true";
            result.put("msg", "类别未变");
        }
        returnJson.put("error", error);
        returnJson.put("result", result);
        return returnJson;
    }

    @RequestMapping(value = RestConstants.UPDATE_SOUBASEINFO)
    @ResponseBody
    public JSONObject updateSouBaseInfo(SouvenirDTO soudto) {
        JSONObject returnJson = new JSONObject();
        String error = "";
        JSONObject result = new JSONObject();
        if (souvenirService.updateSouBaseInfo(soudto)) {
            error = "false";
            result = soudto.getDtoToJson();
        } else {
            error = "true";
            result.put("msg", "更新信息失败！");
        }

        returnJson.put("error", error);
        returnJson.put("result", result);
        return returnJson;
    }

    @RequestMapping(value = RestConstants.CREATE_RECORD)
    @ResponseBody
    public JSONObject createSouRecord(RecordinfoDTO recorddto) {
        JSONObject returnJson = new JSONObject();
        String error = "";
        JSONObject result = new JSONObject();
        if (souvenirService.createSouRecord(recorddto)) {
            error = "false";
            result = recorddto.getDtoToJson();
        } else {
            error = "true";
            result.put("msg", "新增评论失败！");
        }

        returnJson.put("error", error);
        returnJson.put("result", result);
        return returnJson;
    }

    @RequestMapping(value = RestConstants.SOU_INFO)
    @ResponseBody
    public JSONObject getSouvenirInfo(String souid) {
        JSONObject returnJson = new JSONObject();
        String error = "";
        JSONObject result = new JSONObject();

        result=souvenirService.getSouvenirInfo(souid);
        if(result==null){
            error= "true";
            result.put("msg","查询错误");
        }else{
            error= "false";

        }
        returnJson.put("error", error);
        returnJson.put("result", result);
        return returnJson;
    }

    @RequestMapping(value = RestConstants.DELETE_RECORD)
    @ResponseBody
    public JSONObject deleteSouRecord(String recordid) {
        JSONObject returnJson = new JSONObject();
        String error = "";
        JSONObject result = new JSONObject();

        if(souvenirService.deleteSouRecord(recordid)){
            error= "false";
            result.put("msg","删除成功");
        }else{
            error= "true";
            result.put("msg","删除失败");
        }
        returnJson.put("error", error);
        returnJson.put("result", result);
        return returnJson;

    }

    @RequestMapping(value = RestConstants.UPDATE_RECORD)
    @ResponseBody
    public JSONObject updateSouRecord(RecordinfoDTO recordinfoDTO) {
        JSONObject returnJson = new JSONObject();
        String error = "";
        JSONObject result =new JSONObject();
        recordinfoDTO.setTimerecord(SysUtils.getNowTimeStr());
        if (souvenirService.updateSouRecord(recordinfoDTO)) {
            error = "false";
            result=recordinfoDTO.getDtoToJson();
        }else{
            error = "true";
            result.put("msg","更新评论失败");
        }

        returnJson.put("error", error);
        returnJson.put("result", result);
        return returnJson;
    }

    @RequestMapping(value = RestConstants.CREATE_SOU)
    @ResponseBody
    public JSONObject createSou(String userid) {
        JSONObject returnJson = new JSONObject();
        String error = "";
        JSONObject result = new JSONObject();
        SouvenirDTO soudto = new SouvenirDTO();
        soudto.setUserid(userid);

        if (souvenirService.createSouvenir(soudto)) {
            error = "false";
            result = soudto.getDtoToJson();
        } else {
            error = "true";
            result.put("msg", "新增物品失败！");
        }

        returnJson.put("error", error);
        returnJson.put("result", result);
        return returnJson;
    }

    @RequestMapping(value = RestConstants.DELETE_IMAGES)
    public void deleteImages(String imageId,String userid,String souid) {//在image表删除记录，在clearqiniu表增加记录
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("imageId",imageId);
        paramMap.put("userid",userid);
        paramMap.put("souid",souid);
        souvenirService.deleteImage(paramMap);
    }

    @RequestMapping(value = RestConstants.SOU_LIST_TYPE)
    @ResponseBody
    public JSONObject souListByType(String soutypeid, String userid) {
        JSONObject returnJson = new JSONObject();
        String error = "";
        JSONObject result = new JSONObject();
        SouvenirDTO soudto = new SouvenirDTO();
        soudto.setUserid(userid);
        soudto.setSouvenirtypeid(soutypeid);
        JSONArray souListJson = souvenirService.getSouvenirListForType(soudto);
        error = "false";
        result.put("souList", souListJson);
        returnJson.put("error", error);
        returnJson.put("result", result);
        return returnJson;
    }

}
