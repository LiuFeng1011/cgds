package com.liufeng.http.handler.impl;

import java.util.HashMap;
import java.util.Map;

import com.liufeng.cgds.user.UserManager;
import com.liufeng.http.handler.HttpHandler;
import com.liufeng.http.handler.domain.ReturnEntity;
import com.liufeng.http.request.HttpRequestMessage;
import com.liufeng.http.response.HttpResponseMessage;
import com.liufeng.utils.JsonUtil;
import com.liufeng.utils.RSAUtils;

public class UserLogin extends HttpHandler {
	@Override
	public String handle(HttpRequestMessage request, HttpResponseMessage response) {
		// TODO Auto-generated method stub

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Map<String,Object> data = new HashMap<String,Object>();
		
		try {
			// 解密
			password = RSAUtils.decryptDataOnJava(password, RSAUtils.PRIVATEKEY);
				
		} catch (Exception e) {
			e.printStackTrace();
			this.SetFail(response, "解密失败");
			return null;
		}
		
		boolean islogin = UserManager.getInstance().Login(username, password);
		if(islogin){
			this.SetSuccess(response, "success");
		}else{
			this.SetFail(response, "用户名或密码错误");
		}
		
		return null;
	}
}
