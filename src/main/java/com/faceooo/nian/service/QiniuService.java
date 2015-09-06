package com.faceooo.nian.service;

import com.faceooo.nian.dao.SouDAO;
import com.faceooo.nian.model.ImageDTO;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanlin on 15/8/17.
 */


@Service("qiniuService")
public class QiniuService {

    @Autowired
    SouDAO souDAO;

    public static final String ACCESS_KEY = "eY5FxmhOnLWXx7BXbhnmIVYlpsF-U_GBAKUKdtTV";
    public static final String SECRET_KEY = "Gx_nUEYTyzw0V8TO9ufK8HuvPmi3AnxvH5OnKDzm";
    public static final String BUCKET_NAME= "nian";
    public static final int TIME_OUT= 3600;
    public static final String SAVE_KEY= "saveKey";//用于更换存储图片名称
    public static final String QINIU_DOMAIN = "7xkbp1.com1.z0.glb.clouddn.com";
    public static final String PHONE_STYLE = "?imageView2/1/w/111/h/111/q/24";
    public static final long QINIU_TIME_OUT = 3600*12;
    private UploadManager uploadManager = new UploadManager();
    private Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    public void uploadToQiniu(byte[] bytes, String qiniuImageName) {

        String token = auth.uploadToken(BUCKET_NAME, null, TIME_OUT, new StringMap().put(SAVE_KEY, qiniuImageName));
        try {
            Response res = uploadManager.put(bytes, null, token);
            JSONObject hashkeyJson = (JSONObject) JSONValue.parse(res.bodyString());
            String key = (String) hashkeyJson.get("key");
            System.out.println(key);
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时简单状态信息
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                e.printStackTrace();
            }
        }
     }

    public List<ImageDTO> getSouSmallImagesList(Map paramMap) {
        List<ImageDTO> smallImagesList= null;
        try {
            smallImagesList = souDAO.getSouSmallImagesList(paramMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(smallImagesList!=null){
            for(ImageDTO smallImage : smallImagesList){
                String qiniuImegeName =  smallImage.getUserid()+"-"+smallImage.getSouvenirid()+"-"+smallImage.getId();
                String qiniuImageUrl = "http://"+QINIU_DOMAIN+"/"+qiniuImegeName;
                String qiniuSmallImageURL = auth.privateDownloadUrl(qiniuImageUrl+PHONE_STYLE,TIME_OUT);
                String qiniuOrigImageURL = auth.privateDownloadUrl(qiniuImageUrl,QINIU_TIME_OUT);
                smallImage.setImagesmallurl(qiniuSmallImageURL);
                smallImage.setImageorigurl(qiniuOrigImageURL);
            }
        }
        return smallImagesList;
    }

}
