package daos;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.Employee;

public class daoTests {

	@Test
	public void test() {
		EmployeeDaoImpl btdi = new EmployeeDaoImpl();
		Employee emp = new Employee();
		emp.employeeId = 5;
		emp.imageLocation = "https://imgur.com/gallery/N98BYni".toUpperCase();
		emp.state = 2;
		emp.resolvingManager = 2;
		boolean itWorked = btdi.submitReimbursementRequest(emp);
		assertTrue(itWorked);
	}

}
