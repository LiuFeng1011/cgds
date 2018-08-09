package com.liufeng.cgds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class Test {

	private static final Logger logger = LoggerFactory.getLogger(Test.class);
	

    public static void main( String[] args )
    {
    	String key = "a";
    	
    	String[] list = key.split(",");
    	logger.info("size : "+ list.length);
    	logger.info(JSON.toJSONString(list));
    }
}
