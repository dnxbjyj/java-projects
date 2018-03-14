package com.executor.util;

import com.executor.model.Person;

/**
 * RestAPI������
 * 
 * @author Administrator
 *
 */
public class PersonRestAPIUtil {
	/**
	 * ģ���Rest API��ѯ�û���Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public static Person queryPersonFromAPI(String id) {
		Person p = new Person();
		
		// ģ����ӿں�ʱ��300����
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// ģ���ѯ�����û���Ϣ��������
		p.setId(id);
		p.setName("person#" + id);
		return p;
	}
}
