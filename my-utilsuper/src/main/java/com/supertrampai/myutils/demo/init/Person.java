/** 
 * Project Name:Test 
 * File Name:Person.java 
 * Package Name:init 
 * Date:2019年3月29日下午2:48:32 
 * Copyright (c) 2019, 交艺网 All Rights Reserved. 
 * 
*/  
  
package com.supertrampai.myutils.demo.init;
/** 
 * ClassName:Person <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年3月29日 下午2:48:32 <br/> 
 * @author   lxh95529@outlook.com
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class Person {

	private static int p=1;
	static {
		System.out.println("父类静态成员变量初始化"+p);
		p=2;
		System.out.println("父类静态代码块初始化"+p);
	}
	
	public Person() {
		p=3;
		System.out.println("父类构造函数初始化"+p);
	}
	{
		System.out.println("父类普通代码块初始化");
	}
	
/*	public static void main(String[] args) {
		new Person();
	}*/
	
}
 