package com.executor.business;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.executor.model.Person;
import com.executor.task.QueryPersonTask;
import com.executor.util.PersonRestAPIUtil;

public class PersonBusiness {
	public static void main(String[] args) throws Exception {
		// 构建20个用户的ID列表
		List<String> ids = new ArrayList<String>();
		for (int i = 1; i <= 20; i++) {
			ids.add(String.valueOf(i));
		}
		
		// 单线程查询
		singleThreadQueryPersons(ids);
		// 并发查询
		concurrentQueryPersons(ids);
	}

	/**
	 * 单线程查询多个用户信息
	 * 
	 * @param ids
	 * @return
	 */
	private static List<Person> singleThreadQueryPersons(List<String> ids) {
		// 计时开始
		long start = System.currentTimeMillis();

		List<Person> persons = new ArrayList<Person>();

		// 遍历每个用户ID，依次查询用户信息
		for (String id : ids) {
			Person p = PersonRestAPIUtil.queryPersonFromAPI(id);
			persons.add(p);
		}

		// 计时结束
		long end = System.currentTimeMillis();
		System.out.println("查询" + ids.size()
				+ "个用户，singleThreadQueryPersons方法共耗时：" + (end - start) + "毫秒");

		return persons;
	}

	private static List<Person> concurrentQueryPersons(List<String> ids)
			throws Exception {
		// 计时开始
		long start = System.currentTimeMillis();

		// 1. 构建查询多个用户信息的Callable任务列表
		List<Callable<Person>> tasks = new ArrayList<Callable<Person>>();
		for (String id : ids) {
			QueryPersonTask task = new QueryPersonTask(id);
			tasks.add(task);
		}

		// 2. 并发执行多个任务，并获取并发执行结果
		// 2.1 获取线程池
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(100);

		// 2.2 执行任务，并获取任务执行结果
		List<Future<Person>> futureResults = new ArrayList<Future<Person>>();
		try {
			futureResults = executor.invokeAll(tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 2.3 从futureResults中获取并解析出Person列表
		List<Person> persons = new ArrayList<Person>();
		for (Future<Person> ret : futureResults) {
			Person p;
			try {
				p = ret.get(); // get()方法会阻塞等到，直到获取到结果为止
				if (null != p) {
					persons.add(p);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw new Exception("InterruptedException occurs.");
			} catch (ExecutionException e) {
				e.printStackTrace();
				throw new Exception("ExecutionException occurs.");
			}
		}

		// 计时结束
		long end = System.currentTimeMillis();
		System.out.println("查询" + persons.size()
				+ "个用户，concurrentQueryPersons方法共耗时：" + (end - start) + "毫秒");
		return persons;
	}
}
