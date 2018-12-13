package com.executor.util;

import com.executor.model.Person;

/**
 * RestAPI工具类
 * 
 * @author Administrator
 *
 */
public class PersonRestAPIUtil {
	/**
	 * 模拟调Rest API查询用户信息
	 * 
	 * @param id
	 * @return
	 */
	public static Person queryPersonFromAPI(String id) {
		Person p = new Person();
		
		// 模拟调接口耗时，300毫秒
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 模拟查询到的用户信息，并返回
		p.setId(id);
		p.setName("person#" + id);
		return p;
	}
}
