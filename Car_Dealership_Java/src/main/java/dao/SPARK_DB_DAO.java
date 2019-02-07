package dao;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
/**
 * @author Steve Brown
 * Interface for DB.
 * We're using Postgres(not implemented yet) and MySql in this project
 */
public interface SPARK_DB_DAO {
	
	boolean writeDfToDBTable(Dataset<Row> df, String table) 
			throws  SQLException, BatchUpdateException, SQLIntegrityConstraintViolationException;
	
	boolean writeDfToDBTable(Dataset<Row> df) 
			throws  SQLException, BatchUpdateException, SQLIntegrityConstraintViolationException;

//	void dbConnect(); NOT USED AT PRESENT
//	SparkDAO readDfFromDB(); NOT USED AT PRESENT
}
