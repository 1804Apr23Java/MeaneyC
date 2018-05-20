package daos;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.Employee;

public class daoTests {

	@Test
	public void test() {
		EmployeeDaoImpl btdi = new EmployeeDaoImpl();
		Employee emp = new Employee();
		boolean itWorked = btdi.submitReimbursementRequest(emp);
		assertTrue(itWorked);
	}

}
