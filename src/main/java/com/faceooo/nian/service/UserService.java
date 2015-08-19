package com.faceooo.nian.service;

import com.faceooo.nian.dao.SouDAO;
import com.faceooo.nian.model.SouvenirtypeDTO;
import com.faceooo.nian.model.UserinfoDTO;
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
            json.put("souList", soutypeListjson);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return json;
    }

    public void insertUserinfo(UserinfoDTO userinfo) {

        try {
            userDAO.insertUserinfo(userinfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public UserinfoDTO userLogin(UserinfoDTO userinfo) {
        try {
            return  userDAO.queryUserLogin(userinfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userinfo;
    }
}
