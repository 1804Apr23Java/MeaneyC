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
	public boolean manageRequests(int reqId, String approve, int managerId) throws SQLException; //approve and deny
	public boolean createEmployee(Employee emp, String username, String password, int isManager) throws SQLException;
}