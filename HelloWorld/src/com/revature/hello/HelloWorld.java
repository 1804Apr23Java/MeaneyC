package com.revature.hello;

//classes should be pascal case (OneTwoThree)
public class HelloWorld {
	
	//main method
	//methods should be camel case (oneTwoThree)
	public static void main(String[] args) {
		System.out.println("hello world!");
		
		Orange o = new Orange();
		
		o.setColor("purple");
		
		System.out.println("Orange o is "+o.getColor());
		
		o.setVariety("Valencia");
		
		System.out.println("Orange o has variety: "+o.getVariety());
		
		//what about Object class
		Object obj = new Object();
		System.out.println(obj.toString()); //returns the fully qualified classname + address in memory
		System.out.println(o.toString());
		
		//equals and hashcode
		
		Fruit f1 = new Fruit("red");
		Fruit f2 = new Fruit("green");
		Fruit f3 = new Fruit("green");
		System.out.println(f1.hashCode());
		System.out.println(f2.hashCode());
		System.out.println(f3.hashCode());
		System.out.println(f1.equals(f2));
		System.out.println(f2.equals(f3));
		
		Orange o2 = new Orange();
	}
	
	
}
