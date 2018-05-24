package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Employee;
import daos.ManagerDaoImpl;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ManagerDaoImpl mdi = ManagerDaoImpl
				.getManagerDaoImpl(getServletContext().getResourceAsStream("connection.properties"));
		Employee emp = new Employee();
		emp.employeeFirst = request.getParameter("empFirst");
		emp.employeeLast = request.getParameter("empLast");
		emp.employeeEmail = request.getParameter("empEmail");
		String username = request.getParameter("empUsername");
		String password = request.getParameter("empPassword");
		int isManager = Integer.parseInt(request.getParameter("isManager"));
		boolean didCreate = false;
		try {
			didCreate = mdi.createEmployee(emp, username, password, isManager);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (didCreate) {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("Employee successfully created!");
			pw.println("</div>");
			pw.println("<a href=\"manager\">Go back</a>");
			pw.println("</body></html>");
		}
	}

}
