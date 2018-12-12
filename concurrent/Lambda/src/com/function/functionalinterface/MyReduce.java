package com.function.functionalinterface;

import java.util.List;

/**
 * ʵ��reduce���ܵĺ���ʽ�ӿ�
 *
 * @param <T>
 */
@FunctionalInterface
public interface MyReduce<T> {
	
	/**
	 * ������������ǩ������
	 * @param t1
	 * @param t2
	 * @return
	 */
	T operate(T t1, T t2);
	
	/**
	 * ʵ��reduce���ܵľ�̬����
	 * @param function ��������
	 * @param sequence ����������
	 * @return
	 */
	public static <E> E reduce(MyReduce<E> function, List<E> sequence) {
		// �������ĺ���Ϊ�գ�������Ϊ�գ��򷵻�null
		if (null == function || null == sequence || 0 == sequence.size()) {
			return null;
		}
		
		// ������������ֻ��һ��Ԫ�أ��򷵻����Ԫ��
		if (1 == sequence.size()) {
			return sequence.get(0);
		}
		
		// ����������������������Ԫ�أ��򷵻ض�����Ԫ�ش�ǰ�������ν������������Ľ��
		E result = sequence.get(0);
		for (int i = 1; i < sequence.size(); i++) {
			result = function.operate(result, sequence.get(i));
		}

		return result;
	}
}
