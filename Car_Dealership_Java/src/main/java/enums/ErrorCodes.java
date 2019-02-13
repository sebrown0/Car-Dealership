package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Brown
 * Error handling object. The string value of the error codes are used as part
 * of a regex. The regex tries to match/find the error in the error message.
 * i.e.  duplicate for DUPLICATE_ENTRY.
 * 
 * Some of the codes are used as messages well as errors in the program.
 */
public enum ErrorCodes {

	NONE("none"), 								// No error.
	DUPLICATE_ENTRY("#duplicate entry"), 		// Duplicate entry in DB (PK violation).
	DB_CONN("db_conn"), 						// Error connecting to the DB.
	NO_FILE("no_file"),							// No file found. Not necessarily an error, also a message.
	FILE_ERROR("file_error"),					// Error when performing a file operation.
	FILE_DELETE("error deleting file"),			// Error occurred when trying to delete a file.
	DF_ERROR("table or column doesn't #exist"),	// Error occurred whilst trying to create a data frame probable that tbl/col doesn't exist.
	STORED_PROCEDURE("SQL syntax"),				// Error trying to execute a store procedure.
	UNKNOWN_ERROR("unknown_error");				// We don't know what happend.
	
	private String eCode;
	
	private ErrorCodes(String ec) {
		this.eCode = ec;
	}
	
	public String eCode() {
		return this.eCode;
	}
		
	public static class ErrorHandler {
		//TODO - Always more error handling to do.
		public static ErrorCodes checkError(ErrorCodes suspectedError, String errorMsg) {
			ErrorCodes eCode = ErrorCodes.UNKNOWN_ERROR;
			
			// Switch not necessary if every error is parsed. 
			switch (suspectedError) {
			case DB_CONN:
				System.out.println("DB Connection errror.");
				break;
			
			case FILE_ERROR:
				System.out.println("File error");
				break;
				
			case STORED_PROCEDURE:
				System.out.println("Couldn't execute the store procedure.");
				break;
			
			case DUPLICATE_ENTRY:
				eCode = parseErrorMsg(suspectedError, errorMsg);
				break;

			case DF_ERROR:
				System.out.println("DF_ERROR");
				eCode = parseErrorMsg(suspectedError, errorMsg);
				break;
				
			default:
				break;
			}
			return eCode;
		}
		
		private static ErrorCodes parseErrorMsg(ErrorCodes suspectedError, String errorMsg) {

			// Get the part of the error code that might be in the error message.
			Pattern errorCodePattern = Pattern.compile("#(\\S+)");
			Matcher errorCodeMatch = errorCodePattern.matcher(suspectedError.eCode());
			
			if(errorCodeMatch.find()) {
				String regEx = errorCodeMatch.group(1);
				System.out.println("Looking for: " + regEx);

				// Use the string value of the Enum error message as the regex
				// i.e. we're only looking to see if an expected word occurs in the error message.
				errorCodePattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
				errorCodeMatch = errorCodePattern.matcher(errorMsg);
				
				if(errorCodeMatch.find()) {
					System.out.println("Error Type -->> " + suspectedError);
					System.out.println("Error MSG -->>" + errorMsg);
					return suspectedError;
				}
			}
			
			// Couldn't find an obvious error.
			System.out.println("Error Type -->> " + ErrorCodes.UNKNOWN_ERROR);
			System.out.println("Error MSG -->>" + errorMsg);
			return ErrorCodes.UNKNOWN_ERROR;
		}
		
	}
}
