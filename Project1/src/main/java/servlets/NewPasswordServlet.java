package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.EmployeeDaoImpl;

/**
 * Servlet implementation class NewPasswordServlet
 */
public class NewPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPasswordServlet() {
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
		EmployeeDaoImpl edi = EmployeeDaoImpl
				.getEmployeeDaoImpl(getServletContext().getResourceAsStream("connection.properties"));
		String username = (String) request.getSession().getAttribute("username");
		String password = request.getParameter("newPassword");
		boolean didSetPassword = false;
		try {
			didSetPassword = edi.setPassword(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(didSetPassword) {
			PrintWriter pw = response.getWriter();
			response.setContentType("text/html");
			pw.println("New password successfully created!");
			pw.println("<a href=\"index\">Go back</a>");
			pw.println("</body></html>");
		}
		
	}

}
