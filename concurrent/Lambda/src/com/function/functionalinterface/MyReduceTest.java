package com.function.functionalinterface;

import java.util.ArrayList;
import java.util.List;

public class MyReduceTest {
	public static void main(String[] argv) {
		// 构造一个整型列表和一个字符串列表
		List<Integer> nums = new ArrayList<Integer>();
		List<String> strs = new ArrayList<String>();
		for (int i = 1; i <= 10; i++) {
			nums.add(i);
			strs.add(i + ";");
		}
		
		// 输出整型列表所有元素求和的结果
		System.out.println(MyReduce.reduce((n1, n2) -> n1 + n2, nums));
		// 输出拼接字符串列表每个元素的结果
		System.out.println(MyReduce.reduce((s1, s2) -> s1 + s2, strs));
	}
}
