package daos;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.Employee;
import classes.Reimbursement;
import util.ConnectionUtil;

public class ManagerDaoImpl implements ManagerDao {

	private Connection con;

	public ManagerDaoImpl() {
		con = null;
	}

	public static ManagerDaoImpl getManagerDaoImpl(InputStream f) {
		try {
			ManagerDaoImpl e = new ManagerDaoImpl();
			e.con = ConnectionUtil.getConnectionFromFile(f);
			e.con.setAutoCommit(true);
			return e;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Reimbursement> viewPendingRequests() throws SQLException {
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE STATE = 0";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<Reimbursement> re = new ArrayList<Reimbursement>();
		while (rs.next()) {
			Reimbursement rem = new Reimbursement();
			rem.employeeId = rs.getInt("EMPLOYEE_ID");
			rem.amount = rs.getInt("AMOUNT");
			rem.imageLocation = rs.getString("IMAGE_LOCATION");
			rem.reimbursementId = rs.getInt("REIMBURSEMENT_ID");
			rem.resolvingManager = rs.getInt("RESOLVING_MANAGER");
			re.add(rem);
			rs.next();
		}
		return re;
	}

	@Override
	public List<Reimbursement> viewResolvedRequests() throws SQLException {
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE STATE = 1 OR STATE = 2";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<Reimbursement> re = new ArrayList<Reimbursement>();
		while (rs.next()) {
			Reimbursement rem = new Reimbursement();
			rem.employeeId = rs.getInt("EMPLOYEE_ID");
			rem.amount = rs.getInt("AMOUNT");
			rem.imageLocation = rs.getString("IMAGE_LOCATION");
			rem.reimbursementId = rs.getInt("REIMBURSEMENT_ID");
			rem.resolvingManager = rs.getInt("RESOLVING_MANAGER");
			re.add(rem);
			rs.next();
		}
		return re;		
	}

	@Override
	public List<Employee> viewEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean manageRequests() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Reimbursement> viewEmployeeRequests(int empId) {
		// TODO Auto-generated method stub
		return null;
	}

}
