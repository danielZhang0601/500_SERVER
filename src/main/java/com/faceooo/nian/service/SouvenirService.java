package com.faceooo.nian.service;

import com.faceooo.nian.dao.SouvenirDAO;
import com.faceooo.nian.dao.UserInfoDAO;
import com.faceooo.nian.model.*;
import com.faceooo.nian.utils.SysUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanlin on 15/8/18.
 */
@Service("souvenirService")
public class SouvenirService {

    @Autowired
    SouvenirDAO souvenirDAO;

    @Autowired
    UserInfoDAO userInfoDAO;

    public void deleteSou(String souvenirid) {
        try {
            souvenirDAO.deleteSou(souvenirid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSoutype(String soutypeid) {
        try {
            souvenirDAO.deleteSoutype(soutypeid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateSoutype(SouvenirtypeDTO soutypedto) {
        try {
            souvenirDAO.updateSoutype(soutypedto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void creatSouTypes(SouvenirtypeDTO soutypedto) {
        try {
            souvenirDAO.creatSouTypes(soutypedto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public JSONObject getSouvenirSearch(Map<String, String> paramMap) {// 两种搜索方式取其一
        JSONObject soupositioninfo = new JSONObject();
        try {
            // 判断按编码搜索是否有记录，若无记录则安装名称搜索
            // 按类型编码和排序码进行搜索
            List<SouvenirDTO> souList = souvenirDAO.querySouListForSearch(paramMap);
            if (souList != null && souList.size() > 0) {
                SouvenirDTO soudtoDto = souList.get(0);
                soupositioninfo.put("souinfo", soudtoDto.getDtoToJson());

            } else {// 按照器物名称进行搜索
                List<SouvenirDTO> souListForName = souvenirDAO
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

    public SouvenirDTO querySouBaseInfoForID(SouvenirDTO soudto) {
        try {
            return	souvenirDAO.querySouInfoForID(soudto.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void chargeSoutype(SouvenirDTO soudto) {
        try {
            souvenirDAO.chargeSoutype(soudto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSouBaseInfo(SouvenirDTO soudto) {
        try {
            souvenirDAO.updateSouBaseInfo(soudto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createSouRecord(RecordinfoDTO recorddto) {
        try {
            souvenirDAO.createSouRecord(recorddto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public JSONObject getSouvenirInfo(String souvenirid) {
        JSONObject souvenirInfoJSON = new JSONObject();
        try {
            // 获取用户图片信息
            List<ImageDTO> souImagesList = souvenirDAO
                    .querySouImagesCode(souvenirid);
            JSONArray souImagesListJson = new JSONArray();
            for (ImageDTO image : souImagesList) {
                souImagesListJson.add(image.getDtoToJson());
            }
            souvenirInfoJSON.put("souImagesList", souImagesListJson);

            // 获取用户时间轴信息 获取用户信息列表（用户id，用户名，用户头像图片）
            List<UserinfoDTO> timelineUsers = userInfoDAO
                    .queryTimelineUsers(souvenirid);
            JSONArray soutimeline = new JSONArray();
            for (UserinfoDTO userdto : timelineUsers) {
                soutimeline.add(userdto.getDtoToJson());
            }
            souvenirInfoJSON.put("soutimeline", soutimeline);

            // 获取用户评论信息
            List<String> soureviewList = userInfoDAO.querySoureviewList(souvenirid);
            JSONArray userreview = new JSONArray();
            userreview.add(soureviewList);
            souvenirInfoJSON.put("userreviews", userreview);

            //获取藏品的基础信息
            SouvenirDTO soudto = souvenirDAO.querySouInfoForID(souvenirid);
            souvenirInfoJSON.put("soubaseinfo", soudto.getDtoToJson());

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return souvenirInfoJSON;
    }

    public void deleteSouRecord(RecordinfoDTO recorddto) {
        try {
            souvenirDAO.deleteSouRecord(recorddto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSouRecord(RecordinfoDTO recorddto) {
        try {
            souvenirDAO.updateSouRecord(recorddto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String createImages(String userid, String souid) {
        String imageid= userid+"-"+souid+"-"+ SysUtils.getImageID();

        return imageid;
    }

    public void createSouvenir(SouvenirDTO soudto) {
        try {
            soudto.setId(SysUtils.getSouvenirID());
            soudto.setTimerecord(SysUtils.getNowTimeStr());
            souvenirDAO.createSouvenir(soudto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
