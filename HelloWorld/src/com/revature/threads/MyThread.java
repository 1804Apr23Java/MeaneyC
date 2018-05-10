package com.revature.threads;

public class MyThread extends Thread{
	
	@Override
	public void run() {
		System.out.println("Hello from "+this.getName());
		
		for (int i=0;i<100;i++)
		{
			Counter.incrementCount();
			System.out.println(this.getName()+"incremented count for iteration: "+i);
			System.out.println(Counter.getCount());
		}
	}
}
