package com.liufeng.http.handler;

import com.liufeng.http.handler.domain.ReturnEntity;
import com.liufeng.http.request.HttpRequestMessage;
import com.liufeng.http.response.HttpResponseMessage;
import com.liufeng.utils.JsonUtil;
import com.liufeng.utils.Tools;

public abstract class HttpHandler {
	public  abstract String handle(HttpRequestMessage request,HttpResponseMessage response); 
	
	public void SetSuccess(HttpResponseMessage response,Object data){
		SetSuccess(response,data,false);
	}
	public void SetSuccess(HttpResponseMessage response,Object data,boolean isCompress){
		ReturnEntity ret = ReturnEntity.createSucc(data);
		
		String retString = JsonUtil.ObjectToJsonString(ret);
		
		if(isCompress){
			retString = Tools.compress(retString);
		}
		
		response.appendBody(retString);
		response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
	}
	
	public void SetFail(HttpResponseMessage response,String msg){
		ReturnEntity ret = ReturnEntity.createFail(msg);
		response.appendBody(JsonUtil.ObjectToJsonString(ret));
		response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
	}
}
