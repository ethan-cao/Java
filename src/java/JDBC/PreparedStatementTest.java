package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementTest {

	public static void main(String[] args) {
		String url = "jdbc:derby://localhost:1521/BookSellerDB";
		String user = "bookguy";
		String pwd = "$3lleR";
		
		try ( Connection conn = DriverManager.getConnection(url, user, pwd) ) {
            String pQuery = "SELECT UnitPrice from Book WHERE Title LIKE ?"; // Parameterized 

            // once the object is created with the parameterized query, the query is pre-compiled.
            PreparedStatement pstmt = conn.prepareStatement(pQuery);

            // replace the String for 1st parameter (?), could also be int ...
            // when parameter is passed, the query is stored in its post-plan state in the database	
            pstmt.setString(1, "%Heroes%");   // setXXX method is indexed from 1
            
            // When parameters are received, the database simply has to substitute them into the plan and execute the query.
            ResultSet rs = pstmt.executeQuery();
          } catch (SQLException se) {
        	  
          }
		
	}

}
