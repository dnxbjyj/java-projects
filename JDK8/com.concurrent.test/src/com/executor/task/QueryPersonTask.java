package com.executor.task;

import java.util.concurrent.Callable;

import com.executor.model.Person;
import com.executor.util.PersonRestAPIUtil;

/**
 * ��ѯ�����û���Ϣ������
 * 
 * @author Administrator
 *
 */
public class QueryPersonTask implements Callable<Person> {

	private String id;

	public QueryPersonTask(String id) {
		this.id = id;
	}

	/**
	 * call����������ִ�е����壬
	 */
	@Override
	public Person call() throws Exception {
		return PersonRestAPIUtil.queryPersonFromAPI(this.id);
	}

}
