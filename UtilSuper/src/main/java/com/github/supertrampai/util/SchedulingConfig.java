package com.github.supertrampai.util;

import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
public class SchedulingConfig implements SchedulingConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		// 设定一个长度10的定时任务线程池
		taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
	}

	@Scheduled(cron = "0 0/5 * * * ?")
	public void autoBidStatus() {
		// 每5分钟执行一次
		//调用持久化操作
	}

	@Scheduled(cron = "0 0/10 * * * ?")
	public void pmtStatus1() {
		// 每10分钟执行一次
		//调用持久化操作
	}

	@Scheduled(cron = "0 1/10 * * * ?")
	public void pmtStatus2() {
		// 每10分钟执行一次
		//调用持久化操作
	}

	@Scheduled(cron = "0 2/10 * * * ?")
	public void pmtStatus3() {
		//调用持久化操作
	}

	@Scheduled(cron = "0 0 16 * * ?")
	public void pmtStatus6() {
		// 15:30
		//调用持久化操作
	}

	@Scheduled(cron = "0 0/5 * * * ?")
	public void orderCancel() {
		// 5分钟一次
		//调用持久化操作
	}

	// @Scheduled(cron = "30 0 0/4 * * ?")
	@Scheduled(cron = "30 0/5 * * * ?")
	public void orderAutoBalance() {
		// 4小时一次
		//调用持久化操作
	}

	@Scheduled(cron = "50 10 0/4 * * ?") // 4小时一次
	public void orderAutoReceive() {
		//调用持久化操作
	}

	@Scheduled(cron = "0 2/10 * * * ?") // 每10分钟一次
	public void autoDeduct() {
		//调用持久化操作
	}

	@Scheduled(cron = "0 0 * * * ?") // 每小时一次
	public void autoUpdateFinanceIndex() {
		//调用持久化操作
	}

	@Scheduled(cron = "0 5 * * * ?") // 每小时一次
	public void autoUpdateArtistPriceRange() {
		//调用持久化操作
	}

	@Scheduled(cron = "0 0 1 * * ?") // 每天1次
	public void autoUpdateArtistFollow() {
		//调用持久化操作
	}

	@Scheduled(cron = "0 6 * * * ?") // 每小时一次
	public void autoUpdateArtistViewCnt() {
		//调用持久化操作
	}

	@Scheduled(cron = "0 2 8/4 * * ?") // 每天4次
	public void autoUpdateArtworkFollow() {
		//调用持久化操作
	}

	@Scheduled(cron = "0 0 * * * ?") // 每小时一次
	public void autoUpdateArtworkViewCnt() {
	}

	@Scheduled(cron = "0 0 19 * * ?")
	public void storeEndMsg() {
		logger.info("快到期提前7天");
	}

	@Scheduled(cron = "0 0 8/4 * * ?") // 每天4次
	public void autoAddTakeNum() {
	}

	@Scheduled(cron = "30 0/20 * * * ?") // 每20一次
	public void autoUpdateArtistTakeNum() {
	}

	@Scheduled(cron = "50 0/20 * * * ?") // 每20一次
	public void autoUpdateBatchTakeNum() {
	}

	@Scheduled(cron = "40 0/10 * * * ?") // 每10一次
	// @Scheduled(cron = "0 * * * * ?") // 每10一次
	public void autoPublishArticle() {
	}

	/* (每月1日、11日、21日执行) */
	@Scheduled(cron = "0 0 0 1,11,21 * ?")
	public void recordArtistArtindexHis() {
	}

	/* 每天10点、15点 */
	@Scheduled(cron = "0 0 10,15 * * ?")
	public void sendSmsToPhysicalDisplayUser() {
	}

	/* 每天12点 17点 */
	@Scheduled(cron = "0 0 12,17 * * ?")
	public void batchUpdateDefalt() {
	}

	/* 每小时一次 */
	@Scheduled(cron = "0 0 */1 * * ?")
	public void batchUpdateBailUserCancel() {
	}

	/* 每分钟轮训一次 */
	@Scheduled(cron = "0 */1 * * * ?")
	public void museumOrderTask() {
	}
	
	/* 每半天轮训一次 */
	@Scheduled(cron = "0 0 1/13 * * ?")
	public void museumTask() {
	}
	
	@Scheduled(cron = "0 3/20 * * * ?")
	public void museumSellNumTask() {
	}
	

	@Scheduled(cron = "40 5/10 * * * ?") 
	public void musOrderAutoReceive() {
		logger.info("订单发货7天后自动收货");
	}
	
}