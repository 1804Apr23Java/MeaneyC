package com.revature.producer;
import java.util.Random;

public class Producer extends Thread {
	Random randomNumberGenerator = new Random();
	int randomNumber;

	@Override
	public synchronized void run() {
		while(true) {
			while(!(Problem.intList.size() == 10)) {
				System.out.println("Added: " + this.randomNumber);
				randomNumber = randomNumberGenerator.nextInt(100) + 0;
				Problem.intList.add(this.randomNumber);
				}
			try {
				sleep(60);
			} catch (InterruptedException e) {
				System.out.println("producer exception");
				e.printStackTrace();
			  }
			
		}
	}
}

