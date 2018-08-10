package com.liufeng.cgds.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liufeng.db.DBManager;
import com.liufeng.utils.TimeUtils;

public class UserManager {
	private static final Logger logger = LoggerFactory.getLogger(UserManager.class);
	
	private static UserManager instance = null;
	
	public static UserManager getInstance()
	{
	    if (instance == null)
	    {
	      instance = new UserManager();
	    }

	    return instance;
	}

	Map<String,UserData> userMap = new HashMap<String,UserData>();
	
	//服务器启动时获取全部用户数据
	private UserManager(){
		List<UserData> userList = DBManager.getInstance().getUserDAO().getUserList();
		
		for(int i = 0 ; i < userList.size() ; i ++){
			UserData data = userList.get(i);
			userMap.put(data.getUsername(), data);
		}
	}
	
	//登陆
	public boolean Login(String username,String password){
		
		if(userMap.containsKey(username)){
			UserData ud = userMap.get(username);
			if(ud.password.equals(password)){
				return true;
			}
		}
		
		return false;
	}
	
	//用户注册
	public String Regist(String username,String password,String email,String qq){

		if(userMap.containsKey(username)){
			return "用户名重复";
		}
		if(email.equals("")){
			return "email不能为null";
		}
		
		UserData data = new UserData();
		data.setUsername(username);
		data.setPassword(password);
		data.setCreatetime(TimeUtils.nowLong());
		data.setEmail(email);
		data.setQq(qq);
		DBManager.getInstance().getUserDAO().addUser(data);
		
		userMap.put(username, data);
		
		return "";
	}
	

}
