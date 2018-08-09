package com.liufeng.cgds;

import org.quartz.JobKey;
import org.quartz.SchedulerException;



/**
 * 计划任务管理器
 * @author mingming
 * @date 2017年2月8日-下午4:16:12
 * @desc:
 */
public class SchedulerManager {

	public static SchedulerServer scheduler;
	
	public static void schedule(SchedulerServer schedulerServer) {
		scheduler = schedulerServer;
		
//		scheduler.addJob(RankJob.class, "0 0 0 * * ?"); //每天触发一次
		
		try {
			scheduler.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加一个定时消息任务规则
	 */
	public static void addCrontabRule(Class c,String rule){
		if(SchedulerManager.scheduler != null){
			SchedulerManager.scheduler.addJob(c, rule);
		}
	}
	/**
	 * 删除一条消息任务
	 * @return
	 * @throws SchedulerException 
	 */
	public static boolean removeCrontabRule(JobKey jobkey) throws SchedulerException{
		boolean result = false;
		if(SchedulerManager.scheduler != null){
			SchedulerManager.scheduler.del(jobkey);
		}
		
		
		return result;
	}
	
	public static void main(){
		
	}
}
