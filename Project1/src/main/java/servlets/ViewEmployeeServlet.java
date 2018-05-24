package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Employee;
import classes.Reimbursement;
import daos.ManagerDaoImpl;

/**
 * Servlet implementation class ViewEmployeeServlet
 */
public class ViewEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ManagerDaoImpl mdi = ManagerDaoImpl
				.getManagerDaoImpl(getServletContext().getResourceAsStream("connection.properties"));
		List<Employee> EmpArr = new ArrayList<Employee>();
		try {
			EmpArr = mdi.viewEmployees();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Employee e : EmpArr) {
			response.getWriter().write(e.employeeId + ":");
			response.getWriter().write(e.employeeFirst + ":");
			response.getWriter().write(e.employeeLast + ":");
			response.getWriter().write(e.employeeEmail + ":");
		}	}

}
