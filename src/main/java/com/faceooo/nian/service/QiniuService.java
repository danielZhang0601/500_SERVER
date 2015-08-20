package com.faceooo.nian.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;

/**
 * Created by yuanlin on 15/8/17.
 */


@Service("qiniuService")
public class QiniuService {

    public static final String ACCESS_KEY = "eY5FxmhOnLWXx7BXbhnmIVYlpsF-U_GBAKUKdtTV";
    public static final String SECRET_KEY = "Gx_nUEYTyzw0V8TO9ufK8HuvPmi3AnxvH5OnKDzm";
    public static final String BUCKET_NAME= "nian";
    public static final int TIME_OUT= 3600;
    public static final String SAVE_KEY= "saveKey";//用于更换存储图片名称

    public void uploadToQiniu(byte[] bytes, String imageId) {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String token = auth.uploadToken(BUCKET_NAME, null, TIME_OUT, new StringMap().put(SAVE_KEY, imageId));
        UploadManager uploadManager = new UploadManager();
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
}
