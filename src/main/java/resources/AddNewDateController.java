package resources;

import dao.DateDao;
import dao.EmployeeDao;
import dao.LoginDao;
import model.Date;
import model.Employee;
import model.Login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AddCustomerController
 */
public class AddNewDateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewDateController() {
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

//		Get Data from request in this way
//		String email = request.getParameter("employeeEmail");


		Date date = new Date();
		date.setDateID("1");
		date.setUser1ID("111");
		date.setUser2ID("222");
		date.setDate("12-12-1222");
		date.setGeolocation("Himachal");
		date.setBookingfee("22");
		date.setCustRepresentative("Tom Alter");
		date.setComments("First date");
		date.setUser1Rating("1");
		date.setUser2Rating("2");

		DateDao dao = new DateDao();
		String result = dao.addDate(date);

		if(result.equals("success")) {
			Login login = new Login();
			login.setUsername("email");
			login.setPassword("password");
			login.setRole("customerRepresentative");
			LoginDao loginDao = new LoginDao();
			String loginResult = loginDao.addUser(login);
			if(loginResult.equals("success")) {
				response.sendRedirect("managerHome.jsp?status=addDateSuccess");
			}
			else {
				// Create addDate.jsp page to add this date to the database
				response.sendRedirect("addDate.jsp");
			}
		}
		else {
			response.sendRedirect("addDate.jsp");
		}

		
	}

}
