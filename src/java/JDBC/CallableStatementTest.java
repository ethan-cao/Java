package JDBC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CallableStatementTest {

	public static void main(String[] args) {
		String url = "jdbc:derby://localhost:1521/BookSellerDB";
		String user = "bookguy";
		String pwd = "$3lleR";
		
		try ( Connection conn = DriverManager.getConnection(url, user, pwd) ) {
			// find books bought by customerID during fromDate and toDate, IN parameter example
	        int customerID = 5001;
	        Date fromDate = new Date(50000) ;  // The start date for the search
	        Date toDate = new Date(80000);     // The end date for the search

	        String getBooksInDateRange  = "{call getBooksDateRange(?, ?, ?)}";
	        CallableStatement cstmt = conn.prepareCall(getBooksInDateRange, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

	        cstmt.setInt(1, customerID);  // IN parameter 1 for customerID
	        cstmt.setDate(2, fromDate);   // IN parameter 2 for fromDate
	        cstmt.setDate(3, toDate);     // IN parameter 3 for toDate

	        cstmt.execute();

	        if (cstmt.getMoreResults()) {
	        	//true: result is a ResultSet    	
	        	ResultSet rs = cstmt.getResultSet();
	        } else {
	        	//false : result is an update count
	        	int updateCount = cstmt.getUpdateCount(); 
	        	// If the update count is -1 it means there are no more results. 
	        	// the update count will also be -1 when the current result is a ResultSet
	        }


	        // get the total of all orders placed by a customer, OUT parameter example
	        cstmt = conn.prepareCall("{? = call customerTotal (?)}");
	        cstmt.registerOutParameter(1, java.sql.Types.DOUBLE);  // register the OUT parameter
	        cstmt.setInt(2, customerID);

	        cstmt.execute();  // Note we are not returning a ResultSet, so execute is the appropriate method

	        double customerTotal = cstmt.getDouble(1);


	        
	        // counts the orders and returns them in the same parameter, INOUT parameter example
	        cstmt = conn.prepareCall("{call customerOrderCount (?)}");
	        cstmt.setInt(1, customerID);                            // set the IN part of the parameter
	        cstmt.registerOutParameter(1, java.sql.Types.INTEGER);  // the OUT
	        cstmt.execute();
	        int numberOfOrders = cstmt.getInt(1);
          } catch (SQLException se) {
        	  
          }
		
	}

}
