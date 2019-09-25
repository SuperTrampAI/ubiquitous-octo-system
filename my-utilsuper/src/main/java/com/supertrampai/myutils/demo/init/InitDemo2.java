/** 
 * Project Name:Test 
 * File Name:InitDemo2.java 
 * Package Name:init 
 * Date:2019年3月29日上午11:16:29 
 * Copyright (c) 2019, 交艺网 All Rights Reserved. 
 * 
*/  
  
package com.supertrampai.myutils.demo.init;
/** 
 * ClassName:InitDemo2 <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年3月29日 上午11:16:29 <br/> 
 * @author   lxh95529@outlook.com
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class InitDemo2 {

	private int i;
	private static int j;
	{
		System.out.println(i);
		System.out.println(j);
		System.out.println(getX());
	}
	public InitDemo2() {
		i=1;
		j=2;
		x=3;
		System.out.println(i);
		System.out.println(j);
		System.out.println(x);
	}
	private static int x=2;
	public int getX() {
		return x;
	}
	public static void main(String[] args) {
		new InitDemo2();
	}
	
}
 