package com.executor.task;

import java.util.concurrent.Callable;

import com.executor.model.Person;
import com.executor.util.PersonRestAPIUtil;

/**
 * 查询单个用户信息任务类
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
	 * call方法是任务执行的主体，
	 */
	@Override
	public Person call() throws Exception {
		return PersonRestAPIUtil.queryPersonFromAPI(this.id);
	}

}
