package database;

import java.sql.Connection;
import java.util.Properties;

import enums.ErrorCodes;

public interface DbConnectionInterface {
	ErrorCodes connect(Properties dbProp);
	Connection connection();
}
