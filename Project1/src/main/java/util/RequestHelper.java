package util;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import daos.EmployeeDaoImpl;

public class RequestHelper {

	public String checkLogin(HttpServletRequest req, EmployeeDaoImpl edi) throws IOException {
		int[] arr = null;
		HttpSession session = req.getSession();
		try {
			arr = edi.login(req.getParameter("username"), req.getParameter("password"));
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		if (arr != null && arr[1] == 1) {
			session.setAttribute("managerId", arr[0]);
			session.setAttribute("isManager", 1);
			return "manager";
		} else if (arr != null && arr[1] == 0){
			session.setAttribute("employeeId", arr[0]);
			session.setAttribute("isManager", 0);
			return "employee";
		}
		else
			return "error";
	}

}
