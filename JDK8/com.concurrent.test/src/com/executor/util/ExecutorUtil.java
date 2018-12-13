package com.executor.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.executor.model.Person;

/**
 * Executor Framework������������
 * 
 * @author Administrator
 *
 */
public class ExecutorUtil {

	/**
	 * ����ִ��Callable���񷽷���֧�ַ��Ͳ���
	 * 
	 * @param tasks
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> concurrentExecute(List<Callable<T>> tasks)
			throws Exception {
		// 1. ��ȡ�̳߳�
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(100);

		// 2. ����ִ�����񣬲���ȡ���ؽ��
		List<Future<T>> futureResults = new ArrayList<Future<T>>();
		try {
			futureResults = executor.invokeAll(tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new Exception("InterruptedException occurs.");
		}

		// 3. ȡ�ز��������ؽ��
		List<T> results = getFromFutureResults(futureResults);

		return results;
	}

	/**
	 * �Ӳ���Future�����ȡ�ز����������֧�ַ��Ͳ���
	 * 
	 * @param futureResults
	 * @return
	 * @throws Exception
	 */
	private static <T> List<T> getFromFutureResults(
			List<Future<T>> futureResults) throws Exception {

		List<T> results = new ArrayList<T>();
		for (Future<T> ret : futureResults) {
			try {
				T r = ret.get(); // get()�����������ȵ���ֱ����ȡ�����Ϊֹ
				if (null != r) {
					results.add(r);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw new Exception("InterruptedException occurs.");
			} catch (ExecutionException e) {
				e.printStackTrace();
				throw new Exception("ExecutionException occurs.");
			}
		}

		return results;
	}

}
