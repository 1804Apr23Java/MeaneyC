package daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import classes.Employee;
import classes.Reimbursement;
import util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao{

	@Override
	public boolean submitReimbursementRequest(Employee emp) {
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "INSERT INTO REIMBURSEMENTS(EMPLOYEE_ID, IMAGE_LOCATION, STATE, RESOLVING_MANAGER) VALUES(?, ?, ?, ?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, 3);
			statement.setString(2, "https://imgur.com/gallery/N98BYni".toUpperCase());
			statement.setInt(3, 1);
			statement.setInt(4, 1);
			ResultSet rs = statement.executeQuery();
			while (rs.next()){
				con.close();
				return true;
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return false;
		}

	@Override
	public List<Reimbursement> viewReimbursements(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee viewInformation(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateInformation(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

}
