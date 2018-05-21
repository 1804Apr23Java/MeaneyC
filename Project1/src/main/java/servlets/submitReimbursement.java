package servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Employee;
import daos.EmployeeDao;
import daos.EmployeeDaoImpl;

/**
 * Servlet implementation class submitReimbursement
 */
public class submitReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public submitReimbursement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		Employee emp = new Employee();
		emp.employeeId = 5;
		emp.imageLocation = "https://imgur.com/gallery/N98BYni".toUpperCase();
		emp.state = 2;
		emp.resolvingManager = 2;
		boolean isTrue = edi.submitReimbursementRequest(emp);
		if(isTrue) {
			response.getWriter().write("true");
		} else {
			response.getWriter().write("false");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
