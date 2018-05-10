package com.revature.threads;

public class Loom {

		public static void main(String[] args) {
			
			Thread t1 = new MyThread();
			t1.start();
			
			Thread t2 = new MyThread();
			t2.start();
			
			//other approach for creating a thread
			//Thread t3 = new Thread(new MyRunnable());
			//t3.start();
			
			System.out.println(Counter.getCount());
		}
}
