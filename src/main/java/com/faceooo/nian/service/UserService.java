package com.faceooo.nian.service;

import com.faceooo.nian.dao.SouDAO;
import com.faceooo.nian.dao.UserDAO;
import com.faceooo.nian.model.ImageDTO;
import com.faceooo.nian.model.SouvenirDTO;
import com.faceooo.nian.model.SouvenirtypeDTO;
import com.faceooo.nian.model.UserinfoDTO;
import com.faceooo.nian.utils.SysUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yuanlin on 15/8/17.
 */

@Service ("userService")
public class UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    SouDAO souDAO;

    @Autowired
    QiniuService qiniuService;

    //一次查询出所有数据返回给前端
    public JSONObject getUserHomeInfo(UserinfoDTO userinfo) {
        JSONObject json = new JSONObject();
        try {
            // 查询用户物品的分类列表
            List<SouvenirtypeDTO> soutypeList = userDAO.queryUserSouTypeList(userinfo);
            JSONArray soutypeListjson = new JSONArray();

            ImageDTO tempImage =  new ImageDTO();
            tempImage.setUserid(userinfo.getId());

            SouvenirDTO paramSou = new SouvenirDTO();
            paramSou.setUserid(userinfo.getId());

            for (SouvenirtypeDTO soutype : soutypeList) {
                paramSou.setSouvenirtypeid(soutype.getId());
                //通过souTypeID查询物品信息
                List<SouvenirDTO> souList = souDAO.getSouvenirListForType(paramSou);
                JSONArray souListjson = new JSONArray();
                for (SouvenirDTO souDTO : souList){
                    tempImage.setSouvenirid(souDTO.getId());
                    tempImage.setId(souDTO.getMainImageID());
                    qiniuService.getSmallImageURL(tempImage);
                    souDTO.setMainImageURL(tempImage.getImagesmallurl());
                    souListjson.add(souDTO.getDtoToJson());

                }
                json.put(soutype.getId(), souListjson);
                soutypeListjson.add(soutype.getDtoToJson());
            }
            json.put("souTypeList", soutypeListjson);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return json;
    }

    public String insertUserinfo(UserinfoDTO userinfo) {

        try {
            userinfo.setId(SysUtils.getuserid());
            userinfo.setTimerecord(SysUtils.getNowTimeStr());
            if(userinfo.getUsername()==null){
                userinfo.setUsername(userinfo.getUserphone());
            }
            List<UserinfoDTO> userinfoList= userDAO.queryUserLogin(userinfo);
            if(userinfoList!=null&&userinfoList.size()>0){
                return "该手机号码已经注册，请登录！";
            }else{
                userDAO.insertUserinfo(userinfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
            return null;
    }

    public UserinfoDTO userLogin(UserinfoDTO userinfo) {
        try {
            List<UserinfoDTO> userinfoList=  userDAO.queryUserLogin(userinfo);
            if(userinfoList!=null&&userinfoList.size()==1){
                return userinfoList.get(0);
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkUserIsExists(UserinfoDTO userinfo) {
        UserinfoDTO userinfoList=null;
        try {
            userinfoList=  userDAO.queryUserInfoForID(userinfo.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(userinfoList==null){
            return true;
        }else{
            return false;
        }
    }


}
