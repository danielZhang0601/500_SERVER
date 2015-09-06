package com.faceooo.nian.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class QiniuBiz {

	public static void main(String args[]) {
		QiniuBiz qqBiz = new QiniuBiz();
		qqBiz.uploadSouImages();
	}

	public static final String ACCESS_KEY = "eY5FxmhOnLWXx7BXbhnmIVYlpsF-U_GBAKUKdtTV";
	public static final String SECRET_KEY = "Gx_nUEYTyzw0V8TO9ufK8HuvPmi3AnxvH5OnKDzm";

	public void uploadSouImages() {
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		String token = auth.uploadToken("nian", null, 3600,
				new StringMap().put("saveKey", "11118409891212311.jpg"));
		String filePath = "D://18409891212311.jpg";
		byte[] buffer = getFilebyteArray(filePath);
		String key = upload(buffer, null, token);
		BucketManager bucketManager = new BucketManager(auth);
		BucketManager.Batch ops = new BucketManager.Batch().delete("nian", "12313");
		try {
			Response r = bucketManager.batch(ops);
			BatchStatus[] bs = r.jsonToObject(BatchStatus[].class);
			for (BatchStatus b : bs) {
				System.out.println(b.code);
			}

		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String upload(byte[] byteOrFile, String key, String token) {
		UploadManager uploadManager = new UploadManager();
		try {
			Response res = uploadManager.put(byteOrFile, null, token);
			MyRet ret = res.jsonToObject(MyRet.class);
			System.out.println(res.toString());
			System.out.println(res.bodyString());
			JSONObject hashkeyJson = (JSONObject) JSONValue.parse(res
					.bodyString());
			String hash = (String) hashkeyJson.get("hash");
			key = (String) hashkeyJson.get("key");
			System.out.println(hash);
			System.out.println(key);

			return key;

		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时简单状态信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
		return null;
	}

	private byte[] getFilebyteArray(String filePath) {
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	public class MyRet {
		public long fsize;
		public String key;
		public String hash;
		public int width;
		public int height;
	}

}
