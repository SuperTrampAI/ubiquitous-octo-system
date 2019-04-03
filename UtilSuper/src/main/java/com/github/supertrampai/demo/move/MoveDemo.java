/** 
 * Project Name:Test 
 * File Name:MoveDemo.java 
 * Package Name:move 
 * Date:2019年3月21日下午3:41:46 
 * Copyright (c) 2019, 交艺网 All Rights Reserved. 
 * 
*/  
  
package com.github.supertrampai.demo.move;  
/** 
 * ClassName:MoveDemo <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年3月21日 下午3:41:46 <br/> 
 * @author   lxh95529@outlook.com
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class MoveDemo {

	public static void main(String[] args) {
		System.out.println(2<<2);// m<<n 相当于	m乘以2的n次方
		System.out.println(3<<3);
		
		System.out.println(5>>2);//m>>n即相当于m除以2的n次方
		System.out.println(5>>>2);//m>>>n：整数m表示的二进制右移n位，不论正负数，高位都补0,
		System.out.println(-5>>>2);
		System.out.println("----------------");
		Integer a=2;
		Integer b=3;
		if ((a & 1) == 0) {
			System.out.println(a+":为偶数");
		}else {
			System.out.println(a+":为奇数");
		}
		System.out.println("----------------");
		System.out.println(a+" "+b);
		a ^= b;
		System.out.println(a+" "+b);
		b ^= a;
		System.out.println(a+" "+b);
		a ^= b;
		System.out.println(a+" "+b);
		
		
		
	}
}
 