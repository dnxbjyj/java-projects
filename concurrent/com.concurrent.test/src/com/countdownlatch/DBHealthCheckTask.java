package com.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 数据库服务健康检查任务类
 * 
 * @author Administrator
 *
 */
public class DBHealthCheckTask extends BaseHealthCheckTask {

	public DBHealthCheckTask(CountDownLatch latch) {
		super(latch, "DBService");
	}

	@Override
	public void doCheck() throws InterruptedException {
		System.out
				.println("start to check: " + this.serviceName + " health...");
		// sleep 3秒，模拟执行数据库健康检查任务
		Thread.sleep(3000);
		System.out.println("finish to check: " + this.serviceName + " health!");
	}

}
