package com.liufeng.db;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.liufeng.cgds.data.KVData;


public class Test {
	private static final Logger logger = LoggerFactory.getLogger(Test.class);
	 public static void main(String[] args) {

			//数据库
			DBManager.getInstance().init();

			List<KVData> list = DBManager.getInstance().getKvdataDAO().getData("1", "1,2","a");
			logger.info(JSON.toJSONString(list));
	 }
}
