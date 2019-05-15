/**
 * 
 */
package dao;

import java.sql.Connection;

import database.StoredProcedure;

/**
 * @author Steve Brown
 * 
 *  
 */
public interface DatabaseDAO extends DBProperty{
			
	Connection getDbConnection();
		
	StoredProcedure executeSP(String query);		
}
