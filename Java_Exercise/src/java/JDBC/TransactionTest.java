package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class TransactionTest {

	public static void main(String[] args) {
		String url = "jdbc:derby://localhost:1521/BookSellerDB";
		String user = "bookguy";
		String pwd = "$3lleR";
		
			try (
				Connection conn = DriverManager.getConnection(url, user, pwd);  // Get Connection
				Statement stmt = conn.createStatement();                       // Create Statement, gets closed first
			) {
			conn.setAutoCommit(false);  // this means begin transaction
			
			int result1, result2, result3;
			Savepoint sp1;
				
			try{
				result1 = stmt.executeUpdate("INSERT INTO Author VALUES(1031, 'Rachel', 'McGinn')");
				result2 = stmt.executeUpdate("INSERT INTO Book VALUES('0554466789', 'My American Dolls', '2012-08-31','Paperback', 7.95)");
			    
				sp1 = conn.setSavepoint();      // Create a savepoint for the two inserts so far
			} catch (SQLException se){
				conn.rollback();
				// if fail, roll back entire transaction
				// rollback () releases any database locks currently held by this connection object.
			    throw new SQLException("fail");
			}
				
			try{
			    result3 = stmt.executeUpdate("INSERT INTO Books_by_Author VALUES(1031,'0554466789')");

			    // three tables to be populated in a single transaction (insert statements happen as a unit)
			    // Commit the current transaction and start another transaction	 
			    conn.commit();      
			    // if we don't commit, the driver and database simply roll back the transaction as if nothing happened.
			} catch (SQLException ex) {
			    conn.rollback(sp1);  // if the last one failed, roll back to savepoint

			    //conn.rollback();     // roll back entire transaction   // release lock ????
			}
			
		} catch (SQLException e) {
		}  

	}

}
