package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import classes.Employee;
import daos.EmployeeDaoImpl;

/**
 * Servlet implementation class viewInformation
 */
public class viewInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EmployeeDaoImpl edi = EmployeeDaoImpl.getEmployeeDaoImpl(getServletContext().getResourceAsStream("connection.properties"));
		Employee emp = new Employee();
		HttpSession session = request.getSession();
		emp.employeeId = (Integer) session.getAttribute("employeeId");
		try {
			emp = edi.viewInformation(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectMapper om = new ObjectMapper();
		response.getWriter().write(om.writeValueAsString(emp));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EmployeeDaoImpl edi = EmployeeDaoImpl.getEmployeeDaoImpl(getServletContext().getResourceAsStream("connection.properties"));
		Employee emp = new Employee();
		HttpSession session = request.getSession();
		emp.employeeId = (Integer) session.getAttribute("employeeId");
		try {
			emp = edi.viewInformation(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectMapper om = new ObjectMapper();
		response.getWriter().write(om.writeValueAsString(emp));
	}

}
