package dao;

import model.Date;
import model.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DateDao {

    public String addDate(Date date) {
    	
    	try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");;
			
			PreparedStatement st2=con.prepareStatement("insert into date(`Profile1`, `Profile2`, `CustRep`,`Date_Time`, `Location`,`BookingFee`,`Comments`,`User1Rating`,`User2Rating`) values(?,?,?,?,?,?,?,?,?)");
			st2.setString(1, date.getUser1ID());
			st2.setString(2, date.getUser2ID());
			st2.setString(3, date.getCustRepresentative());
			st2.setString(4, date.getDate());
			st2.setString(5, date.getGeolocation());
			st2.setString(6, date.getBookingfee());
			st2.setString(7, date.getUser1Rating());
			st2.setString(8, date.getUser2Rating());
			st2.executeUpdate();
			
			
        } catch(Exception e) {
			e.printStackTrace();
		}

    	
        return "Success";
    }


    public List<Date> getDatesByCalendar(String calendarDate) {
        List<Date> dates = new ArrayList<Date>();
        try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");

		    PreparedStatement st= con.prepareStatement("SELECT P.FirstName, P.LastName, D.* FROM date D, person P WHERE D.CustRep = P.SSN AND Date_Time=?;");
			st.setString(1, calendarDate);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				Date date = new Date();
				date.setDateID(String.valueOf((Math.floor(Math.random() * 1000000))));
	            date.setUser1ID(rs.getString("Profile1"));
	            date.setUser2ID(rs.getString("Profile2"));
	            date.setDate(rs.getString("Date_Time"));
	            date.setGeolocation(rs.getString("Location"));
	            date.setBookingfee(rs.getString("BookingFee"));
	            date.setCustRepresentative(rs.getString("FirstName") + " " + rs.getString("LastName"));
	            date.setComments(rs.getString("Comments"));
	            date.setUser1Rating(rs.getString("User1Rating"));
	            date.setUser2Rating(rs.getString("User2Rating"));
				dates.add(date);
			}

        } catch(Exception e) {
			e.printStackTrace();
		}
        
        return dates;
    }

    public List<Date> getDatesByMonthYear(String month, String year) {
        List<Date> dates = new ArrayList<Date>();
        
        try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");

		    PreparedStatement st= con.prepareStatement("SELECT P.FirstName, P.LastName, D.* FROM date D, person P WHERE D.CustRep = P.SSN AND MONTH(Date_Time)=? AND YEAR(Date_Time)=?;");
			st.setString(1, month);
			st.setString(2, year);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				Date date = new Date();
				date.setDateID(rs.getString("DateID"));
	            date.setUser1ID(rs.getString("Profile1"));
	            date.setUser2ID(rs.getString("Profile2"));
	            date.setDate(rs.getString("Date_Time"));
	            date.setGeolocation(rs.getString("Location"));
	            date.setBookingfee(rs.getString("BookingFee"));
	            date.setCustRepresentative(rs.getString("FirstName") + " " + rs.getString("LastName"));
	            date.setComments(rs.getString("Comments"));
	            date.setUser1Rating(rs.getString("User1Rating"));
	            date.setUser2Rating(rs.getString("User2Rating"));
				dates.add(date);
			}

        } catch(Exception e) {
			e.printStackTrace();
		}
        
        return dates;
    }

    public List<Date> getDatesByCustomerName(String customerName) {

        List<Date> dates = new ArrayList<Date>();

        try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");

		    PreparedStatement st= con.prepareStatement("SELECT P.FirstName, P.LastName, D.* FROM date D, person P WHERE D.CustRep = P.SSN AND MONTH(Date_Time)=?;");
			st.setString(1, customerName);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				Date date = new Date();
				date.setDateID(rs.getString("DateID"));
	            date.setUser1ID(rs.getString("Profile1"));
	            date.setUser2ID(rs.getString("Profile2"));
	            date.setDate(rs.getString("Date_Time"));
	            date.setGeolocation(rs.getString("Location"));
	            date.setBookingfee(rs.getString("BookingFee"));
	            date.setCustRepresentative(rs.getString("FirstName") + " " + rs.getString("LastName"));
	            date.setComments(rs.getString("Comments"));
	            date.setUser1Rating(rs.getString("User1Rating"));
	            date.setUser2Rating(rs.getString("User2Rating"));
				dates.add(date);
			}

        } catch(Exception e) {
			e.printStackTrace();
		}
        
        return dates;
    }

    public List<Date> getHighestRatedCalendarDate() {
        List<Date> dates = new ArrayList<Date>();

        try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");

		    Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT R.Date, R.User1Rating ,AVG(R.TotalRating) as AvgRating FROM  ((SELECT CAST(D.Date_Time AS DATE) AS Date, SUM(D.User1Rating) AS TotalRating FROM date D GROUP BY CAST(D.Date_Time AS DATE) ORDER BY SUM(D.User1Rating) DESC) UNION ALL (SELECT CAST(D.Date_Time AS DATE) AS Date, SUM(D.User2Rating) AS TotalRating FROM date D GROUP BY CAST(D.Date_Time AS DATE) ORDER BY SUM(D.User2Rating) DESC )) R GROUP BY R.Date ORDER BY AVG(R.TotalRating) DESC;\n"
					+ "");
			
			while(rs.next()) {
				Date date = new Date();
				date.setDateID(String.valueOf((Math.floor(Math.random() * 1000000))));
	            date.setDate(rs.getString("Date_Time"));
	            date.setUser2Rating(rs.getString("AvgRating"));
				dates.add(date);
			}

        } catch(Exception e) {
			e.printStackTrace();
		}
        
        return dates;
    }


    public List<Date> getOpenDates() {

        List<Date> dates = new ArrayList<Date>();

        try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");

		    Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT E.FirstName, E.LastName, D.* FROM date D, employee E WHERE D.CustRep=E.SSN");
			
			while(rs.next()) {
				Date date = new Date();
				date.setDateID(rs.getString("DateID"));
	            date.setUser1ID(rs.getString("Profile1"));
	            date.setUser2ID(rs.getString("Profile2"));
	            date.setDate(rs.getString("Date_Time"));
	            date.setGeolocation(rs.getString("Location"));
	            date.setBookingfee(rs.getString("BookingFee"));
	            date.setCustRepresentative(rs.getString("FirstName") + " " + rs.getString("LastName"));
	            date.setComments(rs.getString("Comments"));
	            date.setUser1Rating(rs.getString("User1Rating"));
	            date.setUser2Rating(rs.getString("User2Rating"));
	            dates.add(date);
			}

        } catch(Exception e) {
			e.printStackTrace();
		}
        
        return dates;
    }

    public String setNewDate(String user1, String user2) {
    	
    	try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");
		    
			PreparedStatement st2=con.prepareStatement("insert into date(`DateID`,`Profile1`, `Profile2`) values(?,?,?)");
			st2.setString(1, String.valueOf((Math.floor(Math.random() * 1000000))));
			st2.setString(2, user1);
			st2.setString(3, user2);
			st2.executeUpdate();
			
			//moveToInsertRow() or moveToCurrentRow() MIGHT WANNA LOOK INTO THIS PUT IN A WHILE LOOP?
		} catch(Exception e) {
			e.printStackTrace();
			return "failure";
		}
    	
        return "Successfull date b/w " + user1 + " and " + user2;
    }

    public String cancelDate(String dateID) {
    	
    	try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");
		    
			PreparedStatement st2=con.prepareStatement("delete from date where DateID=?");
			st2.setString(1, dateID);
			st2.executeUpdate();
			
			//moveToInsertRow() or moveToCurrentRow() MIGHT WANNA LOOK INTO THIS PUT IN A WHILE LOOP?
		} catch(Exception e) {
			e.printStackTrace();
			return "failure";
		}

    	
        return "Date - " + dateID + " is now cancelled";
    }

    public String commentDate(String dateID, String comment) {
    	
    	try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to a selected database...");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeguan","root","badpassword");
		    System.out.println("Connected database successfully...");
		    
			PreparedStatement st2=con.prepareStatement("update date set Comment=? where DateID=?");
			st2.setString(1, comment);
			st2.setString(2, dateID);
			st2.executeUpdate();
			
			//moveToInsertRow() or moveToCurrentRow() MIGHT WANNA LOOK INTO THIS PUT IN A WHILE LOOP?
		} catch(Exception e) {
			e.printStackTrace();
			return "failure";
		}
    	
        return "Date - " + dateID + " has new comment - " + comment;
    }

    public String getSalesReport(String month, String year) {
        return "1211";
    }

    public List<Date> getPendingDates(String user) {

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
            date.setCustRepresentative("Manoj Pandey");
            date.setComments("Comments");
            date.setUser1Rating("3");
            date.setUser2Rating("3");
            dates.add(date);
        }
        /*Sample data ends*/

        return dates;
    }

    public List<Date> getPastDates(String user) {

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
            date.setCustRepresentative("Manoj Pandey");
            date.setComments("Comments");
            date.setUser1Rating("3");
            date.setUser2Rating("3");
            dates.add(date);
        }
        /*Sample data ends*/

        return dates;
    }


    public String getMostPopularLocation(String user) {
        return "Jersey City";
    }


    public List<Date> getDateSuggestions(String user) {

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
            date.setCustRepresentative("Manoj Pandey");
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

        /*Sample data begins*/
        for (int i = 0; i < 10; i++) {
            Date date = new Date();
            date.setDateID("12313123");
            date.setUser1ID("1212");
            date.setUser2ID("2121");
            date.setDate("12-12-2020");
            date.setGeolocation("location");
            date.setBookingfee("21");
            date.setCustRepresentative("Manoj Pandey");
            date.setComments("Comments");
            date.setUser1Rating("3");
            date.setUser2Rating("3");
            dates.add(date);
        }
        /*Sample data ends*/

        return dates;
    }
}
