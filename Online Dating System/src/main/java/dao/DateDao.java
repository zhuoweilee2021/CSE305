package dao;

import model.Date;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.*;


public class DateDao {

    public String addDate(Date date) {
        return "Success";
    }


    public List<Date> getDatesByCalendar(String calendarDate) {
        List<Date> dates = new ArrayList<Date>();

        /*Sample data begins*/
        for (int i = 0; i < 10; i++) {
            Date date = new Date();
            date.setDateID("12313123");
            date.setUser1ID("1212");
            date.setUser2ID("2121");
            date.setDate("12-12-2020");
            date.setGeolocation("location");
            date.setBookingfee("21");
            date.setCustRepresentative("Manoj Pandeyq");
            date.setComments("Comments");
            date.setUser1Rating("3");
            date.setUser2Rating("3");
            dates.add(date);
        }
        /*Sample data ends*/

        return dates;
    }

    public List<Date> getDatesByMonthYear(String month, String year) {
        List<Date> dates = new ArrayList<Date>();

        /*Sample data begins*/
        for (int i = 0; i < 10; i++) {
            Date date = new Date();
            date.setDateID("12313123");
            date.setUser1ID("1212");
            date.setUser2ID("2121");
            date.setDate("12-12-2020");
            date.setGeolocation("location");
            date.setBookingfee("21");
            date.setCustRepresentative("Manoj Pandeyw");
            date.setComments("Comments");
            date.setUser1Rating("3");
            date.setUser2Rating("3");
            dates.add(date);
        }
        /*Sample data ends*/

        return dates;
    }

    public List<Date> getDatesByCustomerName(String customerName) {

        List<Date> dates = new ArrayList<Date>();

        /*Sample data begins*/
        for (int i = 0; i < 10; i++) {
            Date date = new Date();
            date.setDateID("12313123");
            date.setUser1ID("1212");
            date.setUser2ID("2121");
            date.setDate("12-12-2020");
            date.setGeolocation("location");
            date.setBookingfee("21");
            date.setCustRepresentative("Manoj Pandeye");
            date.setComments("Comments");
            date.setUser1Rating("3");
            date.setUser2Rating("3");
            dates.add(date);
        }
        /*Sample data ends*/

        return dates;
    }

    public List<Date> getHighestRatedCalendarDate() {
        List<Date> dates = new ArrayList<Date>();

        /*Sample data begins*/
        for (int i = 0; i < 10; i++) {
            Date date = new Date();
            date.setDateID("12313123");
            date.setUser1ID("1212");
            date.setUser2ID("2121");
            date.setDate("12-12-2020");
            date.setGeolocation("location");
            date.setBookingfee("21");
            date.setCustRepresentative("Manoj Pandey9");
            date.setComments("Comments");
            date.setUser1Rating("3");
            date.setUser2Rating("3");
            dates.add(date);
        }
        /*Sample data ends*/

        return dates;
    }


    public List<Date> getOpenDates() {

        List<Date> dates = new ArrayList<Date>();

        /*Sample data begins*/
        for (int i = 0; i < 10; i++) {
            Date date = new Date();
            date.setDateID("12313123");
            date.setUser1ID("1212");
            date.setUser2ID("2121");
            date.setDate("12-12-2020");
            date.setGeolocation("location");
            date.setBookingfee("21");
            date.setCustRepresentative("Manoj Pandeyf");
            date.setComments("Comments");
            date.setUser1Rating("3");
            date.setUser2Rating("3");
            dates.add(date);
        }
        /*Sample data ends*/

        return dates;
    }

    public String setNewDate(String user1, String user2) {
        return "Successfull date b/w " + user1 + " and " + user2;
    }

    public String cancelDate(String dateID) {
        return "Date - " + dateID + " is now cancelled";
    }

    public String commentDate(String dateID, String comment) {
        return "Date - " + dateID + " has new comment - " + comment;
    }

    public String getSalesReport(String month, String year) {
        return "1211";
    }

    public List<Date> getPendingDates(String user) {

    	List<Date> dates = new ArrayList<Date>();

        try {

        	Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jeguan","jeguan","111681093");
		    System.out.println("Connected database successfully...");
		    
		    Statement st= con.createStatement();
		    ResultSet rs= st.executeQuery("SELECT ProfileID FROM Profile A, Person B WHERE A.OwnerSSN = B.SSN and email like \'%"+user+"%\'");
		    
		    if(rs.next()) {
		    	String profile=rs.getString("ProfileID");
		    	Statement st2=con.createStatement();
				ResultSet rs2=st2.executeQuery("SELECT * FROM Date WHERE date_time>now() and (profile1 like \'%"+profile +"%\'OR profile2 like \'%"+profile+"%\')");
				while(rs2.next()) {
					Date date= new Date();
					date.setGeolocation(rs2.getString("Location"));
			    	date.setBookingfee(rs2.getString("BookingFee"));
			    	date.setDateID(rs2.getString("DateID"));
			    	date.setUser1ID(rs2.getString("Profile1"));
			    	date.setUser2ID(rs2.getString("Profile2"));
			    	date.setDate(rs2.getString("Date_Time"));
			    	date.setCustRepresentative(rs2.getString("CustRep"));
			    	date.setComments(rs2.getString("Comments"));
			    	date.setUser1Rating(rs2.getString("User1Rating"));
			    	date.setUser2Rating(rs2.getString("User2Rating"));
			    	dates.add(date);
			    }
		    }
		    
		} catch (Exception e) {
			System.out.println(e);
			
		}

        return dates;
    }

