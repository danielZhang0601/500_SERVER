package com.faceooo.nian.model;

public class ImageDTO extends BaseDTO{
	public String id;
	public String souvenirid;
    public String userid;
	public String imagescode;//针对七牛上的图片链接
	public String imagesort;
	public String timerecord;

	public String getImagesort() {
		return imagesort;
	}

	public void setImagesort(String imagesort) {
		this.imagesort = imagesort;
	}

	public String imagesmallurl;
    public String imageorigurl;


    public String getImagescode() {
		return imagescode;
	}
	public void setImagescode(String imagescode) {
		this.imagescode = imagescode;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSouvenirid() {
		return souvenirid;
	}
	public void setSouvenirid(String souvenirid) {
		this.souvenirid = souvenirid;
	}
	public String getTimerecord() {
		return timerecord;
	}
	public void setTimerecord(String timerecord) {
		this.timerecord = timerecord;
	}
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getImagesmallurl() {
        return imagesmallurl;
    }

    public void setImagesmallurl(String imagesmallurl) {
        this.imagesmallurl = imagesmallurl;
    }

    public String getImageorigurl() {
        return imageorigurl;
    }

    public void setImageorigurl(String imageorigurl) {
        this.imageorigurl = imageorigurl;
    }

	
	
}