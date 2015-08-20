package com.faceooo.nian.model;

public class ImageDTO extends BaseDTO{
	public String id;
	public String souvenirid;
	public String imagescode;//针对七牛上的图片链接
	public String imagessort;
	public String timerecord;
	
	public String getImagescode() {
		return imagescode;
	}
	public void setImagescode(String imagescode) {
		this.imagescode = imagescode;
	}
	public String getImagessort() {
		return imagessort;
	}
	public void setImagessort(String imagessort) {
		this.imagessort = imagessort;
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

	
	
}