    public List<Date> getPastDates(String user) {

        List<Date> dates = new ArrayList<Date>();

        try {

        	Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jeguan","jeguan","111681093");
		    System.out.println("Connected database successfully...");
		    
		    Statement st= con.createStatement();
		    ResultSet rs= st.executeQuery("SELECT ProfileID FROM Profile A, Person B WHERE A.OwnerSSN = B.SSN and email like \'%"+user+"%\'");
		    
		    if(rs.next()) {
		    	String profile=rs.getString("ProfileID");
		    	Statement st2=con.createStatement();
				ResultSet rs2=st2.executeQuery("SELECT * FROM Date WHERE (profile1 like \'%"+profile +"%\'OR profile2 like \'%"+profile+"%\')and date_time<now()");
				while(rs2.next()) {
					Date date= new Date();
					date.setGeolocation(rs2.getString("Location"));
			    	date.setBookingfee(rs2.getString("BookingFee"));
			    	date.setDateID(rs2.getString("DateID"));
			    	date.setUser1ID(rs2.getString("Profile1"));
			    	date.setUser2ID(rs2.getString("Profile2"));
			    	date.setDate(rs2.getString("Date_Time"));
			    	date.setCustRepresentative(rs2.getString("CustRep"));
			    	date.setComments(rs2.getString("Comments"));
			    	date.setUser1Rating(rs2.getString("User1Rating"));
			    	date.setUser2Rating(rs2.getString("User2Rating"));
			    	dates.add(date);
			    }
		    }
		    
		} catch (Exception e) {
			System.out.println(e);
			
		}

        return dates;
    }


    public List<Date> getMostPopularLocation(String user) {
    	
    	ArrayList<Date> locations= new ArrayList<>();
    	try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jeguan","jeguan","111681093");
		    System.out.println("Connected database successfully...");
		    
		    Statement st= con.createStatement();
		    ResultSet rs= st.executeQuery("SELECT ProfileID FROM Profile A, Person B WHERE A.OwnerSSN = B.SSN and email like \'%"+user+"%\'");
		    
		    if(rs.next()) {
		    	String profile=rs.getString("ProfileID");
		    	Statement st2=con.createStatement();
				ResultSet rs2=st.executeQuery("SELECT Location, count(location) as Rate FROM jeguan.Date WHERE profile1 like \'%"+profile +"%\'OR profile2 like \'%"+profile+"%\' group by location order by rate desc");
				while(rs2.next()) {
			    	Date date= new Date();
			    	date.setGeolocation(rs2.getString("Location"));
			    	date.setBookingfee(rs2.getString("Rate"));
			    	locations.add(date);
			    }
		    }
		   
		} catch (Exception e) {
			System.out.println(e);
		}
    	return locations;
    }


    public List<Date> getDateSuggestions(String user) { //ZOOO DID THIS ONE

        List<Date> dates = new ArrayList<Date>();

        /*Sample data begins*/
        for (int i = 0; i < 10; i++) {
            Date date = new Date();
            date.setDateID("12313123");
            date.setUser1ID("1212");
            date.setUser2ID("2121");
            date.setDate("12-12-2020");
            date.setGeolocation("location");
            date.setBookingfee("21");
            date.setCustRepresentative("Manoj Pandey2");
            date.setComments("Comments");
            date.setUser1Rating("3");
            date.setUser2Rating("3");
            dates.add(date);
        }
        /*Sample data ends*/

        return dates;
    }

    public List<Date> getRevenueByCalendar(String calendarDate) {
        List<Date> dates = new ArrayList<Date>();
        
        try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jeguan","jeguan","111681093");
		    System.out.println("Connected database successfully...");
		    
		    Statement st= con.createStatement();
		    ResultSet rs = st.executeQuery("SELECT * FROM Date WHERE Date(Date_Time) like \'%"+calendarDate+"%\'");
		    
		    while(rs.next()) {
		    	Date date= new Date();
		    	date.setBookingfee(rs.getString("BookingFee"));
		    	date.setUser1ID(rs.getString("Profile1"));
		    	date.setUser2ID(rs.getString("Profile2"));
		    	date.setDate(calendarDate);
		    	date.setGeolocation(rs.getString("Location"));
		    	date.setCustRepresentative(rs.getString("CustRep"));
		    	date.setDateID(rs.getString("DateID"));
		    	dates.add(date);
		    }
		    
		    
		} catch (Exception e) {
			System.out.println(e);
		}

        return dates;
    }
}