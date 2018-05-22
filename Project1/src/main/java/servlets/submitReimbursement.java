package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.revature.project1.dao.EmployeeDao;

import classes.Reimbursement;
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
		//EmployeeDaoImpl edi = new EmployeeDaoImpl();
		EmployeeDaoImpl edi = EmployeeDaoImpl.getEmployeeDaoImpl(getServletContext().getResourceAsStream("connection.properties"));
		Reimbursement re = new Reimbursement();
		re.employeeId = 5;
		re.imageLocation = "https://imgur.com/gallery/N98BYni".toUpperCase();
		re.state = 2;
		re.resolvingManager = 2;
		boolean isTrue = false;
		try {
			isTrue = edi.submitReimbursementRequest(re);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
