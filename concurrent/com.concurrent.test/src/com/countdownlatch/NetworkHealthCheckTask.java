package com.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * ������񽡿����������
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
		// sleep 5�룬ģ��ִ�����罡���������
		Thread.sleep(5000);
		System.out.println("finish to check: " + this.serviceName + " health!");
	}

}
