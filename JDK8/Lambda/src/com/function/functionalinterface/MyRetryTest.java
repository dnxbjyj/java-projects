package com.function.functionalinterface;

/**
 * 测试重试函数式接口
 *
 */
public class MyRetryTest {

	public static void main(String[] args) {
		// 工作函数入参
		boolean param = true;
		MyRetry.retry(MyRetry.MAX_RETRY_COUNT_5, () -> doWork(param),
				() -> MyRetry.doSleep(MyRetry.INTERVAL_SECONDS_3));
	}

	/**
	 * 工作函数
	 * 
	 * @param param
	 *            一个布尔类型的入参
	 * @return
	 */
	public static boolean doWork(boolean param) {
		System.out.println("start to do some work...");

		// 为简单期间，将入参param的值取反，模拟工作函数的执行结果
		boolean result = !param;
		if (result) {
			System.out.println("doWork success!");
		} else {
			System.out.println("doWork failed!");
		}

		return result;
	}

}
