package com.lambda.test.sample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ����ɾ���б��в�����ĳ��������Ԫ��
 * 
 * @author Administrator
 *
 */
public class RemoveListElement {
	public static void main(String[] args) {
		// method1();
		// �����
		/*
		 * [1, abc, null, 123456, ] 667562667 [123456] 1450575490
		 */

		// method2();
		// �����
		/*
		 * [1, abc, null, 123456, ] 667562667 [123456] 1450575490
		 */

		// method3();
		// ����java.util.ConcurrentModificationException

		method4();
		// �����
		/*
		 * [1, abc, null, 123456, ] 667562667 [123456] 1450575490
		 */
	}

	/**
	 * ��ʽ1
	 */
	public static void method1() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("abc");
		list.add(null);
		list.add("123456");
		list.add("");
		System.out.println(list);
		System.out.println(list.hashCode());

		list.removeIf(e -> isNotValid(e));
		System.out.println(list);
		System.out.println(list.hashCode());
	}

	/**
	 * ��ʽ2
	 */
	public static void method2() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("abc");
		list.add(null);
		list.add("123456");
		list.add("");
		System.out.println(list);
		System.out.println(list.hashCode());

		list.removeIf(RemoveListElement::isNotValid);
		System.out.println(list);
		System.out.println(list.hashCode());
	}

	/**
	 * ��ȣ���ʽ3
	 */
	public static void method3() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("abc");
		list.add(null);
		list.add("123456");
		list.add("");

		for (String str : list) {
			if (isNotValid(str)) {
				list.remove(str);
			}
		}
		System.out.println(list);
		System.out.println(list.hashCode());
	}

	/**
	 * ��ȣ���ʽ4
	 */
	public static void method4() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("abc");
		list.add(null);
		list.add("123456");
		list.add("");
		System.out.println(list);
		System.out.println(list.hashCode());

		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String str = it.next();
			if (isNotValid(str)) {
				it.remove();
			}
		}
		System.out.println(list);
		System.out.println(list.hashCode());
	}

	/**
	 * У��һ���ַ����Ƿ�Ϸ��ķ������ַ����ĳ��ȴ��ڵ���6���ǺϷ���
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValid(String str) {
		if (null == str || str.length() < 6) {
			return false;
		}
		return true;
	}

	public static boolean isNotValid(String str) {
		return !isValid(str);
	}
}
