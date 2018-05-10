package com.revature.hello;

//classes should be pascal case (OneTwoThree)
public class Orange extends Fruit{
	
	public Orange() {
		super(); //calling Fruit's noargs constructor
	}
	
	public Orange(String variety) {
		this();
		this.variety = variety;
	}
	
	private String variety;

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}
	

	@Override
	public String toString() {
		return "Orange [variety = " + variety + "]";
	}
	//overriding: providing a new implementation of an instance method in a subclass
	//need to have the same method signature as superclass' method
	
	//code blocks
	
	//instance:
	{
		System.out.println("instance code block and will run when Orange is instantiated");
		//runs before constructor
	}
	
	//static:
	static {
		System.out.println("static code block and will run when Orange passes through JVM");
	}
}
