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
		// ����20���û���ID�б�
		List<String> ids = new ArrayList<String>();
		for (int i = 1; i <= 20; i++) {
			ids.add(String.valueOf(i));
		}
		
		// ���̲߳�ѯ
		singleThreadQueryPersons(ids);
		// ������ѯ
		concurrentQueryPersons(ids);
	}

	/**
	 * ���̲߳�ѯ����û���Ϣ
	 * 
	 * @param ids
	 * @return
	 */
	private static List<Person> singleThreadQueryPersons(List<String> ids) {
		// ��ʱ��ʼ
		long start = System.currentTimeMillis();

		List<Person> persons = new ArrayList<Person>();

		// ����ÿ���û�ID�����β�ѯ�û���Ϣ
		for (String id : ids) {
			Person p = PersonRestAPIUtil.queryPersonFromAPI(id);
			persons.add(p);
		}

		// ��ʱ����
		long end = System.currentTimeMillis();
		System.out.println("��ѯ" + ids.size()
				+ "���û���singleThreadQueryPersons��������ʱ��" + (end - start) + "����");

		return persons;
	}

	private static List<Person> concurrentQueryPersons(List<String> ids)
			throws Exception {
		// ��ʱ��ʼ
		long start = System.currentTimeMillis();

		// 1. ������ѯ����û���Ϣ��Callable�����б�
		List<Callable<Person>> tasks = new ArrayList<Callable<Person>>();
		for (String id : ids) {
			QueryPersonTask task = new QueryPersonTask(id);
			tasks.add(task);
		}

		// 2. ����ִ�ж�����񣬲���ȡ����ִ�н��
		// 2.1 ��ȡ�̳߳�
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(100);

		// 2.2 ִ�����񣬲���ȡ����ִ�н��
		List<Future<Person>> futureResults = new ArrayList<Future<Person>>();
		try {
			futureResults = executor.invokeAll(tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 2.3 ��futureResults�л�ȡ��������Person�б�
		List<Person> persons = new ArrayList<Person>();
		for (Future<Person> ret : futureResults) {
			Person p;
			try {
				p = ret.get(); // get()�����������ȵ���ֱ����ȡ�����Ϊֹ
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

		// ��ʱ����
		long end = System.currentTimeMillis();
		System.out.println("��ѯ" + persons.size()
				+ "���û���concurrentQueryPersons��������ʱ��" + (end - start) + "����");
		return persons;
	}
}
