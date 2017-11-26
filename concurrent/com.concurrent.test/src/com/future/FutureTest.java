package com.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {
	/**
	 * Runnable线程任务类
	 * 
	 * @author Administrator
	 *
	 */
	public static class MyRunnableTask implements Runnable {
		@Override
		public void run() {
			// 计算用时
			long start = System.currentTimeMillis();
			System.out.println("do something in run@"
					+ Thread.currentThread().getId());
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			long end = System.currentTimeMillis();
			System.out.println("线程@" + Thread.currentThread().getId() + "花费了："
					+ (end - start) + "毫秒");

		}
	}

	/**
	 * 实现Callable任务类，泛型参数为call方法的返回类型
	 * 
	 * @author Administrator
	 *
	 */
	public static class MyCallableTask implements Callable<String> {
		@Override
		public String call() throws Exception {
			// 计算用时
			long start = System.currentTimeMillis();
			System.out.println("do something in call@"
					+ Thread.currentThread().getId());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			long end = System.currentTimeMillis();
			System.out.println("线程@" + Thread.currentThread().getId() + "花费了："
					+ (end - start) + "毫秒");

			// 返回当前的线程ID加上当前的时间毫秒数
			return "result: " + String.valueOf(Thread.currentThread().getId())
					+ "@" + System.currentTimeMillis();
		}

	}

	public static void main(String[] args) {
		// testRunnable();
		testCallable();
	}

	/**
	 * 测试 Runnable任务
	 */
	public static void testRunnable() {
		// 计算用时
		long start = System.currentTimeMillis();

		// 创建一个线程池
		ExecutorService executor = Executors.newCachedThreadPool();
		// 执行十次任务
		for (int i = 0; i < 10; i++) {
			executor.submit(new MyRunnableTask());
		}

		long end = System.currentTimeMillis();
		System.out.println("testRunnable方法花费了：" + (end - start) + "毫秒");
	}

	/**
	 * 测试Callable任务
	 */
	public static void testCallable() {
		// 计算用时
		long start = System.currentTimeMillis();

		// 创建一个线程池
		ExecutorService executor = Executors.newCachedThreadPool();
		// 线程返回结果，泛型参数为Future<T>，T为call方法的返回类型
		List<Future<String>> results = new ArrayList<Future<String>>();
		// 执行十次任务
		for (int i = 0; i < 10; i++) {
			Future<String> result = executor.submit(new MyCallableTask());
			results.add(result);
		}

		for (Future<String> result : results) {
			try {
				System.out.println(result.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		long end = System.currentTimeMillis();
		System.out.println("testRunnable方法花费了：" + (end - start) + "毫秒");
	}

}
