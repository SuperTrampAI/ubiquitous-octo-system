/** 
 * Project Name:Test 
 * File Name:Factorial.java 
 * Package Name:demo 
 * Date:2019年4月3日下午1:33:45 
 * Copyright (c) 2019, 交艺网 All Rights Reserved. 
 * 
*/

package com.supertrampai.myutils.demo.factorial;

/**
 * ClassName:Factorial <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2019年4月3日 下午1:33:45 <br/>
 * 
 * @author lxh95529@outlook.com
 * @version
 * @since JDK 1.8
 * @see
 */
public class Factorial {

	/**
	 * 
	 * factorial:(计算乘阶).
	 * 
	 * @author lxh95529@outlook.com
	 * @param n
	 * @return
	 * @since JDK 1.8
	 */
	public static int factorial(int n) {
		if (n == 1)
			return 1;
		else
			return n * factorial(n - 1);
	}

	/**
	 * 
	 * fibonacci:(斐波那契数列). 
	 * 
	 * @author lxh95529@outlook.com
	 * @param num
	 * @return 
	 * @since JDK 1.8
	 */
	long fibonacci(long num) {
		if (num == 0 || num == 1)
			return num;
		else
			return fibonacci(num - 1) + fibonacci(num - 2);

	}

	public static void main(String[] args) {
		System.out.println(factorial(4));
	}
}
