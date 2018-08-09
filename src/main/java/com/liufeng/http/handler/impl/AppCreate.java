package com.liufeng.http.handler.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liufeng.http.handler.HttpHandler;
import com.liufeng.http.handler.domain.ReturnEntity;
import com.liufeng.http.request.HttpRequestMessage;
import com.liufeng.http.response.HttpResponseMessage;
import com.liufeng.utils.JsonUtil;

public class AppCreate extends HttpHandler{
	private static final Logger logger = LoggerFactory.getLogger(AppCreate.class);

	@Override
	public String handle(HttpRequestMessage request, HttpResponseMessage response) {
		String appname = request.getParameter("appname");
		String resettime = request.getParameter("resettime");
		long resettimel = -1;
		try{
			resettimel = Long.parseLong(resettime);
		}catch(Exception e){
			this.SetFail(response, "参数不正确");
			return null;
		}
		
//		AppManager.getInstance().CreateApp(appname, resettimel);
		
		// TODO Auto-generated method stub
		Map<String,Object> data = new HashMap<String,Object>();
		
		this.SetSuccess(response, data);
		return null;
	}


}
