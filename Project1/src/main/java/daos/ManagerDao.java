package daos;

import java.sql.SQLException;
import java.util.List;

import classes.Employee;
import classes.Reimbursement;

public interface ManagerDao {
	
	public List<Reimbursement> viewPendingRequests() throws SQLException;
	public List<Reimbursement> viewResolvedRequests() throws SQLException;
	public List<Employee> viewEmployees() throws SQLException;
	public List<Reimbursement> viewEmployeeRequests(int empId) throws SQLException;
	public boolean manageRequests() throws SQLException; //approve and deny
	
}