/** 
 * Project Name:Test 
 * File Name:Dog.java 
 * Package Name:init 
 * Date:2019年3月29日上午11:44:25 
 * Copyright (c) 2019, 交艺网 All Rights Reserved. 
 * 
*/  
  
package com.supertrampai.myutils.demo.init;
/** 
 * ClassName:Dog <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年3月29日 上午11:44:25 <br/> 
 * @author   lxh95529@outlook.com
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class Dog {

	Animal a=new Animal(3);//实例化多次
	
	static Animal b=new Animal(4);
	
	public Dog() {
		System.out.println("Dog contructor method");
	}
	public void d(int i) {
		System.out.println("d("+i+")");
		
	}

	static Animal c=new Animal(5);
}
 