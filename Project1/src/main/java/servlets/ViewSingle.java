package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Reimbursement;
import daos.ManagerDaoImpl;

/**
 * Servlet implementation class ViewSingle
 */
public class ViewSingle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewSingle() {
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
		ManagerDaoImpl mdi = ManagerDaoImpl.getManagerDaoImpl(getServletContext().getResourceAsStream("connection.properties"));
		List<Reimbursement> RemArr = new ArrayList<Reimbursement>();
		int empId = Integer.parseInt(request.getParameter("empId"));
		try {
			RemArr = mdi.viewEmployeeRequests(empId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Reimbursement r : RemArr) {
			response.getWriter().write(r.reimbursementId+":");
			response.getWriter().write(r.employeeId+":");
			response.getWriter().write(r.amount+":");
			response.getWriter().write(r.imageLocation+":");
			response.getWriter().write(r.state+":");
			response.getWriter().write(r.resolvingManager + ":");
		}	}

}
