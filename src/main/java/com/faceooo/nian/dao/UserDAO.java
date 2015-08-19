package com.faceooo.nian.dao;

import com.faceooo.nian.model.SouvenirtypeDTO;
import com.faceooo.nian.model.UserinfoDTO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yuanlin on 15/8/19.
 */
@Repository("userDAO")
public interface UserDAO {

    public UserinfoDTO queryUserInfoForID(String userid) throws SQLException ;

    public List<UserinfoDTO> queryTimelineUsers(String souvenirid)throws SQLException;

    public List<String> querySoureviewList(String souvenirid)throws SQLException;

    public List<SouvenirtypeDTO> queryUserSouTypeList(UserinfoDTO userinfo)throws SQLException;

    public void insertUserinfo(UserinfoDTO userinfo)throws SQLException;

    public UserinfoDTO queryUserLogin(UserinfoDTO userinfo)throws SQLException;

}
