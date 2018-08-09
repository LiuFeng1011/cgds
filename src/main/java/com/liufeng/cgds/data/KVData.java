package com.liufeng.cgds.data;

public class KVData {
	public long id;
	public int appid;
	public int uid;
	public String key;
	public String value;
	
	public KVData(){
		
	}
	
	public KVData(int appid,int uid,String key,String value){
		this.appid = appid;
		this.uid = uid;
		this.key = key;
		this.value = value;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAppid() {
		return appid;
	}
	public void setAppid(int appid) {
		this.appid = appid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
