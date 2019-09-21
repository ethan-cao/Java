package JDBC;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class CachedRowSetTest {

	public static void main(String[] args) {
		String url = "jdbc:derby://localhost:1527/BookSellerDB";
        String user = "bookguy";
        String pwd = "$3lleR";
		String query = "SELECT * FROM Author";

        // Disconnected RowSet
        try (CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet() ){

        	crs.setCommand(query);
        	crs.setUrl(url);
        	crs.setUsername(user);
        	crs.setPassword(pwd);

        	crs.execute();    	// Populate the CachedRowSet with data	
        	
        	// Once you have made some changes and are ready to push changes to the database
        	crs.acceptChanges();
        } catch (SQLException sq){
        	
        }

	}

}
