package JDBC;

import static java.lang.System.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadWriteTest {

	public void RW() {
		String url = "jdbc:derby://localhost:1521/BookSellerDB";
		String user = "bookguy";
		String pwd = "$3lleR";
		
		Logger logger = Logger.getLogger("JDBC.ReadWriteTest");

		/*
		Automatic Resource Management
		Since JDK 7, The try-with-resources statement will automatically call the close()
		method on any resource declared in the parentheses at the end of the try block
		in the reverse order of their declaration
		object puts here must implement AutoCloseable
		variable declared in the parentheses are final and scoped to only try block
		*/
		try (
		        // resource cannot be declared outside the parenthesis
				Connection conn = DriverManager.getConnection(url, user, pwd); // Get Connection
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);                       // Create Statement, gets closed first
			) { 
			
			String query = "SELECT * FROM Customer";                       // Query String
			ResultSet rs = stmt.executeQuery(query);

			// Get ResultSet column details
			ResultSetMetaData rsmd = rs.getMetaData();

            int colCount = rsmd.getColumnCount();      // How many columns in the resultSet
            for (int i = 1; i <= colCount; i++) {      // column index starts from 1
                out.println("Table Name:   " + rsmd.getTableName(i));
                out.println("Column Name:  " + rsmd.getColumnName(i));
                out.println("Column Size:  " + rsmd.getColumnDisplaySize(i));
            }	

            // ResultSetMetaData does not have a row count method. 
            // To determine the number of rows returned, the ResultSet must be scrollable.	
			// Get the number of rows that are in the resultSet
			if (rs.last()){                        // Move to the last row
				int rowCount = rs.getRow();
				rs.beforeFirst();                  // Move to before the 1st row
			}
            
			// get each row
			while (rs.next()) {  			// Move the cursor to print until the end 
				// Read data in the column named "CustomerID" into a Integer  
				out.print(rs.getInt("CustomerID") + "  ");  
				// Alternatively, out.print(rs.getInt(1) + "  "); since the 1st column is customerID
				// The column index starts from 1

				out.print(rs.getString("FirstName") + "  ");
				out.print(rs.getString("FirstName") + "  ");
				out.print(rs.getString("LastName") + "  ");
				out.print(rs.getString("EMail") + "  ");
				out.println(rs.getString("Phone"));
			}
			
			// Not for OCP exam, Update database from resultSet
			Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);     
		    String query1 = "SELECT UnitPrice from Book WHERE Format = 'Hardcover'";
		    ResultSet rs1 = stmt.executeQuery(query);      // Populate the ResultSet
			while (rs1.next()) {
				if (rs1.getFloat("UnitPrice") == 10.95f) { 
			           rs1.updateFloat("UnitPrice", 11.95f);  
			           rs1.updateRow();                    // update the row in the database
				} 
			}
			
			if (rs != null) {
				rs.close();           // Close the ResultSet
		    }
		} catch (SQLException se) {
			// exceptions thrown as a result of closing resources are suppressed 
			// if there was also an exception thrown in try block. the suppressed can be retrieved like below
			// exception thrown when opening resource can be caught
			Throwable[] suppressed = se.getSuppressed(); // Get an array of suppressed exceptions
			for (Throwable t: suppressed) {  
				out.println("Suppressed exception: " + t);
			}

			// SQLExceptions are chained (multiple exceptions)
			// You must iterate through the exception to get all of the reasons why an exception was thrown.
			while (se != null) {
				// used to demonstrate SQLException methods
				logger.log(Level.SEVERE, "------ SQLException ------");
		        logger.log(Level.SEVERE, "SQLState: " + se.getSQLState());
		        logger.log(Level.SEVERE, "Vendor Error code: " + se.getErrorCode());
		        logger.log(Level.SEVERE,"Message: " + se.getMessage());
			       
		        se = se.getNextException();
			}
		}
	}
}