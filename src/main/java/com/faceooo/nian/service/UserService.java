package com.faceooo.nian.service;

import com.faceooo.nian.dao.SouDAO;
import com.faceooo.nian.dao.UserDAO;
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

    public JSONObject getUserHomeInfo(UserinfoDTO userinfo) {
        JSONObject json = new JSONObject();
        try {
            // 查询用户物品的分类列表
            List<SouvenirtypeDTO> soutypeList = userDAO
                    .queryUserSouTypeList(userinfo);
            JSONArray soutypeListjson = new JSONArray();
            for (SouvenirtypeDTO soutype : soutypeList) {
                soutypeListjson.add(soutype.getDtoToJson());
            }
            json.put("soutypeList", soutypeListjson);

            // 查询最新录入的10个器物列表, 默认类型为第一个类型，0是未分类，从1开始计算
            List<SouvenirtypeDTO> userSouList = souDAO
                    .queryUserSouListForHome(userinfo);
            JSONArray souListjson = new JSONArray();
            for (SouvenirtypeDTO soudto : userSouList) {
                souListjson.add(soudto.getDtoToJson());
            }
            json.put("souList", souListjson);

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
