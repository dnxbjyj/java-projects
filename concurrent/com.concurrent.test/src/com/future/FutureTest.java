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
	 * Runnable�߳�������
	 * 
	 * @author Administrator
	 *
	 */
	public static class MyRunnableTask implements Runnable {
		@Override
		public void run() {
			// ������ʱ
			long start = System.currentTimeMillis();
			System.out.println("do something in run@"
					+ Thread.currentThread().getId());
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			long end = System.currentTimeMillis();
			System.out.println("�߳�@" + Thread.currentThread().getId() + "�����ˣ�"
					+ (end - start) + "����");

		}
	}

	/**
	 * ʵ��Callable�����࣬���Ͳ���Ϊcall�����ķ�������
	 * 
	 * @author Administrator
	 *
	 */
	public static class MyCallableTask implements Callable<String> {
		@Override
		public String call() throws Exception {
			// ������ʱ
			long start = System.currentTimeMillis();
			System.out.println("do something in call@"
					+ Thread.currentThread().getId());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			long end = System.currentTimeMillis();
			System.out.println("�߳�@" + Thread.currentThread().getId() + "�����ˣ�"
					+ (end - start) + "����");

			// ���ص�ǰ���߳�ID���ϵ�ǰ��ʱ�������
			return "result: " + String.valueOf(Thread.currentThread().getId())
					+ "@" + System.currentTimeMillis();
		}

	}

	public static void main(String[] args) {
		// testRunnable();
		testCallable();
	}

	/**
	 * ���� Runnable����
	 */
	public static void testRunnable() {
		// ������ʱ
		long start = System.currentTimeMillis();

		// ����һ���̳߳�
		ExecutorService executor = Executors.newCachedThreadPool();
		// ִ��ʮ������
		for (int i = 0; i < 10; i++) {
			executor.submit(new MyRunnableTask());
		}

		long end = System.currentTimeMillis();
		System.out.println("testRunnable���������ˣ�" + (end - start) + "����");
	}

	/**
	 * ����Callable����
	 */
	public static void testCallable() {
		// ������ʱ
		long start = System.currentTimeMillis();

		// ����һ���̳߳�
		ExecutorService executor = Executors.newCachedThreadPool();
		// �̷߳��ؽ�������Ͳ���ΪFuture<T>��TΪcall�����ķ�������
		List<Future<String>> results = new ArrayList<Future<String>>();
		// ִ��ʮ������
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
		System.out.println("testRunnable���������ˣ�" + (end - start) + "����");
	}

}
