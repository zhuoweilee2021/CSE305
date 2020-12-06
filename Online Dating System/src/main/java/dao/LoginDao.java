package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

import model.Employee;
import model.Login;

public class LoginDao {
	/*
	 * This class handles all the database operations related to login functionality
	 */
	
	
	public Login login(String username, String password) {
		/*
		 * Return a Login object with role as "manager", "customerRepresentative" or "customer" if successful login
		 * Else, return null
		 * The role depends on the type of the user, which has to be handled in the database
		 * username, which is the email address of the user, is given as method parameter
		 * password, which is the password of the user, is given as method parameter
		 * Query to verify the username and password and fetch the role of the user, must be implemented
		 */

		Login login = new Login();
		login.setRole("");
		try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","password");
		    System.out.println("Connected database successfully...");
		    
		    
		    //first check if employee
			PreparedStatement st = con.prepareStatement("SELECT E.Role FROM employee E, person P WHERE P.Email=? AND P.Password=? AND P.SSN=E.SSN");
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			
			
			while(rs.next()) {
				String role = rs.getString("Role");
				
				if (role.equals("Manager")) login.setRole("manager");
				else if (role.equals("CustRep")) login.setRole("customerRepresentative");
				else login.setRole("");
			}
			
			//else check person table
			if (login.getRole().isEmpty()) {
//				System.out.println("check person for customer");
				PreparedStatement st2 = con.prepareStatement("SELECT * FROM person where Email=? and Password=?");
				st2.setString(1, username);
				st2.setString(2, password);
				ResultSet rs2 = st2.executeQuery();
				while(rs2.next()) {
//					System.out.println("got result");
					login.setRole("customer");
				}
			}
			
			//if the login role is still empty return null
			if (login.getRole().isEmpty()) return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*Sample data begins*/
//		login.setRole("customerRepresentative");
//		login.setRole("customer");
//		login.setRole("manager");
		return login;
		/*Sample data ends*/
		
	}
	
	public String addUser(Login login) {
		/*
		 * Query to insert a new record for user login must be implemented
		 * login, which is the "Login" Class object containing username and password for the new user, is given as method parameter
		 * The username and password from login can get accessed using getter methods in the "Login" model
		 * e.g. getUsername() method will return the username encapsulated in login object
		 * Return "success" on successful insertion of a new user
		 * Return "failure" for an unsuccessful database operation
		 */
		try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","password");
		    System.out.println("Connected database successfully...");
		    
		    
		    //first check if employee
			PreparedStatement st = con.prepareStatement("update person set Password=? where Email=?");
			st.setString(1, login.getPassword());
			st.setString(2, login.getUsername());
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/
	}

}
