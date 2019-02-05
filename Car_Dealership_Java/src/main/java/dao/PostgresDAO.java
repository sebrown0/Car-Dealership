package dao;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
/**
 * @author Steve Brown
 * NOT COMPLETE OR USING AT THE MOMENT
 */
public class PostgresDAO implements DB_DAO{

	@Override
	public boolean writeDfToDBTable(Dataset<Row> df, String table)
			throws SQLException, BatchUpdateException, SQLIntegrityConstraintViolationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean writeDfToDBTable(Dataset<Row> df)
			throws SQLException, BatchUpdateException, SQLIntegrityConstraintViolationException {
		// TODO Auto-generated method stub
		return false;
	}



}
