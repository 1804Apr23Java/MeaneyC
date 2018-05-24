package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Employee;
import daos.EmployeeDaoImpl;

/**
 * Servlet implementation class UpdateInformation
 */
public class UpdateInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateInformation() {
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
		EmployeeDaoImpl edi = EmployeeDaoImpl
				.getEmployeeDaoImpl(getServletContext().getResourceAsStream("connection.properties"));
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		String email = request.getParameter("email");
		HttpSession session = request.getSession();
		int empId = (Integer) session.getAttribute("employeeId");
		Employee emp = new Employee();
		emp.employeeId = empId;
		emp.employeeFirst = first;
		emp.employeeLast = last;
		emp.employeeEmail = email;
		boolean didUpdate = false;
		try {
			didUpdate = edi.updateInformation(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (didUpdate) {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("Employee successfully updated!");
			pw.println("</div>");
			pw.println("<a href=\"employee\">Go back</a>");
			pw.println("</body></html>");
		}
	}

}
