package com.revature.producer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Producer extends Thread {
	List<Integer> intList = Collections.synchronizedList(new ArrayList<Integer>());
	Random randomNumberGenerator = new Random();
	int randomNumber = randomNumberGenerator.nextInt(100) + 0;
	/*public Producer(List<Integer> intList) {
		// TODO Auto-generated constructor stub
		this.intList = intList;
	}*/

	@Override
	public synchronized void run() {
		while(true) {
			while(!(intList.size() == 10)) {
				intList.add(this.randomNumber);
				intList.notifyAll();
				}
			try {
				intList.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("producer block");
				e.printStackTrace();
			}
			}
		}
	}

