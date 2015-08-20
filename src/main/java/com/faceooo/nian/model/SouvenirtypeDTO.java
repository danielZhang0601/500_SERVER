package com.faceooo.nian.model;

public class SouvenirtypeDTO extends BaseDTO {
	public String id;
	public String userid;
	public String typename;
	public String typecount;//该类别物品数量
	public String timerecord;
	public String typesort;
	
	
	public String getTypesort() {
		return typesort;
	}
	public void setTypesort(String typesort) {
		this.typesort = typesort;
	}
	public String getTypecount() {
		return typecount;
	}
	public void setTypecount(String typecount) {
		this.typecount = typecount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getTimerecord() {
		return timerecord;
	}
	public void setTimerecord(String timerecord) {
		this.timerecord = timerecord;
	}
	
	

}
