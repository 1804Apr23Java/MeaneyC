package util;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import daos.EmployeeDaoImpl;

public class RequestHelper {

	public int[] checkLogin(HttpServletRequest req, EmployeeDaoImpl edi) throws IOException {
		int arr[] = null;
		try {
			arr = edi.login(req.getParameter("username"), req.getParameter("password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("id is "+arr[0]+"\n isManager is "+ arr[1]);
		return arr;
	}

}
