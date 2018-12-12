package com.function.functionalinterface;

import java.util.ArrayList;
import java.util.List;

public class MyReduceTest {
	public static void main(String[] argv) {
		// ����һ�������б��һ���ַ����б�
		List<Integer> nums = new ArrayList<Integer>();
		List<String> strs = new ArrayList<String>();
		for (int i = 1; i <= 10; i++) {
			nums.add(i);
			strs.add(i + ";");
		}
		
		// ��������б�����Ԫ����͵Ľ��
		System.out.println(MyReduce.reduce((n1, n2) -> n1 + n2, nums));
		// ���ƴ���ַ����б�ÿ��Ԫ�صĽ��
		System.out.println(MyReduce.reduce((s1, s2) -> s1 + s2, strs));
	}
}
