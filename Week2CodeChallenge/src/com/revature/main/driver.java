package com.revature.main;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;

public class driver {
	public static void main(String[] args) {
		
		EmployeeDao ed = new EmployeeDaoImpl();
		
		ed.printAvgSalary();
		ed.spRaiseProcedure();
		ed.printAvgSalary();		


	}
}
