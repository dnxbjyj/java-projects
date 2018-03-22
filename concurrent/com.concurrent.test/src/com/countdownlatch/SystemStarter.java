package com.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 系统启动器
 * 
 * @author Administrator
 *
 */
public class SystemStarter {
	// 单例化，因为系统启动器对象全局只能有一个
	private static SystemStarter INSTANCE = new SystemStarter();

	// 同步计数闩对象
	private CountDownLatch latch;

	// 设置默认的同步计数闩所允许的线程数为3
	private static final int LATCH_COUNT = 3;

	// 默认的超时等待描述
	private static final int DEFAULT_LATCH_TIMEOUT_SECOND = 60;

	private SystemStarter() {
		// 初始化启动器的同步闩对象
		this.latch = new CountDownLatch(LATCH_COUNT);
	}

	public static SystemStarter getInstance() {
		return INSTANCE;
	}

	public void startUp() throws InterruptedException {
		// 用于函数计时
		long start = System.currentTimeMillis();

		// 执行器对象，用于执行3个健康检查线程任务
		Executor executor = Executors.newFixedThreadPool(LATCH_COUNT);
		List<BaseHealthCheckTask> tasks = new ArrayList<BaseHealthCheckTask>();
		// 使用同步计数闩对象初始化3个健康检查任务对象：数据库、网络和虚拟机健康检查
		tasks.add(new DBHealthCheckTask(this.latch));
		tasks.add(new NetworkHealthCheckTask(this.latch));
		tasks.add(new VmHealthCheckTask(this.latch));

		// 并发执行健康检查任务
		for (BaseHealthCheckTask task : tasks) {
			executor.execute(task);
		}

		// 同步计数闩阻塞主线程进行等待，直到上面的3个健康检查任务全部执行完成，或超过默认超时时间才继续往下执行主线程
		this.latch.await(DEFAULT_LATCH_TIMEOUT_SECOND, TimeUnit.SECONDS);

		System.out.println("check system health FINISH!");

		// 输出每个健康检查任务的检查结果（是否检查通过）
		for (BaseHealthCheckTask task : tasks) {
			System.out.println("health check result (is passed): "
					+ task.getServiceName() + " - " + task.isCheckOk());
			// 一旦有一个任务失败，显示系统启动失败，退出启动器
			if (!task.isCheckOk()) {
				System.out.println("start up the system FAILED! "
						+ task.getServiceName() + " is NOT OK!");
				return;
			}
		}

		System.out.println("-----");

		// 在全部健康检查完成后，才开始执行启动过程中的其他任务
		doOtherStartupWork();

		System.out.println("-----");

		long end = System.currentTimeMillis();
		System.out.println("start up the system FINISH! totally spent "
				+ (end - start) + "ms.");
	}

	/**
	 * 其他任务，依赖于所有健康检查完成之后，才能执行这些任务
	 * 
	 * @throws InterruptedException
	 */
	private void doOtherStartupWork() throws InterruptedException {
		System.out.println("do some other works...");
		Thread.sleep(1500);
	}
}
