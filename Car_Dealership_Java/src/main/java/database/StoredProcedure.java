/**
 * 
 */
package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import enums.CD_Schema;
import enums.ErrorCodes;
import enums.SalesDeptSP;
import enums.ErrorCodes.ErrorHandler;
import utils.Log;
import utils.Logger;

/**
 * @author Steve Brown 
 * Handles/executes a stored procedure.
 * Inputs: 
 * 1. Database object.
 * 2. Database connection interface.
 * 3. Database connection. 
 * 
 * Returns a Stored Procedure object with Error code (if any) and result set if successful.
 */
public class StoredProcedure {

	private String query = "";
	private ResultSet rs = null;
	private Connection conn = null;
	private ErrorCodes eCode = ErrorCodes.NONE;

	private Log log = new Logger(false);
	private static final String objId = "<StoredProcedure>";

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
			log.logEntry(objId, "Error executing stored procedure: " + query);
		} 
		return this;
	}

	/*
	 * Returns a single value as a string from the executed SP.
	 * It's up to the caller to validate or change the string.
	 */
	public String getSingleValue() {
		String result = "";
		
		try {
			if(rs.first()) 
				result = rs.getString(1);
		} catch (SQLException e) {
			ErrorHandler.checkError(ErrorCodes.STORED_PROCEDURE, e.getMessage());
		}
		
		return result;
	}

	/*
	 * Returns a List from the executed SP.
	 * It's up to the caller to validate or change the List.
	 * Will throw SQLException if the column doesn't exist.
	 */
	public List<String> getListOfValues(String colOne) {
		
		List<String> result = new ArrayList<>();
		
		try {
			while(rs.next()) {
				result.add(rs.getString(colOne));
			}
		} catch (SQLException e) {
			ErrorHandler.checkError(ErrorCodes.STORED_PROCEDURE, e.getMessage());
		}
		
		return result;
	}
	
	/*
	 * Returns a ConcurrentHashMap (Likely that we'll use the result for threads) from the executed SP.
	 * It's up to the caller to validate or change the List.
	 * Will throw SQLException if the column doesn't exist.
	 */
	public ConcurrentHashMap<String, String> getMapOfValues(String colOne, String colTwo) {
		
		ConcurrentHashMap<String, String> result = new ConcurrentHashMap<>();
		
		try {
			while(rs.next()) {
				result.put(rs.getString(colOne), rs.getString(colTwo));
			}
		} catch (SQLException e) {
			ErrorHandler.checkError(ErrorCodes.STORED_PROCEDURE, e.getMessage());
		}
		
		return result;
	}
	
	/*
	 * Returns a result set from the executed SP.
	 */
	public ResultSet getRs() {
		return rs;
	}

	public ErrorCodes errorCode() {
		return eCode;
	}	
	
	/*
	 * Builds a string that can be used as a callable statement.
	 * 
	 * Build is a static method called with the inputs below.
	 * 
	 * The statement is parsed recursively until all the elements are exhausted.
	 * 
	 * Inputs:
	 * 		1. ArrayList<String> elements
	 * 			A list of values to use as inputs to the statement.
	 * 			The "regex" part of the statement is replaced by the next element in the list.
	 * 		2. StoredProcedures sp
	 * 			The name (value) of the stored procedure.
	 */
	public static class QueryBuilder{
				
		private static String parseStatement(ArrayList<String> elements, String statement) {
	
			Pattern pattern = Pattern.compile("regex");
			Matcher match = pattern.matcher(statement);
		
			if(match.find() && !elements.isEmpty()) {			// TODO - Error handler
				String s = match.replaceFirst(elements.get(0));
				elements.remove(0);
				statement = parseStatement(elements, s);			
			}
						
			return statement;
		}

		private static String parseStatement(String element, String statement) {
			
			Pattern pattern = Pattern.compile("regex");
			Matcher match = pattern.matcher(statement);
		
			if(match.find() && element != null) {			// TODO - Error handler
				String s = match.replaceFirst(element);
				statement = parseStatement(element, s);			
			}
						
			return statement;
		}
		public static String build(ArrayList<String> elements, String stmnt) {
			
			String query = parseStatement(elements, stmnt);
						
			return query;
		}

		public static String build(String element, String stmnt) {
			String query = parseStatement(element, stmnt);
			
			return query;
		}
		
	}
}
