package daos;

import java.util.List;

import classes.Employee;
import classes.Reimbursement;

public interface ManagerDao {
	
	public List<Reimbursement> viewPendingRequests();
	public List<Reimbursement> viewResolvedRequests();
	public List<Employee> viewEmployees();
	public boolean manageRequests(); //approve and deny
	
}