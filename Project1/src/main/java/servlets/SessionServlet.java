package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet
 */
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session!=null) {
			response.setContentType("application/json");
			if(session.getAttribute("employeeId") != null)
				response.getWriter().write("{\"employeeId\":\""+session.getAttribute("employeeId")+"\"}");
			else
				response.getWriter().write("{\"managerId\":\""+session.getAttribute("managerId")+"\"}");

		} else {
			response.setContentType("application/json");
			response.getWriter().write("{\"session\":null}");
		}
	}


}
