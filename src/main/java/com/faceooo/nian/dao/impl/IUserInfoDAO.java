package com.faceooo.nian.dao.impl;

import com.faceooo.nian.model.SouvenirtypeDTO;
import com.faceooo.nian.model.UserinfoDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by qin on 15/8/18.
 */
public interface IUserInfoDAO {

    public List<SouvenirtypeDTO> queryUserSouTypeList(UserinfoDTO userinfo) throws SQLException;

    public List<UserinfoDTO> queryTimelineUsers(String souvenirid) throws SQLException;

    public List<String> querySoureviewList(String souvenirid) throws SQLException;

    public UserinfoDTO queryUserInfoForID(String userid)throws SQLException;

    public void insertUserinfo(UserinfoDTO userinfo)throws SQLException;

    public UserinfoDTO queryUserLogin(UserinfoDTO userinfo) throws SQLException;

}
