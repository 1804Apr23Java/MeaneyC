package daos;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import classes.Employee;
import classes.Reimbursement;
import util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	private Connection con;

	public EmployeeDaoImpl() {
		con = null;
	}

	public static EmployeeDaoImpl getEmployeeDaoImpl(InputStream f) {
		try {
			EmployeeDaoImpl e = new EmployeeDaoImpl();
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
	public boolean submitReimbursementRequest(Reimbursement re) throws SQLException {
		// try(Connection con = ConnectionUtil.getConnectionFromFile(is)){
		String sql = "INSERT INTO REIMBURSEMENTS(EMPLOYEE_ID, IMAGE_LOCATION, AMOUNT, STATE) VALUES(?, ?, ?, ?)";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, re.employeeId);
		statement.setString(2, re.imageLocation);
		statement.setInt(3, re.amount);
		statement.setInt(4, 0);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			con.close();
			return true;
		}
		con.close();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }
		return false;
	}

	@Override
	public List<Reimbursement> viewReimbursements(Employee emp) throws SQLException {
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE EMPLOYEE_ID = ? AND STATE = 1 OR STATE = 2";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, emp.employeeId);
		ResultSet rs = pstmt.executeQuery();
		List<Reimbursement> re = new ArrayList<Reimbursement>();
		while (rs.next()) {
			Reimbursement rem = new Reimbursement();
			rem.reimbursementId = rs.getInt("REIMBURSEMENT_ID");
			rem.amount = rs.getInt("AMOUNT");
			rem.imageLocation = rs.getString("IMAGE_LOCATION");
			rem.resolvingManager = rs.getInt("RESOLVING_MANAGER");
			re.add(rem);
		}
		return re;
	}

	@Override
	public Employee viewInformation(Employee emp) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, emp.employeeId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			emp.employeeFirst = rs.getString("EMPLOYEE_FIRST");
			emp.employeeLast = rs.getString("EMPLOYEE_LAST");
			emp.employeeEmail = rs.getString("EMPLOYEE_EMAIL");
		}
		return emp;
	}

	@Override
	public boolean updateInformation(Employee emp) throws SQLException {
		String sql = "Update employee set employee_first = ?, employee_last = ?, "
				+ "employee_email = ? WHERE EMPLOYEE_ID = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, emp.employeeFirst);
		pstmt.setString(2, emp.employeeLast);
		pstmt.setString(3, emp.employeeEmail);
		pstmt.setInt(4, emp.employeeId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
			return true;
		else
			return false;
	}

	@Override
	public int[] login(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT LOGIN_ID, ISMANAGER FROM LOGIN WHERE USERNAME = ? AND PASSWORD = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		// System.out.println("did the select work: "+rs.next());
		int loginId;
		int isManager;
		if (rs.next()) {
			loginId = rs.getInt("LOGIN_ID");
			isManager = rs.getInt("ISMANAGER");
			int id = 0;
			if (isManager == 1) {
				String sql2 = "SELECT MANAGER_ID FROM MANAGER WHERE LOGIN_ID = ?";
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, loginId);
				ResultSet rs2 = pstmt2.executeQuery();
				if (rs2.next()) {
					id = rs2.getInt("MANAGER_ID");
				}
			} else {
				System.out.println("loginId = " + loginId);
				String sql2 = "SELECT EMPLOYEE_ID FROM EMPLOYEE WHERE LOGIN_ID = ?";
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, loginId);
				ResultSet rs2 = pstmt2.executeQuery();
				if (rs2.next()) {
					id = rs2.getInt("EMPLOYEE_ID");
				}
			}
			int[] arr = new int[2];
			arr[0] = id;
			arr[1] = isManager;
			return arr;
		}
		return null;
	}

	@Override
	public List<Reimbursement> viewPending(Employee emp) throws SQLException {
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE EMPLOYEE_ID = ? AND STATE = 0";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, emp.employeeId);
		ResultSet rs = pstmt.executeQuery();
		List<Reimbursement> re = new ArrayList<Reimbursement>();
		while (rs.next()) {
			Reimbursement rem = new Reimbursement();
			rem.reimbursementId = rs.getInt("REIMBURSEMENT_ID");
			rem.amount = rs.getInt("AMOUNT");
			rem.imageLocation = rs.getString("IMAGE_LOCATION");
			rem.resolvingManager = rs.getInt("RESOLVING_MANAGER");
			re.add(rem);
		}
		return re;
	}

	@Override
	public boolean reset(String username) throws SQLException {
		String sql = "INSERT INTO RESETS(USERNAME) VALUES(?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

}
