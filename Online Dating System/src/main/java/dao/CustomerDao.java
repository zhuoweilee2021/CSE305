package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
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
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","password");
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
		
		
		/*Sample data begins*/
//		for (int i = 0; i < 10; i++) {
//			Customer customer = new Customer();
//			customer.setUserID("111-11-1111");
//			customer.setUserSSN("112-11-1111");
//			customer.setFirstName("Shiyong");
//			customer.setLastName("Lu");
//			customer.setAddress("123 Success Street12");
//			customer.setCity("Stony Brook");
//			customer.setState("NY");
//			customer.setZipCode(11790);
//			customer.setTelephone("5166328959");
//			customer.setEmail("shiyong@cs.sunysb.edu");
//			customer.setAccNum("12345");
//			customer.setAccCreateDate("12-12-2020");
//			customer.setCreditCard("1234567812345678");
//			customer.setPpp("User");
//			customer.setRating(1);
//			customer.setDateLastActive("12-12-2020");
//			customers.add(customer);
//		}
//		/*Sample data ends*/
		
//		return customers;
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
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","password");
		    System.out.println("Connected database successfully...");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT P.SSN, P.FirstName, P.LastName, P.Street, P.City, P.State, P.Zipcode, P.Email, P.Telephone, U.DateOfLastAct, U.PPP, MAX(A.CardNumber) as CardNumber "
					+ "FROM person P, user U, account A "
					+ "WHERE U.SSN = P.SSN AND P.SSN = A.OwnerSSN "
					+ "GROUP BY P.SSN, P.FirstName, P.LastName, P.Street, P.City, P.State, P.Zipcode, P.Email, P.Telephone");
			
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
				customer.setTelephone(rs.getString("Telephone"));
				customer.setCreditCard(rs.getString("CardNumber"));
				customer.setDateLastActive(String.valueOf(rs.getDate("DateOfLastAct")));
				customer.setPpp(rs.getString("PPP"));

				customers.add(customer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*Sample data begins*/
//		for (int i = 0; i < 10; i++) {
//			Customer customer = new Customer();
//			customer.setUserID("111-11-1111");
//			customer.setAddress("123 Success Street");
//			customer.setLastName("Lu");
//			customer.setFirstName("Shiyong");
//			customer.setCity("Stony Brook");
//			customer.setState("NY");
//			customer.setEmail("shiyong@cs.sunysb.edu");
//			customer.setZipCode(11790);
//			customers.add(customer);			
//		}
		/*Sample data ends*/
		
		return customers;
	}

	public Customer getCustomer(String customerID, String customerAccNum) {

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
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","password");
		    System.out.println("Connected database successfully...");
			PreparedStatement st= con.prepareStatement("SELECT P.SSN, P.FirstName, P.LastName, P.Street, P.City, P.State, P.Zipcode, P.Email, P.Telephone, U.Rating, A.CardNumber, A.AcctCreationDate, A.AcctNum, U.DateOfLastAct "
					+ "FROM person P, user U, account A WHERE P.SSN = U.SSN AND A.OwnerSSN = P.SSN AND P.SSN = ? AND A.AcctNum = ?;");
			st.setString(1, customerID);
			st.setString(2, customerAccNum);
			ResultSet rs=st.executeQuery();

			while(rs.next()) {
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
				customer.setRating(Integer.valueOf(rs.getString("Rating")));
				customer.setCreditCard(rs.getString("CardNumber"));
				customer.setAccCreateDate(String.valueOf(rs.getDate("AcctCreationDate")));
				customer.setDateLastActive(String.valueOf(rs.getDate("DateOfLastAct")));
				customer.setAccNum(rs.getString("AcctNum"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*Sample data begins*/
//		Customer customer = new Customer();
//		customer.setUserID("111-11-1111");
//		customer.setAddress("123 Success Street");
//		customer.setLastName("Lu");
//		customer.setFirstName("Shiyong");
//		customer.setCity("Stony Brook");
//		customer.setState("NY");
//		customer.setEmail("shiyong@cs.sunysb.edu");
//		customer.setZipCode(11790);
//		customer.setTelephone("5166328959");
//		customer.setCreditCard("1234567812345678");
//		customer.setRating(1);
		/*Sample data ends*/
		
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
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","password");
		    System.out.println("Connected database successfully...");
		    PreparedStatement st= con.prepareStatement("delete from person where ssn=?");
		    st.setString(1,customerID);
		    st.executeUpdate();
		    
		    PreparedStatement st2= con.prepareStatement("delete from user where ssn=?");
		    st2.setString(1,customerID);
		    st2.executeUpdate();
		    
		    PreparedStatement st3= con.prepareStatement("delete from account where ownerssn=?");
		    st3.setString(1,customerID);
		    st3.executeUpdate();
		    
		} catch(Exception e) {
			e.printStackTrace();
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
		
		String id = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","password");
			PreparedStatement st= con.prepareStatement("select ssn from person where email=?");
		    st.setString(1,username);
		    ResultSet rs = st.executeQuery();
			
		    while (rs.next()) id=rs.getString("SSN");
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
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","password");
					
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
			
			PreparedStatement st3=con.prepareStatement("insert into user(`SSN`, `PPP`, `Rating`, `DateOfLastAct`) values(?,?,?,?)");
			st3.setString(1, customer.getUserSSN());
			st3.setString(2, customer.getPpp());
			st3.setInt(3, customer.getRating());
			st3.setDate(4, Date.valueOf(customer.getDateLastActive()));	
			st3.executeUpdate();
			
			PreparedStatement st=con.prepareStatement("insert into account(`OwnerSSN`, `CardNumber`, `AcctNum`, `AcctCreationDate`) values(?,?,?,?)");
			st.setString(1, customer.getUserSSN());
			st.setString(2, customer.getCreditCard());
			st.setString(3, customer.getAccNum());
			st.setDate(4, Date.valueOf(customer.getAccCreateDate()));
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
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","password");

			System.out.println("edting customer...");
			PreparedStatement st2=con.prepareStatement("update person set FirstName=?, LastName=?,Street=?, City=?,State=?,Zipcode=?,Email=?,Telephone=? where SSN=?");
//			st2.setString(1, customer.getUserSSN());
			st2.setString(1, customer.getFirstName());
			st2.setString(2, customer.getLastName());
			st2.setString(3, customer.getAddress());
			st2.setString(4, customer.getCity());
			st2.setString(5, customer.getState());
			st2.setInt(6, customer.getZipCode());
			st2.setString(7, customer.getEmail());
			st2.setString(8, customer.getTelephone());
			st2.setString(9, customer.getUserID());
			st2.executeUpdate();
			System.out.println("st2 finished..." + customer.getPpp() + customer.getDateLastActive());
			
			PreparedStatement st3=con.prepareStatement("update user set PPP=?, Rating=?, DateOfLastAct=? where SSN=?");
//			st3.setString(1, customer.getUserSSN());
			st3.setString(1, customer.getPpp());
			st3.setInt(2, customer.getRating());
			st3.setString(3, customer.getDateLastActive());
			st3.setString(4, customer.getUserID());
			st3.executeUpdate();
			
			PreparedStatement st=con.prepareStatement("update account set CardNumber=?, AcctCreationDate=? where OwnerSSN=? and AcctNum=?");
//			st.setString(1, customer.getUserSSN());
			st.setString(1, customer.getCreditCard());
			st.setString(2, customer.getAccCreateDate());
			st.setString(3, customer.getUserID());
			st.setString(4, customer.getAccNum());
			st.executeUpdate();
			
			
		} catch(Exception e) {
			e.printStackTrace();
			return "failure";
		}
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}



	public List<Customer>  getMostActiveUser(){
		List<Customer> customers = new ArrayList<Customer>();

		try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","password");
		    System.out.println("Connected database successfully...");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT S.OwnerSSN, P1.Email, P1.FirstName, P1.LastName, P1.Street, P1.City, P1.State, P1.Zipcode, SUM(S.TotalDates) AS TotalDates "
					+ "FROM person P1, (SELECT DISTINCT P.OwnerSSN, P.ProfileID AS ProfileKey, (SELECT COUNT(Profile1) FROM Date D WHERE ProfileKey=Profile1)"
					+ "+(SELECT COUNT(Profile2) FROM Date D WHERE ProfileKey=Profile2) AS TotalDates FROM Date D, Profile P ORDER BY TotalDates DESC ) S "
					+ "WHERE S.OwnerSSN = P1.SSN GROUP BY S.OwnerSSN ORDER BY SUM(S.TotalDates) DESC");
			
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setUserID(rs.getString("OwnerSSN"));
				customer.setUserSSN(rs.getString("OwnerSSN"));
				customer.setEmail(rs.getString("Email"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setLastName(rs.getString("LastName"));
				customer.setAddress(rs.getString("Street"));
				customer.setCity(rs.getString("City"));
				customer.setState(rs.getString("State"));
				customer.setZipCode(rs.getInt("Zipcode"));
				customer.setAccNum(rs.getString("TotalDates"));
				customers.add(customer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*Sample data begins*/
//		for (int i = 0; i < 10; i++) {
//			Customer customer = new Customer();
//			customer.setUserID("111-11-1111");
//			customer.setUserSSN("112-11-1111");
//			customer.setAddress("123 Success Street");
//			customer.setLastName("Lu");
//			customer.setFirstName("Upendra Nath Chaurasia");
//			customer.setCity("Stony Brook");
//			customer.setState("NY");
//			customer.setEmail("uppu_chaur@cs.sunysb.edu");
//			customer.setZipCode(11790);
//			customers.add(customer);
//		}
		/*Sample data ends*/

		return customers;
	}

	public List<Customer> getDatedCustomers(String primary){

		List<Customer> customers = new ArrayList<Customer>();

		try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","password");
		    System.out.println("Connected database successfully...");
			PreparedStatement st= con.prepareStatement("SELECT Profile1SSN, Profile1, Profile2, OwnerSSN AS Profile2SSN FROM (SELECT OwnerSSN AS Profile1SSN, Profile1 AS Profile1, Profile2 AS Profile2 FROM Date D, Profile P WHERE D.Profile1=P.ProfileID UNION ALL SELECT OwnerSSN AS Profile1SSN, Profile2 AS Profile1, Profile1 AS Profile2 FROM Date D, Profile P WHERE D.Profile2=P.ProfileID) AS CUSTOMERS, Profile P WHERE CUSTOMERS.Profile2=P.ProfileID AND Profile1SSN=?;");
			st.setString(1, primary);
			ResultSet rs=st.executeQuery();

			
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setUserID(rs.getString("Profile1SSN"));
				customer.setUserSSN(rs.getString("Profile2SSN"));
				customers.add(customer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*Sample data begins*/
//		for (int i = 0; i < 10; i++) {
//			Customer customer = new Customer();
//			customer.setUserID("111-11-1111");
//			customer.setAddress("123 Success Street");
//			customer.setLastName("Lu");
//			customer.setFirstName("Upendra Nath Chaurasia");
//			customer.setCity("Stony Brook");
//			customer.setState("NY");
//			customer.setEmail("uppu_chaur@cs.sunysb.edu");
//			customer.setZipCode(11790);
//			customers.add(customer);
//		}
		/*Sample data ends*/

		return customers;
	}

	public List<Customer> getHighestRatedCustomer(){
		List<Customer> customers = new ArrayList<Customer>();

		try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","password");
		    System.out.println("Connected database successfully...");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT P.SSN, U.Rating FROM jeguan.user U, jeguan.person P WHERE P.SSN=U.SSN ORDER BY U.Rating DESC;");
			
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setUserSSN(rs.getString("SSN"));
				customer.setRating(Integer.valueOf(rs.getString("Rating")));
				
				customers.add(customer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*Sample data begins*/
//		for (int i = 0; i < 10; i++) {
//			Customer customer = new Customer();
//			customer.setUserID("111-11-1111");
//			customer.setUserSSN("112-11-1111");
//			customer.setAddress("123 Success Street");
//			customer.setLastName("Lu");
//			customer.setFirstName("Upendra Nath Chaurasia");
//			customer.setCity("Stony Brook");
//			customer.setState("NY");
//			customer.setEmail("uppu_chaur@cs.sunysb.edu");
//			customer.setZipCode(11790);
//			customers.add(customer);
//		}
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

		try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","password");
		    System.out.println("Connected database successfully...");
		    
		    PreparedStatement st2= con.prepareStatement("select ProfileID from profile where OwnerSSN=?;");
		    st2.setString(1, userID);
		    ResultSet rs2 = st2.executeQuery();
		    String prof = "";
		    while(rs2.next()) {
		    	prof = rs2.getString("ProfileID");
		    }
		    
		    
			PreparedStatement st= con.prepareStatement("SELECT S.Suggestion, SUM(S.Rating) as TotalRating FROM (SELECT D.Profile2 AS Suggestion, D.User2Rating AS Rating FROM jeguan.date D WHERE D.Profile1=? UNION ALL SELECT D.Profile1 AS Suggestion, D.User1Rating AS Rating FROM jeguan.date D WHERE D.Profile2=? ) S GROUP BY S.Suggestion ORDER BY SUM(S.Rating) DESC;");
			st.setString(1, prof);
			st.setString(2, prof);
			ResultSet rs=st.executeQuery();

			
			while(rs.next()) {
				PreparedStatement tempSt= con.prepareStatement("select P.*, A.CardNumber from jeguan.person P, jeguan.account A, jeguan.profile Pr"
						+ "where Pr.ProfileID=? and P.SSN=Pr.OwnerSSN and A.OwnerSSN=P.SSN "
						+ "group by P.SSN");
				tempSt.setString(1, rs.getString("Suggestion"));
				ResultSet tempRs = tempSt.executeQuery();
				Customer customer = new Customer();
				
				while(tempRs.next()) {
					customer.setUserSSN(tempRs.getString("SSN"));
					customer.setFirstName(tempRs.getString("FirstName"));
					customer.setLastName(tempRs.getString("LastName"));
					customer.setAddress(tempRs.getString("Street"));
					customer.setCity(tempRs.getString("City"));
					customer.setState(tempRs.getString("State"));
					customer.setZipCode(tempRs.getInt("Zipcode"));
					customer.setTelephone(tempRs.getString("Telephone"));
					customer.setEmail(tempRs.getString("Email"));
					customer.setCreditCard(tempRs.getString("CardNumber"));
				}
				
				customer.setUserID(rs.getString("Suggestion"));
				customer.setRating(Integer.valueOf(rs.getString("TotalRating")));
				customers.add(customer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*Sample data begins*/
//		for (int i = 0; i < 10; i++) {
//			Customer customer = new Customer();
//			customer.setUserID("111-11-1111");
//			customer.setFirstName("long");
//			customer.setLastName("Lu");
//			customer.setAddress("123 Success Street12");
//			customer.setCity("Stony Brook");
//			customer.setState("NY");
//			customer.setZipCode(11790);
//			customer.setTelephone("5166328959");
//			customer.setEmail("shiyong@cs.sunysb.edu");
//			customer.setAccNum("12345");
//			customer.setAccCreateDate("12-12-2020");
//			customer.setCreditCard("1234567812345678");
//			customer.setPpp("User");
//			customer.setRating(1);
//			customer.setDateLastActive("12-12-2020");
//			customers.add(customer);
//		}
		/*Sample data ends*/

		return customers;
	}


}
