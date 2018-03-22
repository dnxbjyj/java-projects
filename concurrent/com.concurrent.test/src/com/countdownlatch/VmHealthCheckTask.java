package com.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * ��������񽡿����������
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
		// sleep 4�룬ģ��ִ������������������
		Thread.sleep(4000);
		System.out.println("finish to check: " + this.serviceName + " health!");
	}

}
