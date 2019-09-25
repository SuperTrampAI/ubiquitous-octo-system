/** 
 * Project Name:Test 
 * File Name:InitDemo.java 
 * Package Name:init 
 * Date:2019年3月28日下午6:10:32 
 * Copyright (c) 2019, 交艺网 All Rights Reserved. 
 * 
*/  
  
package com.supertrampai.myutils.demo.init;
/** 
 * ClassName:InitDemo <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年3月28日 下午6:10:32 <br/> 
 * @author   lxh95529@outlook.com
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class InitDemo {
	private int i;
	static {
		int j = 0;
		System.out.println(j);
		j=2;
		System.out.println(j);
	}
	public InitDemo() {}
	public InitDemo(int var) {
		this.i=var;
		System.out.println(i);
	}
	public InitDemo(String...strings) {
		System.out.println(strings.length);
	}
	public InitDemo(Integer...integers) {
		
	}
	public InitDemo(String str,String...strings) {
		System.out.println(strings.length);
	}
	public static void main(String[] args) {
		new InitDemo();
	}
}
 