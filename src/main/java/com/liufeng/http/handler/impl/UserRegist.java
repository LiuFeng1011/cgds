package com.liufeng.http.handler.impl;

import java.util.HashMap;
import java.util.Map;

import com.liufeng.http.handler.HttpHandler;
import com.liufeng.http.request.HttpRequestMessage;
import com.liufeng.http.response.HttpResponseMessage;

public class UserRegist extends HttpHandler{
	@Override
	public String handle(HttpRequestMessage request, HttpResponseMessage response) {
		// TODO Auto-generated method stub

		String test = request.getParameter("test");

		Map<String,Object> data = new HashMap<String,Object>();
		
		data.put("data", "test:"+test);

		this.SetSuccess(response, data);
		return null;
	}
}
