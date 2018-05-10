package com.revature.threads;

public class Counter {

	private static int count;
	
	public static synchronized int getCount() {
		return count;
	}
	
	public static synchronized void incrementCount() {
		count += 1;
	}
}
