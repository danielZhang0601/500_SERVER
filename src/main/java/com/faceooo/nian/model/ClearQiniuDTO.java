package com.faceooo.nian.model;

public class ClearQiniuDTO extends BaseDTO{
	public String id;
    public String souvenirid;
    public String userid;
	public String imageid;
    public String timerecord;

    public String getSouvenirid() {
        return souvenirid;
    }

    public void setSouvenirid(String souvenirid) {
        this.souvenirid = souvenirid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public String getTimerecord() {
        return timerecord;
    }

    public void setTimerecord(String timerecord) {
        this.timerecord = timerecord;
    }
}
