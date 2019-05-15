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

import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import utils.logger.Log;
import utils.logger.Loggable;

/**
 * @author Steve Brown 
 * 
 * Handles/executes a stored procedure.
 * 
 * Inputs: 
 * 	1. Query: the query to run.
 * 	2. Log: the dealer's log.
 * 
 * Returns:
 *  1. Stored Procedure object with Error code (if any).
 *  2. A result set if successful.
 */
public class StoredProcedure implements Loggable{

	private String query = "";
	private ResultSet rs = null;
	private Connection conn = null;
	private ErrorCodes eCode = ErrorCodes.NONE;
	private Log log;

	public StoredProcedure(String query, Log log, Connection conn) {
		this.query = query;
		this.log = log;
		this.conn = conn;
	}

	/*
	 *  Check if there's a query to run.
	 */
	public StoredProcedure execute() {
		if(query != "") 
			runTheQuery();
		else
			eCode = ErrorCodes.STORED_PROCEDURE;

		return this;
	}

	/*
	 *  We have a query, so run it.
	 */
	private void runTheQuery() {
		try {
			CallableStatement stmt = conn.prepareCall(query);
			rs = stmt.executeQuery();
		} catch (SQLException e) {			
			eCode = ErrorHandler.checkError(ErrorCodes.STORED_PROCEDURE, e.getMessage(), log);
			log.logEntry(this, "Error executing stored procedure: " + query);
		}
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
			ErrorHandler.checkError(ErrorCodes.STORED_PROCEDURE, e.getMessage(), log);
		}
		return result;
	}

	/*
	 * Returns a List from the executed SP.
	 * It's up to the caller to validate or change the List.
	 */
	public List<String> getListOfValues(String colOne) {
		List<String> result = new ArrayList<>();
		
		try {
			while(rs.next()) 
				result.add(rs.getString(colOne));
		} catch (SQLException e) {
			ErrorHandler.checkError(ErrorCodes.STORED_PROCEDURE, e.getMessage(), log);
		}
		return result;
	}
	
	/*
	 * Returns a ConcurrentHashMap (Likely that we'll use the result for threads) from the executed SP.
	 * It's up to the caller to validate or change the List.
	 */
	public ConcurrentHashMap<String, String> getMapOfValues(String colOne, String colTwo) {
		ConcurrentHashMap<String, String> result = new ConcurrentHashMap<>();
		
		try {
			while(rs.next()) 
				result.put(rs.getString(colOne), rs.getString(colTwo));
		} catch (SQLException e) {
			ErrorHandler.checkError(ErrorCodes.STORED_PROCEDURE, e.getMessage(), log);
		}
		return result;
	}
	
	/*
	 * Returns a result set from the executed SP.
	 */
	public ResultSet getRs() {
		return rs;
	}

	/*
	 * Return any error code. None is the default.
	 */
	public ErrorCodes errorCode() {
		return eCode;
	}	
	
	/*
	 * Builds a string that can be used in a callable statement.
	 * 
	 * Build is a static method called with the inputs below.
	 * 
	 * The statement is parsed recursively until all the elements (parameters) are exhausted.
	 * 
	 * Inputs:
	 * 		1. ArrayList<String> stmntParameter
	 * 			A list of values to use as inputs to the statement.
	 * 			The "regex" part of the statement is replaced by the next parameter in the list.
	 * 		2. StoredProcedures sp
	 * 			The name (value) of the stored procedure.
	 */
	public static class QueryBuilder{ 
				
		/*
		 *  Recursively parses the statement replacing "regex" with the current parameter in the list.
		 */
		private static String parseStatementWithXpleParams(ArrayList<String> params, String statement) {
			Pattern pattern = Pattern.compile("regex");			
			Matcher match = pattern.matcher(statement);
		
			if(match.find() && !params.isEmpty()) {			
				String s = match.replaceFirst(params.get(0));
				params.remove(0);
				statement = parseStatementWithXpleParams(params, s);			
			}			
			return statement;
		}

		/*
		 *  Recursively parses the statement replacing "regex" with the "param".
		 */
		private static String parseStatementWithOneParam(String param, String statement) {
			Pattern pattern = Pattern.compile("regex");
			Matcher match = pattern.matcher(statement);
		
			if(match.find() && param != null) {		
				String s = match.replaceFirst(param);
				statement = parseStatementWithOneParam(param, s);			
			}
			return statement;
		}
		
		/*
		 *  Build a query/statement for the passed statement with multiple parameters.
		 */
		public static String build(ArrayList<String> stmntParameter, String stmnt) {
			return parseStatementWithXpleParams(stmntParameter, stmnt);
		}

		/*
		 *  Build a query/statement for the passed statement with the one parameter.
		 */
		public static String build(String element, String stmnt) {
			return parseStatementWithOneParam(element, stmnt);
		}
	}
}
