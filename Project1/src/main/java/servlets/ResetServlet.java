package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.EmployeeDaoImpl;

/**
 * Servlet implementation class ResetServlet
 */
public class ResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetServlet() {
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
		int resetCheck = 0;
		String username = request.getParameter("username2");
		try {
			resetCheck = edi.reset(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resetCheck != 0) {
			request.getSession().setAttribute("resetCheck", resetCheck);
			request.getSession().setAttribute("username", username);

			//request.setAttribute("resetCheck", resetCheck);
			//request.setAttribute("username", username);
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("New password successfully requested!");
			pw.println("</div>");
			pw.println("<form action=\"change\" method=\"post\">");
			pw.println("<label for=\"passwordReset\">Code:</label>");
			pw.println("<input type =\"text\" class=\"form-control\" id=\"passwordReset\" name=\"passwordReset\">");
			pw.println("<button type=\"submit\" class=\"btn btn-default\" id=\"change\" name=\"change\">Submit</button>");
			pw.println("</form>");
			pw.println("<a href=\"index\">Go back</a>");
			pw.println("</body></html>");
		}
		
	}

}
