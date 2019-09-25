/** 
 * Project Name:Test 
 * File Name:ThreadDemo.java 
 * Package Name:thread 
 * Date:2019年3月25日下午2:14:35 
 * Copyright (c) 2019, 交艺网 All Rights Reserved. 
 * 
*/  
  
package com.supertrampai.myutils.demo.thread;
/** 
 * ClassName:ThreadDemo <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年3月25日 下午2:14:35 <br/> 
 * @author   lxh95529@outlook.com
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class ThreadDemo extends Thread {

	@Override
	public void run() {//重写run方法，在run方法里面定义具体执行的任务
		// TODO Auto-generated method stub
		for(int i=1;i<=10;i++) {
			System.out.println(this.getName()+":"+i);
		}
	}
	public static void main(String[] args) {
		ThreadDemo td1=new ThreadDemo();
		ThreadDemo td2=new ThreadDemo();
		td1.setName("t1");
		td2.setName("t2");
		td1.start();
		td2.start();
	}
}
 