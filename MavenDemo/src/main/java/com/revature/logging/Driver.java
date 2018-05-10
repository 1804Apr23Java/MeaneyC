package com.revature.logging;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LoggingClass lc = new LoggingClass();
		lc.allTheLogs();
		
		try {
			System.out.println(divide(1,0));
		} catch(Exception e) {
			lc.giveFatal(e);
		}
		
		
		
	}
	
	public static int divide(int a, int b) {
		return a/b;
	}

}
