package com.liufeng.cgds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liufeng.db.DBManager;
import com.liufeng.cgds.user.UserManager;
import com.liufeng.http.HttpServer;

public class World {

	private static final Logger logger = LoggerFactory.getLogger(World.class);
	
    private static World instance = null;
    
    public static World getInstance()
	{
	    if (instance == null)
	    {
	      instance = new World();
	    }

	    return instance;
	}

	public void start(){
		try{

			logger.info("==============加载游戏配置=============");
			GameConfig.load();
			
			logger.info("==============初始化数据库=============");
			DBManager.getInstance().init();
			
			UserManager.getInstance();

			logger.info("==============启动计划任务管理器=============");
			SchedulerManager.schedule(new SchedulerServer());

			logger.info("==============开启http监听=============");
			HttpServer.getInstance().run(); 

		}catch(Exception e){
			e.printStackTrace();
		}
		
		//开启游戏主线程
		new MainLoop().start();
	}

	/**
	 * 游戏主线程
	 * 
	 */
	public class MainLoop extends Thread {
		long tickcount = 0;

		@Override
		public void run() {
			logger.info("启动主线程!");
			while (true) {
				try {
					// 游戏逻辑
					gameTick();
					Thread.sleep((long) 10f);
				} catch (Exception e) {
					logger.error("error in MainLoop tick : " + tickcount, e);
					tickcount++;
				}
			}
		}

		/**
		 * 游戏逻辑
		 */
		public void gameTick() {

		}

	}
}
