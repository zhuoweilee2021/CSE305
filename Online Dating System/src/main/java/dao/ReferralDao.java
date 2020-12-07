package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class ReferralDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */
	
	public String referProfile(String profileA, String profileB, String profileC){
		
		String name=profileA;
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jeguan","jeguan","111681093");
		    System.out.println("Connected database successfully...");
		    
		    Statement st= con.createStatement();
		    ResultSet rs= st.executeQuery("SELECT ProfileID FROM Profile A, Person B WHERE A.OwnerSSN = B.SSN and email like \'%"+profileA+"%\'");
		    
		    while(rs.next()) {
		    	String profile=rs.getString("ProfileID");
		    	PreparedStatement st2=con.prepareStatement("insert into blinddate(profileA,`profileB`,`profileC`, Date_Time) values (?,?,?,?)");
		    	st2.setString(1, profile);
			    st2.setString(2,profileB);
			    st2.setString(3,profileC);
			    st2.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			    st2.executeUpdate();
		    }
		    
		    Statement st2= con.createStatement();
		    ResultSet rs2= st2.executeQuery("SELECT concat_ws(' ',FirstName,LastName) as Name FROM Person B WHERE email like \'%"+profileA+"%\' limit 1");
		    if(rs2.next()) {
		    	name=rs2.getString("Name");
		    }
		    
		} catch(Exception e) {
			System.out.println(e);
		}
		    
		return name +" has referred " + profileB + "  to " + profileC;
	}
}