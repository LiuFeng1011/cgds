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
	
	private UserManager(){
		List<UserData> userList = DBManager.getInstance().getUserDAO().getUserList();
		
		for(int i = 0 ; i < userList.size() ; i ++){
			UserData data = userList.get(i);
			userMap.put(data.getUsername(), data);
		}
	}
	

}
