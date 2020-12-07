package dao;

import model.Customer;
import model.Profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Person;

public class PersonDao {

//	Write functions in order to utilise the Person model
	
	public List<Person> getPeople() {
		List<Person> profiles = new ArrayList<>();
    	try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jeguan","jeguan","111681093");
		    System.out.println("Connected database successfully...");
		    Statement st= con.createStatement();
		    ResultSet rs= st.executeQuery("select ");
		    
		    while(rs.next()) {
	            Person profile = new Person();
	            profile.setPersonID(rs.getString("SSN"));
	            profile.setPassword(rs.getString("Password"));
	            profile.setFirstName(String.valueOf(rs.getString("FirstName")));
	            profile.setLastName(rs.getString("LastName"));
	            profile.setStreet(rs.getString("Street"));
	            profile.setCity(String.valueOf(rs.getInt("City")));
	            profile.setZipcode(String.valueOf(rs.getInt("Zipcode")));
	            profile.setEmail(rs.getString("Email"));
	            profile.setTelephone(rs.getString("Telephone"));
	            profile.setState(String.valueOf(rs.getInt("State")));
	            profiles.add(profile);
		    }
		    
    	} catch(Exception e) {
    		System.out.println(e);
    	}

        return profiles;
	}

}