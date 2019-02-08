/**
 * 
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import enums.CD_Schema;

/**
 * @author Brown
 * Handles/executes a stored procedure. Returns a results set.
 */
public class StoredProcedure {

	private String query = "";
	private Database database = null;
	private ResultSet rs = null;
	
	public StoredProcedure(String query, Database database) {
		super();
		this.query = query;
		this.database = database;
	}

	public ResultSet execute() {
		
		try {
			System.out.println("Query: " + query);

//			java.sql.CallableStatement stmt = mySqlConnection.prepareCall(query);
			java.sql.CallableStatement stmt = database.connection().prepareCall(query);
			rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("Model"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return rs;
	}
	
	
}
