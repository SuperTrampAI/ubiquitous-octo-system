/** 
 * Project Name:Test 
 * File Name:Clild.java 
 * Package Name:init 
 * Date:2019年3月29日下午2:53:49 
 * Copyright (c) 2019, 交艺网 All Rights Reserved. 
 * 
*/  
  
package com.supertrampai.myutils.demo.init;
/** 
 * ClassName:Clild <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年3月29日 下午2:53:49 <br/> 
 * @author   lxh95529@outlook.com
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class Clild extends Person {
	
	private static int c=1;
	private int c1=0;
	static {
		System.out.println("子类静态成员初始化"+c);
		c=2;
		System.out.println("子类静态代码块初始化"+c);
	}
	public Clild() {
		c=3;
		System.out.println("子类构造函数初始化");
	}
	{
		System.out.println("c="+c);
		System.out.println("子类普通代码块");
	}
	public static void main(String[] args) {
		new Clild();
	}

}
 