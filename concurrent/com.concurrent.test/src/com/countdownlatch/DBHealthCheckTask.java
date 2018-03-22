package com.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * ���ݿ���񽡿����������
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
		// sleep 3�룬ģ��ִ�����ݿ⽡���������
		Thread.sleep(3000);
		System.out.println("finish to check: " + this.serviceName + " health!");
	}

}
