package com.function.functionalinterface;

import java.util.List;

/**
 * 实现reduce功能的函数式接口
 *
 * @param <T>
 */
@FunctionalInterface
public interface MyReduce<T> {
	
	/**
	 * 匿名函数方法签名定义
	 * @param t1
	 * @param t2
	 * @return
	 */
	T operate(T t1, T t2);
	
	/**
	 * 实现reduce功能的静态方法
	 * @param function 匿名函数
	 * @param sequence 待操作序列
	 * @return
	 */
	public static <E> E reduce(MyReduce<E> function, List<E> sequence) {
		// 如果传入的函数为空，或序列为空，则返回null
		if (null == function || null == sequence || 0 == sequence.size()) {
			return null;
		}
		
		// 如果传入的序列只有一个元素，则返回这个元素
		if (1 == sequence.size()) {
			return sequence.get(0);
		}
		
		// 如果传入的序列至少有两个元素，则返回对所有元素从前到后依次进行两两操作的结果
		E result = sequence.get(0);
		for (int i = 1; i < sequence.size(); i++) {
			result = function.operate(result, sequence.get(i));
		}

		return result;
	}
}
