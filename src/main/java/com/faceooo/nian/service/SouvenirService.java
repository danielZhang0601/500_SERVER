package com.faceooo.nian.service;
import com.faceooo.nian.dao.SouDAO;
import com.faceooo.nian.dao.UserDAO;
import com.faceooo.nian.model.*;
import com.faceooo.nian.utils.SysUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanlin on 15/8/18.
 */
@Service("souvenirService")
public class SouvenirService {

    @Autowired
    SouDAO souDAO;

    @Autowired
    UserDAO userDAO;


    public boolean deleteSou(String souvenirid,String userid) {
        try {
            //清除藏品相应的图片信息，记录等相关信息

            //清除图片 查询图片列表信息
            Map paramMap = new HashMap<String ,String>();
            paramMap.put("souid",souvenirid);
            paramMap.put("userid",userid);
            List<ImageDTO> souImages = souDAO.getSouSmallImagesList(paramMap);
            if(souImages!=null){
                for(ImageDTO souImage : souImages){
                    ClearQiniuDTO clearqiniudto = new ClearQiniuDTO();
                    clearqiniudto.setId(SysUtils.getClearQiniuID());
                    clearqiniudto.setImageid(souImage.getId());
                    clearqiniudto.setSouvenirid(souvenirid);
                    clearqiniudto.setUserid(userid);
                    clearqiniudto.setTimerecord(SysUtils.getNowTimeStr());
                    souDAO.createClearQiniu(clearqiniudto);
                }
            }

            if(souDAO.deleteImageForSouid(souvenirid)&&souDAO.deleteSouRecordForSouid(souvenirid)){
                return souDAO.deleteSou(souvenirid);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteSoutype(String soutypeid) {
        try {
            return souDAO.deleteSoutype(soutypeid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return false;
    }

    public boolean updateSoutype(SouvenirtypeDTO soutypedto) {
        try {
           return souDAO.updateSoutype(soutypedto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public SouvenirtypeDTO creatSouTypes(SouvenirtypeDTO soutypedto) {
        try {
            SouvenirtypeDTO souIsExist=souDAO.querySouTyepInfo(soutypedto);
            if(souIsExist==null){
                soutypedto.setId(SysUtils.getsoutypeid());
                soutypedto.setTypecount("0");
                soutypedto.setTimerecord(SysUtils.getNowTimeStr());
                souDAO.creatSouTypes(soutypedto);
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getSouvenirSearch(Map<String, String> paramMap) {// 两种搜索方式取其一
        JSONObject soupositioninfo = new JSONObject();
        try {
            // 判断按编码搜索是否有记录，若无记录则安装名称搜索
            // 按类型编码和排序码进行搜索
            List<SouvenirDTO> souList = souDAO.querySouListForSearch(paramMap);
            if (souList != null && souList.size() > 0) {
                SouvenirDTO soudtoDto = souList.get(0);
                soupositioninfo.put("souinfo", soudtoDto.getDtoToJson());

            } else {// 按照器物名称进行搜索
                List<SouvenirDTO> souListForName = souDAO
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
            return	souDAO.querySouInfoForID(soudto.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public boolean chargeSoutype(SouvenirDTO soudto) {
        try {
           return souDAO.chargeSoutype(soudto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSouBaseInfo(SouvenirDTO soudto) {
        try {
            return souDAO.updateSouBaseInfo(soudto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createSouRecord(RecordinfoDTO recorddto) {
        try {
            recorddto.setId(SysUtils.getrecordid());
            recorddto.setTimerecord(SysUtils.getNowTimeStr());
            return souDAO.createSouRecord(recorddto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return false;
    }

    public JSONObject getSouvenirInfo(String souvenirid) {

        try {
//            // 获取用户图片信息
//            List<ImageDTO> souImagesList = souDAO
//                    .querySouImagesCode(souvenirid);
//            JSONArray souImagesListJson = new JSONArray();
//            for (ImageDTO image : souImagesList) {
//                souImagesListJson.add(image.getDtoToJson());
//            }
//            souvenirInfoJSON.put("souImagesList", souImagesListJson);

//            // 获取用户时间轴信息 获取用户信息列表（用户id，用户名，用户头像图片）
//            List<UserinfoDTO> timelineUsers = userDAO
//                    .queryTimelineUsers(souvenirid);
//            JSONArray soutimeline = new JSONArray();
//            for (UserinfoDTO userdto : timelineUsers) {
//                soutimeline.add(userdto.getDtoToJson());
//            }
//            souvenirInfoJSON.put("soutimeline", soutimeline);

            //获取藏品的基础信息
            SouvenirDTO soudto = souDAO.querySouInfoForID(souvenirid);
            if(soudto==null){
                return null;
            }else{
                JSONObject souvenirInfoJSON = new JSONObject();
                souvenirInfoJSON.put("soubaseinfo", soudto.getDtoToJson());

                // 获取用户评论信息
                List<RecordinfoDTO> sourecordList = userDAO.querySoureviewList(souvenirid);
                JSONArray userreview = new JSONArray();
                for(RecordinfoDTO  recordinfoDTO :sourecordList){
                    userreview.add(recordinfoDTO.getDtoToJson());
                }

                souvenirInfoJSON.put("userrecords", userreview);
                return souvenirInfoJSON;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteSouRecord(String recordid) {
        try {
            return souDAO.deleteSouRecord(recordid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSouRecord(RecordinfoDTO recorddto) {
        try {
            return souDAO.updateSouRecord(recorddto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return false;
    }

    public String createImages(String userid, String souid) {
        String imageid= SysUtils.getImageID();
        ImageDTO imagedto= new ImageDTO();
        imagedto.setId(imageid);
        imagedto.setUserid(userid);
        imagedto.setSouvenirid(souid);
        imagedto.setImagessort("0");//TODO 图片排序功能，后面需要优化
        imagedto.setTimerecord(SysUtils.getNowTimeStr());
        try {
            souDAO.createSouImage(imagedto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imageid;
    }

    public boolean createSouvenir(SouvenirDTO soudto) {
        try {
            soudto.setId(SysUtils.getSouvenirID());
            soudto.setSouvenirtypeid("0");//0为未分类
            soudto.setTimerecord(SysUtils.getNowTimeStr());
            return souDAO.createSouvenir(soudto);

        } catch (SQLException e) {
            e.printStackTrace();
        }
            return false;
    }

    public void deleteImage( Map<String,String> paramMap) {
        try {
            String imageId =paramMap.get("imageId");
            String userid =paramMap.get("userid");
            String souid =paramMap.get("souid");
            souDAO.deleteImage(imageId);
            ClearQiniuDTO clearqiniudto = new ClearQiniuDTO();
            clearqiniudto.setId(SysUtils.getClearQiniuID());
            clearqiniudto.setImageid(imageId);
            clearqiniudto.setSouvenirid(souid);
            clearqiniudto.setUserid(userid);
            clearqiniudto.setTimerecord(SysUtils.getNowTimeStr());
            souDAO.createClearQiniu(clearqiniudto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //souvenirtypeid=0未分类
    public JSONArray getSouvenirListForType(SouvenirDTO souvenirDTO) {
        JSONArray souListjson = null;
        List<SouvenirDTO> souList = null;
        try {
            souList = souDAO.getSouvenirListForType(souvenirDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (souList!=null){
            souListjson=new JSONArray();
            for (SouvenirDTO soudto : souList) {
                souListjson.add(soudto.getDtoToJson());
            }
        }

        return souListjson;
    }
}
