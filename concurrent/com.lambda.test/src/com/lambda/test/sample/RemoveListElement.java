package com.lambda.test.sample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 测试删除列表中不满足某种条件的元素
 * 
 * @author Administrator
 *
 */
public class RemoveListElement {
	public static void main(String[] args) {
		// method1();
		// 输出：
		/*
		 * [1, abc, null, 123456, ] 667562667 [123456] 1450575490
		 */

		// method2();
		// 输出：
		/*
		 * [1, abc, null, 123456, ] 667562667 [123456] 1450575490
		 */

		// method3();
		// 报错：java.util.ConcurrentModificationException

		method4();
		// 输出：
		/*
		 * [1, abc, null, 123456, ] 667562667 [123456] 1450575490
		 */
	}

	/**
	 * 方式1
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
	 * 方式2
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
	 * 类比：方式3
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
	 * 类比：方式4
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
	 * 校验一个字符串是否合法的方法，字符串的长度大于等于6才是合法的
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
