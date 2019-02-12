/**
 * 
 */
package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;

/**
 * @author Steve Brown 
 * Handles/executes a stored procedure.
 * Inputs: 
 * 1. Database object.
 * 2. Database connection interface.
 * 3. Database connection. 
 * 
 * Returns a Storedprocedure object with Error code (if any) and result set if successful.
 */
public class StoredProcedure {

	private String query = "";
	private ResultSet rs = null;
	private Connection conn = null;
	private ErrorCodes eCode = ErrorCodes.NONE;

	public StoredProcedure(String query, Database database) {
		this.query = query;
		this.conn = database.dbConnection().connection();
	}

	public StoredProcedure(String query, DbConnectionInterface dbIt) {
		this.query = query;
		this.conn = dbIt.connection();
	}

	public StoredProcedure(String query, Connection conn) {
		this.query = query;
		this.conn = conn;
	}

	public StoredProcedure execute() {
		try {
			CallableStatement stmt = conn.prepareCall(query);
			rs = stmt.executeQuery();
		} catch (SQLException e) {			
			eCode = ErrorHandler.checkError(ErrorCodes.STORED_PROCEDURE, e.getMessage());
		} 
		return this;
	}

	public ResultSet getRs() {
		return rs;
	}

	public ErrorCodes geteCode() {
		return eCode;
	}	
}
