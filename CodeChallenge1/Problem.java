package com.revature.producer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem {
	static List<Integer> intList = Collections.synchronizedList(new ArrayList<Integer>());
	public static void main(String[] args) {
		intList.add(5);
		
		Thread t1 = new Consumer();
		t1.start();
		
		Thread t2 = new Producer();
		t2.start();
		
		
		
	}
	
}
