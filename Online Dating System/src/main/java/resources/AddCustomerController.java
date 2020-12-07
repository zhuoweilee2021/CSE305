package resources;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dao.EmployeeDao;
import dao.LoginDao;
import model.Customer;
import model.Employee;
import model.Login;

/**
 * Servlet implementation class AddCustomerController
 */
public class AddCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCustomerController() {
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

//		Modify and add fields as per requirement

		String email = request.getParameter("customerEmail");
		String password = request.getParameter("customerPassword");
		String firstName = request.getParameter("customerFirstName");
		String lastName = request.getParameter("customerLastName");
		String address = request.getParameter("customerAddress");
		String city = request.getParameter("customerCity");
		String state= request.getParameter("customerState");
		int zipcode = Integer.parseInt(request.getParameter("customerZipcode"));
		String telephone = request.getParameter("customerTelephone");
		String ssn = request.getParameter("customerSSN");
		String creditCard = request.getParameter("customerCreditCard");
		int rating = Integer.parseInt(request.getParameter("customerRating"));
		String accountNum = request.getParameter("customerAcc");
		String creationDate = request.getParameter("customerLastActive");
		String lastActiveDate = request.getParameter("customerAccCreate");
		String ppp= request.getParameter("ppp");
		
		Customer customer = new Customer();
		customer.setEmail(email);
		customer.setUserSSN(ssn);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setState(state);
		customer.setZipCode(zipcode);
		customer.setTelephone(telephone);
		customer.setUserID(ssn);
		customer.setCreditCard(creditCard);
		customer.setRating(rating);
		customer.setAccNum(accountNum);
		customer.setDateLastActive(lastActiveDate);
		customer.setAccCreateDate(creationDate);
		customer.setPpp(ppp);
		
		
		CustomerDao customerDao = new CustomerDao();
		String result = customerDao.addCustomer(customer);
		
		if(result.equals("success")) {
			Login login = new Login();
			login.setUsername(email);
			login.setPassword(password);
			login.setRole("customer");
			LoginDao loginDao = new LoginDao();
			String loginResult = loginDao.addUser(login);
			if(loginResult.equals("success")) {
				response.sendRedirect("customerRepresentativeHome.jsp?status=addCustomerSuccess");
			}
			else {
				response.sendRedirect("addCustomer.jsp?status=error");
			}
		}
		else {
			response.sendRedirect("addCustomer.jsp?status=error");
		}

	}

}
