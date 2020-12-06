package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Employee;

public class EmployeeDao {
	/*
	 * This class handles all the database operations related to the employee table
	 */
	
	public String addEmployee(Employee employee) {

		/*
		 * All the values of the add employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the employee details and return "success" or "failure" based on result of the database insertion.
		 */
		try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");
		    
			String id=employee.getEmployeeID();
			String role=employee.getEmployeeRole();	
			float rate=employee.getHourlyRate();
			Date startdate=Date.valueOf(employee.getStartDate());
			String email=employee.getEmail();
			String password=employee.getPassword();
			String firstName= employee.getFirstName();
			String lastName= employee.getLastName();
			String street= employee.getAddress();
			String state= employee.getState();
			String city= employee.getCity();
			int zip=employee.getZipCode();
			String phone=employee.getTelephone();
			
			PreparedStatement st2=con.prepareStatement("insert into person(`SSN`, `Password`, `FirstName`, `LastName`,`Street`, `City`,`State`,`Zipcode`,`Email`,`Telephone`) values(?,?,?,?,?,?,?,?,?,?)");
			st2.setString(1, id);
			st2.setString(2, password);
			st2.setString(3, firstName);
			st2.setString(4, lastName);
			st2.setString(5,street);
			st2.setString(6,city);
			st2.setString(7,state);
			st2.setInt(8, zip);
			st2.setString(9, email);
			st2.setString(10, phone);
			st2.executeUpdate();
			
			
			PreparedStatement st=con.prepareStatement("insert into employee(`SSN`, `Role`, `StartDate`, `HourlyRate`) values(?,?,?,?)");
			st.setString(1,id);
			st.setString(2,role);
			st.setDate(3,startdate);
			st.setFloat(4, rate); 
			st.executeUpdate();
			
			
			//moveToInsertRow() or moveToCurrentRow() MIGHT WANNA LOOK INTO THIS PUT IN A WHILE LOOP?
		} catch(Exception e) {
			e.printStackTrace();
			return "failure";
		}
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	public String editEmployee(Employee employee) {
		/*
		 * All the values of the edit employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		
		try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");
		    
		    String id=employee.getEmployeeID();
		    String role=employee.getEmployeeRole();	
			float rate=employee.getHourlyRate();
			Date startdate=Date.valueOf(employee.getStartDate());
			String email=employee.getEmail();
			String password=employee.getPassword();
			String firstName= employee.getFirstName();
			String lastName= employee.getLastName();
			String street= employee.getAddress();
			String state= employee.getState();
			String city= employee.getCity();
			int zip=employee.getZipCode();
			String phone=employee.getTelephone();
		    
			PreparedStatement st2=con.prepareStatement("update person set Password=?, FirstName=?, LastName=?, Street=?, City=?, State=?, Zipcode=?, Email=?, Telephone=? where ssn=?") ;
			st2.setString(1, password);
			st2.setString(2, firstName);
			st2.setString(3, lastName);
			st2.setString(4,street);
			st2.setString(5,city);
			st2.setString(6,state);
			st2.setInt(7, zip);
			st2.setString(8, email);
			st2.setString(9, phone);
			st2.setString(10, id);
			st2.executeUpdate();
		    st2.executeUpdate();
		    
		    PreparedStatement st=con.prepareStatement("update employee set Role=?, StartDate=?, HourlyRate=? where ssn=?");
			st.setString(1,role);
			st.setDate(2,startdate);
			st.setFloat(3, rate); 
			st.setString(4,id);
			st.executeUpdate();
		    
		} catch(Exception e) {
			e.printStackTrace();
			return "failure";
		}
		/*Sample data begins*/
		return "success";

	}

	public String deleteEmployee(String employeeID) {
		/*
		 * employeeID, which is the Employee's ID which has to be deleted, is given as method parameter
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
		
		try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");
		    System.out.println(employeeID);
		    PreparedStatement st= con.prepareStatement("delete from employee where ssn=?");
		    st.setString(1,employeeID);
		    st.executeUpdate();
		    
		} catch(Exception e) {
			e.printStackTrace();
			return "failure";
		}
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	
	public List<Employee> getEmployees() {

		/*
		 * The students code to fetch data from the database will be written here
		 * Query to return details about all the employees must be implemented
		 * Each record is required to be encapsulated as a "Employee" class object and added to the "employees" List
		 */

		List<Employee> employees = new ArrayList<Employee>();
		try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM Employee E,Person P where E.SSN = P.SSN");
			
			while(rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeRole(rs.getString("Role"));
				employee.setStartDate(String.valueOf(rs.getDate("StartDate")));
				employee.setEmployeeID(rs.getString("SSN"));
				employee.setHourlyRate(rs.getFloat("HourlyRate"));
				employee.setEmail(rs.getString("Email"));
				employee.setFirstName(rs.getString("FirstName"));
				employee.setLastName(rs.getString("LastName"));
				employee.setAddress(rs.getString("Street"));
				employee.setCity(rs.getString("City"));
				employee.setState(rs.getString("State"));
				employee.setZipCode(rs.getInt("Zipcode"));
				employee.setTelephone(rs.getString("Telephone"));
				employees.add(employee);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employees;
	}

	public Employee getEmployee(String employeeID) {

		/*
		 * The students code to fetch data from the database based on "employeeID" will be written here
		 * employeeID, which is the Employee's ID who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Employee" class object
		 */
		
		Employee employee = new Employee();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
			PreparedStatement st= con.prepareStatement("SELECT * FROM Employee E,Person P where E.SSN = P.SSN AND E.SSN=?");
			st.setString(1, employeeID);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				employee.setEmployeeID(rs.getString("SSN"));
				employee.setHourlyRate(rs.getFloat("HourlyRate"));
				employee.setEmployeeRole(rs.getString("Role"));
				employee.setStartDate(String.valueOf(rs.getDate("StartDate")));
				employee.setEmail(rs.getString("Email"));
				employee.setFirstName(rs.getString("FirstName"));
				employee.setLastName(rs.getString("LastName"));
				employee.setAddress(rs.getString("Street"));
				employee.setCity(rs.getString("City"));
				employee.setState(rs.getString("State"));
				employee.setZipCode(rs.getInt("Zipcode"));
				employee.setTelephone(rs.getString("Telephone"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return employee;
	}
	
	public String getEmployeeID(String username) {
		/*
		 * The students code to fetch data from the database based on "username" will be written here
		 * username, which is the Employee's email address who's Employee ID has to be fetched, is given as method parameter
		 * The Employee ID is required to be returned as a String
		 */
		
		String id="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT E.SSN FROM Employee E, Person P where E.SSN = P.SSN AND email like \'%"+username+"%\'");
			
			while(rs.next()) {
				id=rs.getString("SSN");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}

}