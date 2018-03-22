package com.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 系统健康检查任务基类
 * 
 * @author Administrator
 *
 */
public abstract class BaseHealthCheckTask implements Runnable {

	// 同步计数闩对象
	private CountDownLatch latch;

	// 要进行健康检查的服务名称
	protected String serviceName;

	// 健康检查是否完成
	protected boolean checkOk;

	public BaseHealthCheckTask(CountDownLatch latch, String serviceName) {
		this.latch = latch;
		this.serviceName = serviceName;
		// checkOk初始化为false，等正常完成健康检查操作之后赋值true
		this.checkOk = false;
	}

	public String getServiceName() {
		return serviceName;
	}

	public boolean isCheckOk() {
		return checkOk;
	}

	@Override
	public void run() {
		try {
			// 执行具体的健康检查工作
			doCheck();
			// 检查成功，给检查成功标志对象赋值true
			this.checkOk = true;
		} catch (Exception e) {
			// 如果执行健康检查任务过程中发生异常，不再给checkOk赋值true，表示健康检查失败
			e.printStackTrace();
		}

		// 健康检查完成之后（不管成功还是失败），将同步计数闩的计数值减1
		this.latch.countDown();
	}

	/**
	 * 需要由子类实现的抽象方法，用于做具体的健康检查工作
	 */
	protected abstract void doCheck() throws Exception;
}
