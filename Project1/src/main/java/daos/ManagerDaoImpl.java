package daos;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

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
			rem.state = rs.getInt("STATE");
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
			rem.state = rs.getInt("STATE");
			rem.resolvingManager = rs.getInt("RESOLVING_MANAGER");
			re.add(rem);
			rs.next();
		}
		return re;
	}

	@Override
	public List<Employee> viewEmployees() throws SQLException {
		String sql = "SELECT * FROM EMPLOYEE";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<Employee> empList = new ArrayList<Employee>();
		while (rs.next()) {
			Employee emp = new Employee();
			emp.employeeEmail = rs.getString("EMPLOYEE_EMAIL");
			emp.employeeFirst = rs.getString("EMPLOYEE_FIRST");
			emp.employeeLast = rs.getString("EMPLOYEE_LAST");
			emp.employeeId = rs.getInt("EMPLOYEE_ID");
			empList.add(emp);
		}
		return empList;
	}

	@Override
	public boolean manageRequests(int reqId, String approve, int managerId) throws SQLException {
		String sql2 = "SELECT MANAGER_EMAIL FROM MANAGER WHERE MANAGER_ID = ?";
		PreparedStatement pstmt2 = con.prepareStatement(sql2);
		pstmt2.setInt(1, managerId);
		ResultSet rs2 = pstmt2.executeQuery();
		String managerEmail = null;
		if (rs2.next()) {
			managerEmail = rs2.getString("MANAGER_EMAIL");
		}
		String sql3 = "SELECT EMPLOYEE_EMAIL FROM EMPLOYEE WHERE EMPLOYEE_ID IN (SELECT EMPLOYEE_ID FROM REIMBURSEMENTS WHERE REIMBURSEMENT_ID = ?"
				+ ")";
		PreparedStatement pstmt3 = con.prepareStatement(sql3);
		pstmt3.setInt(1, reqId);
		ResultSet rs3 = pstmt3.executeQuery();
		String employeeEmail = null;
		if (rs3.next()) {
			employeeEmail = rs3.getString("EMPLOYEE_EMAIL");
		}
		String sql = "UPDATE REIMBURSEMENTS SET STATE = ?" + ", RESOLVING_MANAGER = ? WHERE REIMBURSEMENT_ID = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		System.out.println("approve: " + approve + "\nmanagerId: " + managerId + "\nrequestId: " + reqId);
		if (approve.equals("A"))
			pstmt.setInt(1, 1);
		else if (approve.equals("D")) {
			pstmt.setInt(1, 2);
		} else
			return false;
		pstmt.setInt(2, managerId);
		pstmt.setInt(3, reqId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", "smtp.gmail.com");
			properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.setProperty("mail.smtp.socketFactory.fallback", "false");
			properties.setProperty("mail.smtp.port", "465");
			properties.setProperty("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.debug", "true");
			properties.put("mail.store.protocol", "pop3");
			properties.put("mail.transport.protocol", "smtp");
			try {
				Session session = Session.getDefaultInstance(properties, new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("collinmeaney375@gmail.com", "Windwaker1");
					}
				});
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress("collinmeaney375@gmail.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(employeeEmail, false));
				message.setSubject("Reimbursement Resolved!");
				if (approve.equals("A"))
					message.setText("Your reimbursement: " + reqId + " was approved!");
				else
					message.setText("Your reimbursement: " + reqId + " was denied!");
				Transport.send(message);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else
			return false;
	}

	@Override
	public List<Reimbursement> viewEmployeeRequests(int empId) throws SQLException {
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE EMPLOYEE_ID = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, empId);
		ResultSet rs = pstmt.executeQuery();
		List<Reimbursement> re = new ArrayList<Reimbursement>();
		while (rs.next()) {
			Reimbursement rem = new Reimbursement();
			rem.employeeId = rs.getInt("EMPLOYEE_ID");
			rem.amount = rs.getInt("AMOUNT");
			rem.imageLocation = rs.getString("IMAGE_LOCATION");
			rem.reimbursementId = rs.getInt("REIMBURSEMENT_ID");
			rem.state = rs.getInt("STATE");
			rem.resolvingManager = rs.getInt("RESOLVING_MANAGER");
			re.add(rem);
			rs.next();
		}
		return re;
	}

}
