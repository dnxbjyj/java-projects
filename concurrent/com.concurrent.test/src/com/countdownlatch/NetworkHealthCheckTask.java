package com.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 网络服务健康检查任务类
 * 
 * @author Administrator
 *
 */
public class NetworkHealthCheckTask extends BaseHealthCheckTask {

	public NetworkHealthCheckTask(CountDownLatch latch) {
		super(latch, "NetworkService");
	}

	@Override
	public void doCheck() throws InterruptedException {
		System.out
				.println("start to check: " + this.serviceName + " health...");
		// sleep 5秒，模拟执行网络健康检查任务
		Thread.sleep(5000);
		System.out.println("finish to check: " + this.serviceName + " health!");
	}

}
