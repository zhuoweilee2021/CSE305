package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Employee;
import model.Customer;

import java.util.stream.IntStream;

public class CustomerDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */
	
	/**
	 * @return ArrayList<Customer> object
	 */
	public List<Customer> getCustomers() {
		/*
		 * This method fetches one or more customers and returns it as an ArrayList
		 */
		
		List<Customer> customers = new ArrayList<Customer>();

		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */
		
		try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT P.*, U.PPP, U.Rating, U.DateOfLastAct, A.CardNumber, A.AcctNum, A.AcctCreationDate  FROM user U, person P, account A WHERE U.SSN = P.SSN AND P.SSN = A.OwnerSSN;");
			
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setUserID(rs.getString("SSN"));
				customer.setUserSSN(rs.getString("SSN"));
				customer.setEmail(rs.getString("Email"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setLastName(rs.getString("LastName"));
				customer.setAddress(rs.getString("Street"));
				customer.setCity(rs.getString("City"));
				customer.setState(rs.getString("State"));
				customer.setZipCode(rs.getInt("Zipcode"));
				customer.setTelephone(rs.getString("Telephone"));
				customer.setPpp(rs.getString("PPP"));
				customer.setRating(Integer.valueOf(rs.getString("Rating")));
				customer.setDateLastActive(rs.getString("DateOfLastAct"));
				customer.setAccNum(rs.getString("AcctNum"));
				customer.setAccCreateDate(rs.getString("AcctCreationDate"));
				customer.setCreditCard(rs.getString("CardNumber"));

				customers.add(customer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return customers;
	}

	public List<Customer> getCustomerMailingList() {

		/*
		 * This method fetches the all customer mailing details and returns it
		 * The students code to fetch data from the database will be written here
		 * Each customer record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		
		List<Customer> customers = new ArrayList<Customer>();
		
		
		try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT P.SSN, P.FirstName, P.LastName, P.Street, P.City, P.State, P.Zipcode, P.Email FROM person P, user U WHERE U.SSN = P.SSN");
			
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setUserID(rs.getString("SSN"));
				customer.setEmail(rs.getString("Email"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setLastName(rs.getString("LastName"));
				customer.setAddress(rs.getString("Street"));
				customer.setCity(rs.getString("City"));
				customer.setState(rs.getString("State"));
				customer.setZipCode(rs.getInt("Zipcode"));

				customers.add(customer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return customers;
	}

	public Customer getCustomer(String customerID) {

		/*
		 * This method fetches the customer details and returns it
		 * customerID, which is the Customer's ID who's details have to be fetched, is given as method parameter
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */
		Customer customer = new Customer();
		
		try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");
			PreparedStatement st= con.prepareStatement("SELECT P.SSN, P.FirstName, P.LastName, P.Street, P.City, P.State, P.Zipcode, P.Email, P.Telephone, U.Rating, A.CardNumber FROM person P, user U, account A WHERE P.SSN = U.SSN AND A.OwnerSSN = P.SSN AND P.SSN = ?;");
			st.setString(1, customerID);
			ResultSet rs=st.executeQuery();

			
			customer.setUserID(rs.getString("SSN"));
			customer.setEmail(rs.getString("Email"));
			customer.setFirstName(rs.getString("FirstName"));
			customer.setLastName(rs.getString("LastName"));
			customer.setAddress(rs.getString("Street"));
			customer.setCity(rs.getString("City"));
			customer.setState(rs.getString("State"));
			customer.setZipCode(rs.getInt("Zipcode"));
			customer.setTelephone(rs.getString("Telephone"));
			customer.setRating(Integer.valueOf(rs.getString("Rating")));
			customer.setCreditCard(rs.getString("CardNumber"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return customer;
	}
	
	public String deleteCustomer(String customerID) {

		/*
		 * This method deletes a customer returns "success" string on success, else returns "failure"
		 * The students code to delete the data from the database will be written here
		 * customerID, which is the Customer's ID who's details have to be deleted, is given as method parameter
		 */
		try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");
		    PreparedStatement st= con.prepareStatement("delete from person where ssn=?");
		    st.setString(1,customerID);
		    st.executeUpdate();
		    
		} catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/
		
	}


	public String getCustomerID(String username) {
		/*
		 * This method returns the Customer's ID based on the provided email address
		 * The students code to fetch data from the database will be written here
		 * username, which is the email address of the customer, who's ID has to be returned, is given as method parameter
		 * The Customer's ID is required to be returned as a String
		 */
		
		String id="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
			PreparedStatement st= con.prepareStatement("select ssn from person where email=?");
		    st.setString(1,username);
		    ResultSet rs = st.executeQuery();
			
			id=rs.getString("SSN");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}


	public String addCustomer(Customer customer) {

		/*
		 * All the values of the add customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the customer details and return "success" or "failure" based on result of the database insertion.
		 */
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
					
			PreparedStatement st2=con.prepareStatement("insert into person(`SSN`, `FirstName`, `LastName`,`Street`, `City`,`State`,`Zipcode`,`Email`,`Telephone`) values(?,?,?,?,?,?,?,?,?)");
			st2.setString(1, customer.getUserSSN());
			st2.setString(2, customer.getFirstName());
			st2.setString(3, customer.getLastName());
			st2.setString(4, customer.getAddress());
			st2.setString(5, customer.getCity());
			st2.setString(6, customer.getState());
			st2.setInt(7, customer.getZipCode());
			st2.setString(8, customer.getEmail());
			st2.setString(9, customer.getTelephone());
			st2.executeUpdate();
			
			
			PreparedStatement st=con.prepareStatement("insert into account(`OwnerSSN`, `CardNumber`, `AcctNum`, `AcctCreationDate`) values(?,?,?,?)");
			st.setString(1, customer.getUserSSN());
			st.setString(2, customer.getCreditCard());
			st.setString(3, customer.getAccNum());
			st.setString(4, customer.getAccCreateDate());
			st.executeUpdate();
			
			PreparedStatement st=con.prepareStatement("insert into user(`SSN`, `PPP`, `Rating`, `DateOfLastAct`) values(?,?,?,?)");
			st.setString(1, customer.getUserSSN());
			st.setString(2, customer.getPpp());
			st.setFloat(3, customer.getRating());
			st.setString(4, customer.getDateLastActive());
			st.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	public String editCustomer(Customer customer) {
		/*
		 * All the values of the edit customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}



	public List<Customer>  getMostActiveUser(){
		List<Customer> customers = new ArrayList<Customer>();

		/*Sample data begins*/
		for (int i = 0; i < 10; i++) {
			Customer customer = new Customer();
			customer.setUserID("111-11-1111");
			customer.setUserSSN("112-11-1111");
			customer.setAddress("123 Success Street");
			customer.setLastName("Lu");
			customer.setFirstName("Upendra Nath Chaurasia");
			customer.setCity("Stony Brook");
			customer.setState("NY");
			customer.setEmail("uppu_chaur@cs.sunysb.edu");
			customer.setZipCode(11790);
			customers.add(customer);
		}
		/*Sample data ends*/

		return customers;
	}

	public List<Customer> getDatedCustomers(String primary){

		List<Customer> customers = new ArrayList<Customer>();

		/*Sample data begins*/
		for (int i = 0; i < 10; i++) {
			Customer customer = new Customer();
			customer.setUserID("111-11-1111");
			customer.setAddress("123 Success Street");
			customer.setLastName("Lu");
			customer.setFirstName("Upendra Nath Chaurasia");
			customer.setCity("Stony Brook");
			customer.setState("NY");
			customer.setEmail("uppu_chaur@cs.sunysb.edu");
			customer.setZipCode(11790);
			customers.add(customer);
		}
		/*Sample data ends*/

		return customers;
	}

	public List<Customer> getHighestRatedCustomer(){
		List<Customer> customers = new ArrayList<Customer>();

		/*Sample data begins*/
		for (int i = 0; i < 10; i++) {
			Customer customer = new Customer();
			customer.setUserID("111-11-1111");
			customer.setUserSSN("112-11-1111");
			customer.setAddress("123 Success Street");
			customer.setLastName("Lu");
			customer.setFirstName("Upendra Nath Chaurasia");
			customer.setCity("Stony Brook");
			customer.setState("NY");
			customer.setEmail("uppu_chaur@cs.sunysb.edu");
			customer.setZipCode(11790);
			customers.add(customer);
		}
		/*Sample data ends*/

		return customers;
	}


	public List<Customer> getDateSuggestions(String userID) {
		/*
		 * This method fetches one or more customers and returns it as an ArrayList
		 */

		List<Customer> customers = new ArrayList<Customer>();

		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		/*Sample data begins*/
		for (int i = 0; i < 10; i++) {
			Customer customer = new Customer();
			customer.setUserID("111-11-1111");
			customer.setFirstName("long");
			customer.setLastName("Lu");
			customer.setAddress("123 Success Street12");
			customer.setCity("Stony Brook");
			customer.setState("NY");
			customer.setZipCode(11790);
			customer.setTelephone("5166328959");
			customer.setEmail("shiyong@cs.sunysb.edu");
			customer.setAccNum("12345");
			customer.setAccCreateDate("12-12-2020");
			customer.setCreditCard("1234567812345678");
			customer.setPpp("User");
			customer.setRating(1);
			customer.setDateLastActive("12-12-2020");
			customers.add(customer);
		}
		/*Sample data ends*/

		return customers;
	}


}
