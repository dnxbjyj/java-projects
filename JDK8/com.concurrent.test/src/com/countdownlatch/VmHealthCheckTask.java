package com.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 虚拟机服务健康检查任务类
 * 
 * @author Administrator
 *
 */
public class VmHealthCheckTask extends BaseHealthCheckTask {

	public VmHealthCheckTask(CountDownLatch latch) {
		super(latch, "VmService");
	}

	@Override
	public void doCheck() throws Exception {
		System.out
				.println("start to check: " + this.serviceName + " health...");
		// sleep 4秒，模拟执行虚拟机健康检查任务
		Thread.sleep(4000);
		System.out.println("finish to check: " + this.serviceName + " health!");
	}

}
