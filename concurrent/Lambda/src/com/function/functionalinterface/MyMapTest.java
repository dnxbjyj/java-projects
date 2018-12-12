package com.function.functionalinterface;

import java.util.ArrayList;
import java.util.List;

public class MyMapTest {

	public static void main(String[] args) {
		// 构造一个整型列表
		List<Integer> nums = new ArrayList<Integer>();
		for (int i = 1; i <= 10; i++) {
			nums.add(i);
		}

		// 将整型列表每个元素先乘10，再转化为字符串，并拼接上一个逗号，最终输出得到的字符串列表
		System.out
				.println(MyMap.map((e) -> String.valueOf(e * 10) + ";", nums));
	}

}
