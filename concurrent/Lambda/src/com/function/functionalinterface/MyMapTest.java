package com.function.functionalinterface;

import java.util.ArrayList;
import java.util.List;

public class MyMapTest {

	public static void main(String[] args) {
		// ����һ�������б�
		List<Integer> nums = new ArrayList<Integer>();
		for (int i = 1; i <= 10; i++) {
			nums.add(i);
		}

		// �������б�ÿ��Ԫ���ȳ�10����ת��Ϊ�ַ�������ƴ����һ�����ţ���������õ����ַ����б�
		System.out
				.println(MyMap.map((e) -> String.valueOf(e * 10) + ";", nums));
	}

}
