package com.faceooo.nian.dao;

import com.faceooo.nian.model.SouvenirtypeDTO;
import com.faceooo.nian.model.UserinfoDTO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Repository("userinfoDAO")
public class UserInfoDAO  extends BaseDao{

	
	public List<SouvenirtypeDTO> queryUserSouTypeList(UserinfoDTO userinfo) throws SQLException {
		return this.executeQueryForList("queryUserSouTypeList", userinfo);
	}

	public List<UserinfoDTO> queryTimelineUsers(String souvenirid) throws SQLException {
		 return this.executeQueryForList("queryTimelineUsers", souvenirid);
	}

	public List<String> querySoureviewList(String souvenirid) throws SQLException {
		 return this.executeQueryForList("querySoureviewList", souvenirid);
	}

	public UserinfoDTO queryUserInfoForID(String userid)throws SQLException {
		return (UserinfoDTO) this.executeQueryForObject("queryUserInfoForID", userid);
	}

	public void insertUserinfo(UserinfoDTO userinfo)throws SQLException {
		this.executeInsert("insertUserinfo", userinfo);
	}

	public UserinfoDTO queryUserLogin(UserinfoDTO userinfo) throws SQLException {
		return (UserinfoDTO) this.executeQueryForObject("queryUserLogin", userinfo);
	}
}
