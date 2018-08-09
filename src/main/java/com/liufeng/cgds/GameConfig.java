package com.liufeng.cgds;


import java.io.FileNotFoundException;
import java.io.IOException;

import com.liufeng.utils.PropertyManager;


/**
 * 游戏配置信息
 * 
 * @author admin
 * @date 2012-4-23 TODO
 */
public class GameConfig {
	static PropertyManager pm = null;

	//http端口号
	public static int http_port  = 20001;
	public static int app_key_length = 32;

	public static String FILENAME = "config/game.properties";

	public static void load() throws FileNotFoundException, IOException {
		
		pm = new PropertyManager(FILENAME);
		
		http_port = Integer.parseInt(pm.getString("http_port"));
		app_key_length = Integer.parseInt(pm.getString("app_key_length"));

	}

	public static PropertyManager getPm() {
		return pm;
	}

	public static void setPm(PropertyManager pm) {
		GameConfig.pm = pm;
	}

}
