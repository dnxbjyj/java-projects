package com.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * ϵͳ��������������
 * 
 * @author Administrator
 *
 */
public abstract class BaseHealthCheckTask implements Runnable {

	// ͬ�������Ŷ���
	private CountDownLatch latch;

	// Ҫ���н������ķ�������
	protected String serviceName;

	// ��������Ƿ����
	protected boolean checkOk;

	public BaseHealthCheckTask(CountDownLatch latch, String serviceName) {
		this.latch = latch;
		this.serviceName = serviceName;
		// checkOk��ʼ��Ϊfalse����������ɽ���������֮��ֵtrue
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
			// ִ�о���Ľ�����鹤��
			doCheck();
			// ���ɹ��������ɹ���־����ֵtrue
			this.checkOk = true;
		} catch (Exception e) {
			// ���ִ�н��������������з����쳣�����ٸ�checkOk��ֵtrue����ʾ�������ʧ��
			e.printStackTrace();
		}

		// ����������֮�󣨲��ܳɹ�����ʧ�ܣ�����ͬ�������ŵļ���ֵ��1
		this.latch.countDown();
	}

	/**
	 * ��Ҫ������ʵ�ֵĳ��󷽷�������������Ľ�����鹤��
	 */
	protected abstract void doCheck() throws Exception;
}
