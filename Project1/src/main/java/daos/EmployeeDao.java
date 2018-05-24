package daos;

import java.sql.SQLException;
import java.util.List;

import classes.Employee;
import classes.Reimbursement;

public interface EmployeeDao {
	public int[] login(String username, String password) throws SQLException;
	public boolean submitReimbursementRequest(Reimbursement re) throws SQLException;
	public List<Reimbursement> viewReimbursements(Employee emp) throws SQLException;
	public List<Reimbursement> viewPending(Employee emp) throws SQLException;
	public Employee viewInformation(Employee emp) throws SQLException;
	public boolean updateInformation(Employee emp);
	
}
