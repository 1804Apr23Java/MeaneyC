package com.revature.producer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Consumer extends Thread {
	List<Integer> intList = Collections.synchronizedList(new ArrayList<Integer>());
	Random randomNumberGenerator = new Random();
	int randomNumber = randomNumberGenerator.nextInt(100) + 0;
	/*public Consumer(List<Integer> intList) {
		// TODO Auto-generated constructor stub
		this.intList = intList;
	}*/
	

	@Override
	public synchronized void run() {
		while(randomNumber != 0) {
			while(!intList.isEmpty()) {
				for (int i = 0; i<this.randomNumber;i++) {
					intList.remove(intList.get(i));
					this.randomNumber--;
					intList.notifyAll();
				}
			}
					try {
						this.intList.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("consumer block");
						e.printStackTrace();
					}
			}
		}
	}
	
