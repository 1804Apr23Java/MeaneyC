package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.ManagerDaoImpl;

/**
 * Servlet implementation class AlterServlet
 */
public class AlterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlterServlet() {
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
		int reqId = Integer.parseInt(request.getParameter("requestId"));
		String approve = request.getParameter("approve");
		HttpSession session = request.getSession();
		int managerId = (Integer) session.getAttribute("managerId");
		boolean didAlter = false;
		try {
			didAlter = mdi.manageRequests(reqId, approve, managerId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (didAlter) {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("Reimbursement successfully updated!");
			pw.println("</div>");
			pw.println("<a href=\"manager\">Go back</a>");
			pw.println("</body></html>");
		}
	}

}
