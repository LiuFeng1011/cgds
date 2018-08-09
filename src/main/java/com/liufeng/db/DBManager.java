package com.liufeng.db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liufeng.db.dao.AppDAO;
import com.liufeng.db.dao.KVDataDAO;
import com.liufeng.db.dao.UserDAO;




public class DBManager {
	private static DBManager dbManager = null;
	private ApplicationContext context = null;

	private AppDAO appDAO;
	private UserDAO userDAO;
	private KVDataDAO kvdataDAO;
	
	public static DBManager getInstance() {
		if (dbManager == null) {
			dbManager = new DBManager();
		}
		return dbManager;
	}


	public void init() {
		context = new ClassPathXmlApplicationContext("applicationContext-game.xml");

		appDAO = (AppDAO) context.getBean("appDAO");
		userDAO = (UserDAO) context.getBean("userDAO");
		kvdataDAO = (KVDataDAO) context.getBean("kvdataDAO");
	}

	public ApplicationContext getContext() {
		return context;
	}


	public void setContext(ApplicationContext context) {
		this.context = context;
	}


	public AppDAO getAppDAO() {
		return appDAO;
	}


	public void setAppDAO(AppDAO appDAO) {
		this.appDAO = appDAO;
	}


	public UserDAO getUserDAO() {
		return userDAO;
	}


	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	public KVDataDAO getKvdataDAO() {
		return kvdataDAO;
	}


	public void setKvdataDAO(KVDataDAO kvdataDAO) {
		this.kvdataDAO = kvdataDAO;
	}


}
