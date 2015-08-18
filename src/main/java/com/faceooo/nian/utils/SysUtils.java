package com.faceooo.nian.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SysUtils {

	public static String getuserid() {
		return "U"+Long.toString(System.currentTimeMillis());
	}

	public static String getsoutypeid() {
		return "ST"+Long.toString(System.currentTimeMillis());
	}

	public static String getNowTimeStr() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	public static String getrecordid() {
		return "R"+Long.toString(System.currentTimeMillis());
	}
    public static String getImageID() {
        return "I"+Long.toString(System.currentTimeMillis());
    }

    public static String getSouvenirID() {
        return "S"+Long.toString(System.currentTimeMillis());
    }
}
