package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResetPassword
 */
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResetPassword() {
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
		int resetCheck = (Integer) request.getSession().getAttribute("resetCheck");
		int userReset = Integer.parseInt(request.getParameter("passwordReset"));
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		if (resetCheck == userReset) {
			pw.println("</div>");
			pw.println("<form action=\"password\" method=\"post\">");
			pw.println("<label for=\"newPassword\">Password:</label>");
			pw.println("<input type =\"password\" class=\"form-control\" id=\"newPassword\" name=\"newPassword\">");
			pw.println("<button type=\"submit\" class=\"btn btn-default\" id=\"password\" name=\"password\">Submit</button>");
			pw.println("</form>");
			pw.println("<a href=\"index\">Go back</a>");
			pw.println("</body></html>");
		} else {
			pw.println("</div>");
			pw.println("<a href=\"index\">Invalid token; Go back</a>");
			//pw.println("</body></html>");
		}
	}
}
