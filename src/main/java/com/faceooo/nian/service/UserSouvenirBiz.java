package com.faceooo.nian.service;

import com.faceooo.nian.dao.SouvenirDAO;
import com.faceooo.nian.dao.UserInfoDAO;
import com.faceooo.nian.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class UserSouvenirBiz {

	private UserInfoDAO userdao = new UserInfoDAO();
	private SouvenirDAO soudao = new SouvenirDAO();

	/**
	 * 通过用户id获取用户的分类信息和home页的物品信息
	 * 
	 * @param
	 * @return
	 */

	public JSONObject getUserHomeInfo(UserinfoDTO userinfo) {
		JSONObject json = new JSONObject();
		try {
			// 查询用户物品的分类列表
			List<SouvenirtypeDTO> soutypeList = userdao
					.queryUserSouTypeList(userinfo);
			JSONArray soutypeListjson = new JSONArray();
			for (SouvenirtypeDTO soutype : soutypeList) {
				soutypeListjson.add(soutype.getDtoToJson());
			}
			json.put("soutypeList", soutypeListjson);

			// 查询最新录入的10个器物列表, 默认类型为第一个类型，0是未分类，从1开始计算
			List<SouvenirtypeDTO> userSouList = soudao
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

	public JSONObject getSouvenirInfo(String souvenirid) {
		JSONObject souvenirInfoJSON = new JSONObject();
		try {
			// 获取用户图片信息
			List<ImageDTO> souImagesList = soudao
					.querySouImagesCode(souvenirid);
			JSONArray souImagesListJson = new JSONArray();
			for (ImageDTO image : souImagesList) {
				souImagesListJson.add(image.getDtoToJson());
			}
			souvenirInfoJSON.put("souImagesList", souImagesListJson);

			// 获取用户时间轴信息 获取用户信息列表（用户id，用户名，用户头像图片）
			List<UserinfoDTO> timelineUsers = userdao
					.queryTimelineUsers(souvenirid);
			JSONArray soutimeline = new JSONArray();
			for (UserinfoDTO userdto : timelineUsers) {
				soutimeline.add(userdto.getDtoToJson());
			}
			souvenirInfoJSON.put("soutimeline", soutimeline);

			// 获取用户评论信息
			List<String> soureviewList = userdao.querySoureviewList(souvenirid);
			JSONArray userreview = new JSONArray();
			userreview.add(soureviewList);
			souvenirInfoJSON.put("userreviews", userreview);
			
			//获取藏品的基础信息
			SouvenirDTO soudto = soudao.querySouInfoForID(souvenirid);
			souvenirInfoJSON.put("soubaseinfo", soudto.getDtoToJson());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return souvenirInfoJSON;
	}

	public JSONObject getSouvenirType(String userid) {
		JSONObject usersouinfo = new JSONObject();
		// 获取用户分类信息
		try {
			UserinfoDTO userinfo = new UserinfoDTO();
			userinfo.setId(userid);
			// 查询用户物品的分类列表
			List<SouvenirtypeDTO> soutypeList = userdao
					.queryUserSouTypeList(userinfo);
			JSONArray soutypeListjson = new JSONArray();
			for (SouvenirtypeDTO soutype : soutypeList) {
				soutypeListjson.add(soutype.getDtoToJson());
			}
			usersouinfo.put("soutypelist", soutypeListjson);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersouinfo;
	}

	public JSONObject getSouvenirSearch(Map<String, String> paramMap) {// 两种搜索方式取其一
		JSONObject soupositioninfo = new JSONObject();
		try {
			// 判断按编码搜索是否有记录，若无记录则安装名称搜索
			// 按类型编码和排序码进行搜索
			List<SouvenirDTO> souList = soudao.querySouListForSearch(paramMap);
			if (souList != null && souList.size() > 0) {
				SouvenirDTO soudtoDto = souList.get(0);
				soupositioninfo.put("souinfo", soudtoDto.getDtoToJson());

			} else {// 按照器物名称进行搜索
				List<SouvenirDTO> souListForName = soudao
						.querySouListForSearchName(paramMap);
				JSONArray souListjson = new JSONArray();
				for (SouvenirDTO soudto : souListForName) {
					souListjson.add(soudto.getDtoToJson());
				}
				soupositioninfo.put("searchSouList", souListjson);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return soupositioninfo;

	}

	public JSONObject getUserInfo(String userid) {
		JSONObject userinfojson = new JSONObject();
		UserinfoDTO userinfo;
		try {
			userinfo = userdao.queryUserInfoForID(userid);
			userinfojson.put("userinfo", userinfo.getDtoToJson());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userinfojson;
	}

	public JSONObject getUserReg(UserinfoDTO userinfo) {
		JSONObject userinfojson = new JSONObject();
		userinfojson.put("userinfo", userinfo.getDtoToJson());
		try {
			userdao.insertUserinfo(userinfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userinfojson;
	}

	public void creatSouTypes(SouvenirtypeDTO soutypedto) {
		try {
			soudao.creatSouTypes(soutypedto);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public JSONObject getSouvenirListForType(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteSou(String souvenirid) {
		try {
			soudao.deleteSou(souvenirid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteSoutype(String soutypeid) {
		try {
			soudao.deleteSoutype(soutypeid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void updateSoutype(SouvenirtypeDTO soutypedto) {
		try {
			soudao.updateSoutype(soutypedto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public SouvenirDTO querySouBaseInfoForID(SouvenirDTO soudto) {
		try {
			return	soudao.querySouInfoForID(soudto.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public void chargeSoutype(SouvenirDTO soudto) {
		try {
			soudao.chargeSoutype(soudto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateSouBaseInfo(SouvenirDTO soudto) {
		try {
			soudao.updateSouBaseInfo(soudto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createSouRecord(RecordinfoDTO recorddto) {
		try { 
			soudao.createSouRecord(recorddto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deleteSouRecord(RecordinfoDTO recorddto) {
		try { 
			soudao.deleteSouRecord(recorddto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateSouRecord(RecordinfoDTO recorddto) {
		try { 
			soudao.updateSouRecord(recorddto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
