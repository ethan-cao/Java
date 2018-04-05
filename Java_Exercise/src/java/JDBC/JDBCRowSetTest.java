package JDBC;

import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class JDBCRowSetTest {

	public static void main(String[] args) {
		String url = "jdbc:derby://localhost:1527/BookSellerDB";
        String user = "bookguy";
        String pwd = "$3lleR";
		String query = "SELECT * FROM Author";

        // Connected RowSet
        // returns an instance of a factory and creates RowSet objects from the reference implementation
        try (JdbcRowSet jrs = RowSetProvider.newFactory().createJdbcRowSet()) {

          jrs.setCommand(query);
          jrs.setUrl(url);
          jrs.setUsername(user);
          jrs.setPassword(pwd);
          
          // only intended to populate the JdbcRowSet, performing UPDATE/INSERT/DELETE query will result in SQLException
          jrs.execute();   
          
          // whenever a row is updated/deleted/inserted, event listener will be invoked
          jrs.addRowSetListener(new MyRowSetListener());

          while (jrs.next()) {
        	  jrs.last();                               // Position to the last row of Authors
        	  jrs.updateString("FirstName", "Raquel");  // Update the first name (performing update on the JDBCRowSet)
        	  jrs.updateRow();                          // Apply change to the database
        	  
        	  jrs.absolute(5);
        	  jrs.deleteRow();
        	   
        	  jrs.moveToInsertRow();
        	  jrs.updateInt("AuthorID", 1032);
        	  jrs.updateString("FirstName", "Michael");
        	  jrs.updateString("LastName", "Crichton");
        	  jrs.insertRow();
        	  jrs.moveToCurrentRow();
          }
          
        } catch (SQLException se) {

        }
	}
}

// Use the event to keep JDBCRowSet sync
class MyRowSetListener implements RowSetListener {
	@Override
	//A row changed: updated, inserted or deleted.
	public void rowChanged(RowSetEvent event) {
		if (event.getSource() instanceof RowSet) {
			try {
				((RowSet) event.getSource()).execute(); // Re-execute the query, refreshing the results (syncing to DB)
			} catch (SQLException se) {
				System.out.println("SQLException during execute");
			} 	
		}
	}

	@Override
	// Cursor moved
	public void cursorMoved(RowSetEvent event) {  
	}

	@Override
	// Entire RowSet changed
	public void rowSetChanged(RowSetEvent event) { 
	}
}