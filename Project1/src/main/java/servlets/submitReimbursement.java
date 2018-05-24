package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeeDaoImpl edi = EmployeeDaoImpl
				.getEmployeeDaoImpl(getServletContext().getResourceAsStream("connection.properties"));
		ObjectMapper om = new ObjectMapper();
		System.out.println(request.getAttributeNames());
	}*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// EmployeeDaoImpl edi = new EmployeeDaoImpl();
		EmployeeDaoImpl edi = EmployeeDaoImpl
				.getEmployeeDaoImpl(getServletContext().getResourceAsStream("connection.properties"));
		// Reimbursement re = new Reimbursement();
		// re.employeeId = 5;
		// re.imageLocation = "https://imgur.com/gallery/N98BYni".toUpperCase();
		// re.state = 2;
		// re.resolvingManager = 2;
		ObjectMapper om = new ObjectMapper();

		String json = request.getParameter("cmd");
		// String json = "{ \"employeeId\" : 1, \"imageLocation\" : \"www.fun.com\",
		// \"state\" : 1, \"resolvingManager\" : 1 }";
		Reimbursement re = om.readValue(json, Reimbursement.class);
		boolean isTrue = false;
		try {
			isTrue = edi.submitReimbursementRequest(re);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("reimbursement successfully submitted");
		pw.println("</div>");
		pw.println("<a href=\"employee\">Go back</a>");
		pw.println("</body></html>");
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
		Reimbursement re = new Reimbursement();
		HttpSession session = request.getSession();
		// re.employeeId = 5;
		// re.imageLocation = "https://imgur.com/gallery/N98BYni".toUpperCase();
		// re.state = 2;
		// re.resolvingManager = 2;
		// ObjectMapper om = new ObjectMapper();
		// om.readValue(request.getParameter("test"), Reimbursement.class);
		int empId = (Integer) session.getAttribute("employeeId");
		int json = Integer.parseInt(request.getParameter("amount"));
		String json2 = request.getParameter("imageLocation");
		// int json3 = Integer.parseInt(request.getParameter("state"));
		// int json4 = Integer.parseInt(request.getParameter("resolvingManager"));
		// String json = request.getParameter("employeeId").toString();
		/*
		 * JSONObject json = null; try { json = new
		 * JSONObject(request.getInputStream().toString()); } catch (JSONException e1) {
		 * // TODO Auto-generated catch block e1.printStackTrace(); }
		 */
		// byte arr[] = null;
		// System.out.println(json.names());
		// ServletInputStream sis = request.getInputStream();
		// sis.readLine(arr, 0, sis.available());
		// String json = new String(arr);
		// System.out.println("Json 1: "+json+ " Json2: "+json2+" json3: "+json3+"
		// json4:"+json4);
		// System.out.println();
		// System.out.println(json);
		// String json = "{ \"employeeId\" : 1, \"imageLocation\" : \"www.fun.com\",
		// \"state\" : 1, \"resolvingManager\" : 1 }";
		// Reimbursement re = om.valueToTree(re);(json, Reimbursement.class);
		re.employeeId = empId;
		re.amount = json;
		re.imageLocation = json2;
		re.state = 0;
		// re.resolvingManager = null;
		boolean isTrue = false;
		// System.out.println("HOW?");
		try {
			// System.out.println("here?");
			isTrue = edi.submitReimbursementRequest(re);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("reimbursement successfully submitted");
		pw.println("</div>");
		pw.println("<a href=\"employee\">Go back</a>");
		pw.println("</body></html>");
	}
}
