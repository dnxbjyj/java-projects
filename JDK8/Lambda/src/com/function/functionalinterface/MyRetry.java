package com.function.functionalinterface;

import java.util.concurrent.TimeUnit;

/**
 * ���Ժ���ʽ�ӿ�
 *
 */
@FunctionalInterface
public interface MyRetry {
	/**
	 * ִ��ĳ�ֲ����ĺ����ṹ���壬û����Σ�����ֵΪ��������
	 * @return
	 */
	boolean action();

	/**
	 * ������Դ���5��
	 */
	public static final int MAX_RETRY_COUNT_5 = 5;

	/**
	 * ���Լ��ʱ��3��
	 */
	public static final int INTERVAL_SECONDS_3 = 3;

	/**
	 * ʵ������
	 * 
	 * @param maxRetryCount
	 *            ������Դ���
	 * @param workFunction
	 *            ÿ��������ִ�еĹ�������
	 * @param intervalFunctin
	 *            ���Լ���ڼ�ִ�еĺ���������sleepһ��ʱ��
	 */
	public static void retry(int maxRetryCount, MyRetry workFunction,
			MyRetry intervalFunctin) {
		// ���������Դ���С��1�������Թ�������Ϊ�գ�ֱ�ӷ���
		if (maxRetryCount < 1 || null == workFunction) {
			return;
		}

		// ִ��������Դ��������Բ���
		for (int i = 0; i < maxRetryCount; i++) {
			// ������ε�ִ�н��Ϊ�ɹ������ټ�������
			if (workFunction.action()) {
				return;
			}

			// ������ε�ִ�н��Ϊʧ�ܣ���ִ�����Լ������֮���������ִ�й�������
			intervalFunctin.action();
		}

		System.out.println("have tried maxRetryCount!");
	}

	/**
	 * �õ�ǰ�߳�˯��ָ������
	 * 
	 * @param seconds
	 * @return
	 */
	public static boolean doSleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
			return true;
		} catch (InterruptedException e) {
			System.out.println("exception occurs when doSleep, e = " + e);
			return false;
		}
	}
}
