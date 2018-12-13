package com.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ϵͳ������
 * 
 * @author Administrator
 *
 */
public class SystemStarter {
	// ����������Ϊϵͳ����������ȫ��ֻ����һ��
	private static SystemStarter INSTANCE = new SystemStarter();

	// ͬ�������Ŷ���
	private CountDownLatch latch;

	// ����Ĭ�ϵ�ͬ����������������߳���Ϊ3
	private static final int LATCH_COUNT = 3;

	// Ĭ�ϵĳ�ʱ�ȴ�����
	private static final int DEFAULT_LATCH_TIMEOUT_SECOND = 60;

	private SystemStarter() {
		// ��ʼ����������ͬ���Ŷ���
		this.latch = new CountDownLatch(LATCH_COUNT);
	}

	public static SystemStarter getInstance() {
		return INSTANCE;
	}

	public void startUp() throws InterruptedException {
		// ���ں�����ʱ
		long start = System.currentTimeMillis();

		// ִ������������ִ��3����������߳�����
		Executor executor = Executors.newFixedThreadPool(LATCH_COUNT);
		List<BaseHealthCheckTask> tasks = new ArrayList<BaseHealthCheckTask>();
		// ʹ��ͬ�������Ŷ����ʼ��3�������������������ݿ⡢�����������������
		tasks.add(new DBHealthCheckTask(this.latch));
		tasks.add(new NetworkHealthCheckTask(this.latch));
		tasks.add(new VmHealthCheckTask(this.latch));

		// ����ִ�н����������
		for (BaseHealthCheckTask task : tasks) {
			executor.execute(task);
		}

		// ͬ���������������߳̽��еȴ���ֱ�������3�������������ȫ��ִ����ɣ��򳬹�Ĭ�ϳ�ʱʱ��ż�������ִ�����߳�
		this.latch.await(DEFAULT_LATCH_TIMEOUT_SECOND, TimeUnit.SECONDS);

		System.out.println("check system health FINISH!");

		// ���ÿ�������������ļ�������Ƿ���ͨ����
		for (BaseHealthCheckTask task : tasks) {
			System.out.println("health check result (is passed): "
					+ task.getServiceName() + " - " + task.isCheckOk());
			// һ����һ������ʧ�ܣ���ʾϵͳ����ʧ�ܣ��˳�������
			if (!task.isCheckOk()) {
				System.out.println("start up the system FAILED! "
						+ task.getServiceName() + " is NOT OK!");
				return;
			}
		}

		System.out.println("-----");

		// ��ȫ�����������ɺ󣬲ſ�ʼִ�����������е���������
		doOtherStartupWork();

		System.out.println("-----");

		long end = System.currentTimeMillis();
		System.out.println("start up the system FINISH! totally spent "
				+ (end - start) + "ms.");
	}

	/**
	 * �����������������н���������֮�󣬲���ִ����Щ����
	 * 
	 * @throws InterruptedException
	 */
	private void doOtherStartupWork() throws InterruptedException {
		System.out.println("do some other works...");
		Thread.sleep(1500);
	}
}
