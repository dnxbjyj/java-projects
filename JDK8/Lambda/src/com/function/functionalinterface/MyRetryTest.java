package com.function.functionalinterface;

/**
 * �������Ժ���ʽ�ӿ�
 *
 */
public class MyRetryTest {

	public static void main(String[] args) {
		// �����������
		boolean param = true;
		MyRetry.retry(MyRetry.MAX_RETRY_COUNT_5, () -> doWork(param),
				() -> MyRetry.doSleep(MyRetry.INTERVAL_SECONDS_3));
	}

	/**
	 * ��������
	 * 
	 * @param param
	 *            һ���������͵����
	 * @return
	 */
	public static boolean doWork(boolean param) {
		System.out.println("start to do some work...");

		// Ϊ���ڼ䣬�����param��ֵȡ����ģ�⹤��������ִ�н��
		boolean result = !param;
		if (result) {
			System.out.println("doWork success!");
		} else {
			System.out.println("doWork failed!");
		}

		return result;
	}

}
