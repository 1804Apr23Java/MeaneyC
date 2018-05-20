package daos;

import java.util.List;

import classes.Employee;
import classes.Reimbursement;

public interface EmployeeDao {
	public boolean submitReimbursementRequest(Employee emp);
	public List<Reimbursement> viewReimbursements(Employee emp);
	public Employee viewInformation(Employee emp);
	public boolean updateInformation(Employee emp);
	
}
