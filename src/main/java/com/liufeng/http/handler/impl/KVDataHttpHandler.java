package com.liufeng.http.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.liufeng.db.DBManager;
import com.liufeng.cgds.data.KVData;
import com.liufeng.cgds.user.UserManager;
import com.liufeng.http.handler.HttpHandler;
import com.liufeng.http.request.HttpRequestMessage;
import com.liufeng.http.response.HttpResponseMessage;
import com.liufeng.utils.JsonUtil;
import com.liufeng.utils.TimeUtils;

public class KVDataHttpHandler extends HttpHandler{
	private static final Logger logger = LoggerFactory.getLogger(KVDataHttpHandler.class);
	

	@Override
	public String handle(HttpRequestMessage request, HttpResponseMessage response) {
		// TODO Auto-generated method stub
		String function = request.getParameter("f");
		
		if(function == null || "".equals(function)){
			this.SetFail(response, "参数不正确");
			return null;
		}

		String appid = request.getParameter("appid");

		if(appid == null || "".equals(appid)){
			this.SetFail(response, "参数不正确");
			return null;
		}
		
		if("getdata".equals(function)){//获取kv数据
			GetData(appid,request,response);
		}else if("setdata".equals(function)){//设置kv数据
			SetData(appid,request,response);
		}
		
//		long ret = UserManager.getInstance().GetUserId(uname);
		
//		this.SetSuccess(response, ret);
		
		return null;
	}
	
	//获取kv数据
	void GetData(String appid,HttpRequestMessage request, HttpResponseMessage response){

		String uidlist = request.getParameter("uidlist");
		String keylist = request.getParameter("keylist");
		
		List<KVData> dataList = DBManager.getInstance().getKvdataDAO().getData(appid, uidlist, keylist);
		this.SetSuccess(response, dataList);
	}

	//设置kv数据
	void SetData(String appid,HttpRequestMessage request, HttpResponseMessage response){
		String uid = request.getParameter("uid");
		String key = request.getParameter("key");
		
		if(uid == "" || key == "") {
			logger.info("uid == null || key == null");
			this.SetFail(response, "参数不正确");
			return ;
		}
		
		String value = request.getParameter("value");
		logger.info("value : " + value);
		KVData data = DBManager.getInstance().getKvdataDAO().getDataByOne(appid, uid, key);
		if(data != null){
			data.value = value;
			DBManager.getInstance().getKvdataDAO().updateData(data);
		}else{
			data = new KVData();
			data.appid = Integer.parseInt(appid);
			data.uid = Integer.parseInt(uid);
			data.key = key;
			data.value = value;
			DBManager.getInstance().getKvdataDAO().addData(data);
		}
		this.SetSuccess(response, "保存成功");
	}

}
