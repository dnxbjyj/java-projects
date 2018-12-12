package com.function.functionalinterface;

import java.util.ArrayList;
import java.util.List;

/**
 * ʵ��map�����ĺ���ʽ�ӿ�
 *
 * @param <T>
 * @param <R>
 */
@FunctionalInterface
public interface MyMap<T, R> {
	/**
	 * ������������ǩ������
	 * 
	 * @param t
	 * @return
	 */
	R operate(T t);

	/**
	 * ʵ��map���ܵľ�̬����
	 * 
	 * @param function
	 *            ��������
	 * @param sequence
	 *            ����������
	 * @return
	 */
	public static <V, E> List<E> map(MyMap<V, E> function, List<V> sequence) {
		// ������������������Ϊ�գ��򷵻�null
		if (null == function || null == sequence || sequence.isEmpty()) {
			return null;
		}

		List<E> results = new ArrayList<E>();
		sequence.forEach(e -> results.add(function.operate(e)));

		return results;
	}
}
