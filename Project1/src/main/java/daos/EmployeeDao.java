package daos;

import java.sql.SQLException;
import java.util.List;

import classes.Employee;
import classes.Reimbursement;

public interface EmployeeDao {
	public int[] login(String username, String password) throws SQLException;
	public boolean submitReimbursementRequest(Reimbursement re) throws SQLException;
	public List<Reimbursement> viewReimbursements(Employee emp);
	public Employee viewInformation(Employee emp);
	public boolean updateInformation(Employee emp);
	
}
