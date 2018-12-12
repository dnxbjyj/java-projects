package com.function.functionalinterface;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现map操作的函数式接口
 *
 * @param <T>
 * @param <R>
 */
@FunctionalInterface
public interface MyMap<T, R> {
	/**
	 * 匿名函数方法签名定义
	 * 
	 * @param t
	 * @return
	 */
	R operate(T t);

	/**
	 * 实现map功能的静态方法
	 * 
	 * @param function
	 *            匿名函数
	 * @param sequence
	 *            待操作序列
	 * @return
	 */
	public static <V, E> List<E> map(MyMap<V, E> function, List<V> sequence) {
		// 如果函数或待操作序列为空，则返回null
		if (null == function || null == sequence || sequence.isEmpty()) {
			return null;
		}

		List<E> results = new ArrayList<E>();
		sequence.forEach(e -> results.add(function.operate(e)));

		return results;
	}
}
