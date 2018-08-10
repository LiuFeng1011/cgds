package com.liufeng.cgds.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liufeng.cgds.GameConfig;
import com.liufeng.db.DBManager;
import com.liufeng.utils.TimeUtils;
import com.liufeng.utils.Tools;

public class AppManager {
	private static AppManager instance = null;
	
	public static AppManager getInstance()
	{
	    if (instance == null)
	    {
	      instance = new AppManager();
	    }

	    return instance;
	}
	
	Map<String,AppData> appMap = new HashMap<String,AppData>();
	
	public void Init(){
		List<AppData> appList = DBManager.getInstance().getAppDAO().getAppList();
		
		for(int i = 0 ; i < appList.size() ; i ++){
			AppData data = appList.get(i);
			appMap.put(data.getId()+"", data);
		}
	}
	
	//创建应用
	public void CreateApp(String appname,long resettime,String userid){
		AppData data = new AppData();
		
		data.setAppname(appname);
		data.setCreatetime(TimeUtils.nowLong());
		data.setKey(Tools.getNonceStr(GameConfig.app_key_length));
		data.setUserid(userid);
		
		long id = DBManager.getInstance().getAppDAO().addApp(data);
		data.setId(id);
		appMap.put(id+"",data);
	}
	

	//获取应用列表
	public Map<String, AppData> getAppMap() {
		return appMap;
	}

	
	
}
