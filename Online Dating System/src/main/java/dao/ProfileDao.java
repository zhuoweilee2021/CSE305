package dao;

import model.Customer;
import model.Date;

import java.sql.*;
import model.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileDao {
    /*
     * This class handles all the database operations related to the customer table
     */

    public List<Profile> getProfiles() {
    	
    	List<Profile> profiles = new ArrayList<>();
    	try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jeguan","jeguan","111681093");
		    System.out.println("Connected database successfully...");
		    Statement st= con.createStatement();
		    ResultSet rs= st.executeQuery("SELECT ProfileID,OwnerSSN, DatingAgeRangeStart,DatingAgeRangeEnd,DatinGeoRange,M_F,Hobbies,Height,Weight,HairColor, Age,concat_ws(' ',Street,City,State,Zipcode) as Address FROM Profile A, Person B WHERE A.OwnerSSN=B.SSN");
		    
		    while(rs.next()) {
	            Profile profile = new Profile();
	            profile.setProfileID(rs.getString("OwnerSSN"));
	            profile.setProfileName(rs.getString("ProfileID"));
	            profile.setAge(String.valueOf(rs.getString("Age")));
	            profile.setAddress(rs.getString("Address"));
	            profile.setGender(rs.getString("M_F"));
	            profile.setWeight(String.valueOf(rs.getInt("Weight")));
	            profile.setHeight(String.valueOf(rs.getInt("Height")));
	            profile.setHairColor(rs.getString("HairColor"));
	            profile.setInterests(rs.getString("Hobbies"));
	            profile.setPhoto(null);
	            profile.setGeoRange(String.valueOf(rs.getInt("DatinGeoRange")));
	            profile.setAgeRange(rs.getInt("DatingAgeRangeStart")+" to "+rs.getInt("DatingAgeRangeEnd"));
	            profiles.add(profile);
		    }
		    
    	} catch(Exception e) {
    		System.out.println(e);
    	}

        return profiles;
    }

    public List<Profile> getProfilesByAge(String age) {

    	List<Profile> profiles = new ArrayList<>();
    	try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jeguan","jeguan","111681093");
		    System.out.println("Connected database successfully...");
		    Statement st=con.createStatement();
		    ResultSet rs=st.executeQuery("select ownerssn, profileid from profile where age like \'%"+age+"%\'");
		    
		    while(rs.next()) {
	            Profile profile = new Profile();
	            profile.setProfileID(rs.getString("OwnerSSN"));
	            profile.setProfileName(rs.getString("ProfileID"));
	            profiles.add(profile);
		    }
		    
    	} catch(Exception e) {
    		System.out.println(e);
    	}

        return profiles;
    }

    public List<Profile> getProfilesByWeight(String weight) {
    	
    	

        /*Sample data begins*/
        List<Profile> profiles = new ArrayList<>();

    	try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jeguan","jeguan","111681093");
		    System.out.println("Connected database successfully...");
		    Statement st=con.createStatement();
		    ResultSet rs=st.executeQuery("select ownerssn, profileid from profile where weight like \'%"+weight+"%\'");
		    
		    while(rs.next()) {
	            Profile profile = new Profile();
	            profile.setProfileID(rs.getString("OwnerSSN"));
	            profile.setProfileName(rs.getString("ProfileID"));
	            profiles.add(profile);
		    }
		    
    	} catch(Exception e) {
    		System.out.println(e);
    	}

        return profiles;
    }

    public List<Profile> getProfilesByHeight(String height) {

    	List<Profile> profiles = new ArrayList<>();

    	try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jeguan","jeguan","111681093");
		    System.out.println("Connected database successfully...");
		    Statement st=con.createStatement();
		    ResultSet rs=st.executeQuery("select ownerssn, profileid from profile where height like \'%"+height+"%\'");
		    
		    while(rs.next()) {
	            Profile profile = new Profile();
	            profile.setProfileID(rs.getString("OwnerSSN"));
	            profile.setProfileName(rs.getString("ProfileID"));
	            profiles.add(profile);
		    }
		    
    	} catch(Exception e) {
    		System.out.println(e);
    	}
        return profiles;
    }

    public List<Profile> getProfilesByHairColor(String hairColor) {

    	List<Profile> profiles = new ArrayList<>();

    	try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jeguan","jeguan","111681093");
		    System.out.println("Connected database successfully...");
		    Statement st=con.createStatement();
		    ResultSet rs=st.executeQuery("select ownerssn, profileid from profile where haircolor like \'%"+hairColor+"%\'");
		    
		    while(rs.next()) {
	            Profile profile = new Profile();
	            profile.setProfileID(rs.getString("OwnerSSN"));
	            profile.setProfileName(rs.getString("ProfileID"));
	            profiles.add(profile);
		    }
		    
    	} catch(Exception e) {
    		System.out.println(e);
    	}

        return profiles;
    }
    
    public List<Profile> getHighestRatedProfiles(){
    	
    	List<Profile> profiles = new ArrayList<>();
    	try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jeguan","jeguan","111681093");
		    System.out.println("Connected database successfully...");
		    
		    Statement st=con.createStatement();
		    ResultSet rs= st.executeQuery("SELECT ProfileID, Rating FROM User u, Profile p WHERE u.SSN = p.OwnerSSN AND Rating >=3 ORDER BY Rating DESC");
		    
		    while(rs.next()) {
	            Profile profile = new Profile();
	            profile.setProfileName(rs.getString("ProfileID"));
	            profile.setAge(rs.getString("Rating"));
	            profiles.add(profile);
		    }
		    
		} catch (Exception e) {
			System.out.println(e);
		}
    	return profiles;
    }
    
    public List<Profile> mostActiveProfiles(){
    	List<Profile> dates = new ArrayList<>();
        
        try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jeguan","jeguan","111681093");
		    System.out.println("Connected database successfully...");
		    
		    Statement st= con.createStatement();
		    ResultSet rs = st.executeQuery("SELECT profile, COUNT(profile) as active from\r\n"
		    		+ "(SELECT Profile1 AS Profile FROM jeguan.date\r\n"
		    		+ "UNION ALL\r\n"
		    		+ "SELECT Profile2 AS Profile FROM jeguan.date) as A GROUP BY Profile ORDER BY active desc;\r\n"
		    		+ "");
		    
		    while(rs.next()) {
		    	Profile date= new Profile();
		    	date.setProfileName(rs.getString("profile"));
		    	date.setAge(rs.getString("active"));
		    	dates.add(date);
		    }
		    
		} catch (Exception e) {
			System.out.println(e);
		}
        return dates;
    }

}