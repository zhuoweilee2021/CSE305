package dao;

import model.Profile;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class LikesDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */
	public String setNewLike(String user1, String user2){

	    String name=user1;
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jeguan","jeguan","111681093");
		    System.out.println("Connected database successfully...");
		    
		    Statement st= con.createStatement();
		    ResultSet rs= st.executeQuery("SELECT ProfileID FROM Profile A, Person B WHERE A.OwnerSSN = B.SSN and email like \'%"+user1+"%\'");
		    
		    while(rs.next()) {
		    	String profile=rs.getString("ProfileID");
		    	System.out.println(profile);
		    	PreparedStatement st2=con.prepareStatement("insert into likes(Liker,`Likee`, Date_Time) values (?,?,?)");
			    st2.setString(1, profile);
			    st2.setString(2,user2);
			    st2.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			    st2.executeUpdate();
		    }
		    Statement st2= con.createStatement();
		    ResultSet rs2= st2.executeQuery("SELECT concat_ws(' ',FirstName,LastName) as Name FROM Person B WHERE email like \'%"+user1+"%\' limit 1");
		    if(rs2.next()) {
		    	name=rs2.getString("Name");
		    }
		    
		} catch(Exception e) {
			System.out.println(e);
		}
		return "User - "+name+" likes "+user2;
	}

	public List<String> getFavorites(String email){
		

		List<String> favs = new ArrayList<>();
		
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jeguan","jeguan","111681093");
		    System.out.println("Connected database successfully...");
		    
		    Statement st= con.createStatement();
		    ResultSet rs= st.executeQuery("select distinct(likee) as Likee from likes a, \r\n"
		    		+ "(SELECT ProfileID from Profile A, Person B WHERE A.OwnerSSN = B.SSN and email like \'%"+email+"%\') as liker \r\n"
		    				+ "where a.liker=liker.ProfileID;");
		    
		    while(rs.next()) {
		    	String profile=rs.getString("Likee");
	    		favs.add(profile);
		    }
		} catch(Exception e) {
			System.out.println(e);
		}

		return favs;
	}

}