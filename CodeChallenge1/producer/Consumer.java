package com.revature.producer;
import java.util.Random;

public class Consumer extends Thread {
	Random randomNumberGenerator = new Random();
	int randomNumber = randomNumberGenerator.nextInt(100) + 0;
	

	@Override
	public synchronized void run() {
		System.out.println("To remove - "+this.randomNumber+" items");
		while(this.randomNumber != 0) {
			if(!(Problem.intList.isEmpty())) {
				
				for (int i = 0; i<Problem.intList.size();i++) {
					System.out.println("Removed: " + Problem.intList.get(i));
					Problem.intList.remove(Problem.intList.get(i));
					Producer.currentThread().notify();
				}
				this.randomNumber--;
					
			}
			try {
				sleep(60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			  }
		}
	}
}
	
