package com.lambda.test.sample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * �����ò�ͬ�ķ�ʽ�����б�
 * 
 */
public class ListTraverse {

	public static void main(String[] args) {
		method1();
		method2();
		method3();
		method4();
		method5();
	}
	
	/**
	 * ʹ�÷������ñ����б�
	 */
	public static void method5() {
		List<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("12345");
		list.add("a1234");

		System.out.println("---method5 output:");
		list.forEach(System.out::println);
	}
	
	/**
	 * ʹ��lambda���ʽ�����б�
	 */
	public static void method4() {
		List<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("12345");
		list.add("a1234");

		System.out.println("---method4 output:");
		list.forEach(e -> System.out.println(e));
	}
	
	/**
	 * ʹ�õ����������б�
	 */
	public static void method3() {
		List<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("12345");
		list.add("a1234");

		System.out.println("---method3 output:");
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	/**
	 * ʹ��for-eachѭ�������б�
	 */
	public static void method2() {
		List<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("12345");
		list.add("a1234");

		System.out.println("---method2 output:");
		for (String str : list) {
			System.out.println(str);
		}
	}
	
	/**
	 * ʹ����򵥵�ѭ�������б�
	 */
	public static void method1() {
		List<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("12345");
		list.add("a1234");

		System.out.println("---method1 output:");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}
