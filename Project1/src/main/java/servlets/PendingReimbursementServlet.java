package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Employee;
import classes.Reimbursement;
import daos.EmployeeDaoImpl;

/**
 * Servlet implementation class PendingReimbursementServlet
 */
public class PendingReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PendingReimbursementServlet() {
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
		EmployeeDaoImpl edi = EmployeeDaoImpl.getEmployeeDaoImpl(getServletContext().getResourceAsStream("connection.properties"));
		Employee emp = new Employee();
		HttpSession session = request.getSession();
		emp.employeeId = (Integer) session.getAttribute("employeeId");
		List<Reimbursement> RemArr = new ArrayList<Reimbursement>();
		try {
			RemArr = edi.viewPending(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Reimbursement r : RemArr) {
			response.getWriter().write(r.reimbursementId+":");
			response.getWriter().write(r.amount+":");
			response.getWriter().write(r.imageLocation+":");
			response.getWriter().write(r.resolvingManager+":");
		}
	}

}
