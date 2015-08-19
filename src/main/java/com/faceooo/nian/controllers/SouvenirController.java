package com.faceooo.nian.controllers;

import com.faceooo.nian.model.RecordinfoDTO;
import com.faceooo.nian.model.SouvenirDTO;
import com.faceooo.nian.model.SouvenirtypeDTO;
import com.faceooo.nian.service.SouvenirService;
import com.faceooo.nian.utils.RestConstants;
import com.faceooo.nian.utils.SysUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuanlin on 15/8/17.
 * 所有和藏品有关的操作
 */


@Controller
public class SouvenirController {

//    @Autowired
//    SouvenirService souvenirService;
//
//    @RequestMapping(value = RestConstants.DELETE_SOU)
//    public void deleteSou(String souid) {
//       souvenirService.deleteSou(souid);
//    }
//
//    @RequestMapping(value = RestConstants.DELETE_SOUTYPE)
//    public void deleteSoutype(String soutypeid) {
//        souvenirService.deleteSoutype(soutypeid);
//    }
//
//    @RequestMapping(value = RestConstants.UPDATE_SOUTYPE)
//    public void updateSoutype(String soutypeid,String soutypename) {
//        SouvenirtypeDTO soutypedto= new SouvenirtypeDTO();
//        soutypedto.setId(soutypeid);
//        soutypedto.setTypename(soutypename);
//        souvenirService.updateSoutype(soutypedto);
//    }
//
//    @RequestMapping(value = RestConstants.CREATE_SOUTYPE)
//    public void creatSouTypes(String soutypename,String userid) {
//        SouvenirtypeDTO soutypedto = new SouvenirtypeDTO();
//        soutypedto.setId(SysUtils.getsoutypeid());
//        soutypedto.setUserid(userid);
//        soutypedto.setTypename(soutypename);
//        soutypedto.setTypecount("0");
//        soutypedto.setTimerecord(SysUtils.getNowTimeStr());
//        souvenirService.creatSouTypes(soutypedto);
//    }
//
//    @RequestMapping(value = RestConstants.SOU_SEARCH)
//    public ModelAndView getSouvenirSearch(String souvenircode,String userid) {
//        ModelAndView mav = new ModelAndView();
//        Map<String,String> paramMap= new HashMap<String,String>();
//        paramMap.put("souvenircode", souvenircode);
//        paramMap.put("userid", userid);
//        mav.addObject("souSearchInfoJson", souvenirService.getSouvenirSearch(paramMap));
//        return mav;
//    }
//
//    @RequestMapping(value = RestConstants.SOUCHARGE_TYPE)
//    public void chargeSoutype(String soutypeid,String souid){
//        SouvenirDTO soudto = new SouvenirDTO();
//        soudto.setId(souid);
//        soudto.setSouvenirtypeid(soutypeid);
//        SouvenirDTO oldsouinfo=souvenirService.querySouBaseInfoForID(soudto);
//        if(!soutypeid.equals(oldsouinfo.getSouvenirtypeid())){
//            souvenirService.chargeSoutype(soudto);
//        }
//    }
//
//    @RequestMapping (value = RestConstants.UPDATE_SOUBASEINFO)
//    public void  updateSouBaseInfo(SouvenirDTO soudto){
//        souvenirService.updateSouBaseInfo(soudto);
//    }
//
//    @RequestMapping(value = RestConstants.CREATE_RECORD)
//    public void createSouRecord(String souid,String recordcontent){
//        RecordinfoDTO recorddto = new RecordinfoDTO();
//        recorddto.setId(SysUtils.getrecordid());
//        recorddto.setSouvenirid(souid);
//        recorddto.setTimerecord(SysUtils.getNowTimeStr());
//        recorddto.setContent(recordcontent);
//        souvenirService.createSouRecord(recorddto);
//    }
//
//    @RequestMapping(value = RestConstants.SOU_INFO)
//    public ModelAndView getSouvenirInfo(String souvenirid) {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("souInfoJson", souvenirService.getSouvenirInfo(souvenirid));
//        return mav;
//    }
//
//    @RequestMapping(value = RestConstants.DELETE_RECORD)
//    public void  deleteSouRecord(String recordid){
//        RecordinfoDTO recorddto = new RecordinfoDTO();
//        recorddto.setId(recordid);
//        souvenirService.deleteSouRecord(recorddto);
//    }
//
//    @RequestMapping(value = RestConstants.UPDATE_RECORD)
//    public void  updateSouRecord(String recordid,String recordcontent){
//        RecordinfoDTO recorddto = new RecordinfoDTO();
//        recorddto.setId(recordid);
//        recorddto.setTimerecord(SysUtils.getNowTimeStr());
//        recorddto.setContent(recordcontent);
//        souvenirService.updateSouRecord(recorddto);
//    }
//
//    @RequestMapping(value = RestConstants.CREATE_SOU)
//    public void createSou(String userid){
//        SouvenirDTO soudto = new SouvenirDTO();
//        soudto.setUserid(userid);
//        souvenirService.createSouvenir(soudto);
//    }
//
//    @RequestMapping(value = RestConstants.DELETE_IMAGES)
//    public void deleteImages(String imageId){//在image表删除记录，在clearqiniu表增加记录
//        souvenirService.deleteImage(imageId);
//    }
//
//    @RequestMapping(value = RestConstants.SOU_LIST_TYPE)
//    public ModelAndView souListByType(String souTypeID,String userid){
//        ModelAndView mav = new ModelAndView();
//        SouvenirDTO soudto = new SouvenirDTO();
//        soudto.setUserid(userid);
//        soudto.setSouvenirtypeid(souTypeID);
//        mav.addObject("souListJson", souvenirService.getSouvenirListForType(soudto));
//        return mav;
//    }

}
