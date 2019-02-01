package application;

import stock_update.Stock;
import stock_update.StockUpdate;



public class App {

	public static void main(String[] args) {

		// ++++++++++++++++++++++++++++++++++CHANGE THIS TO BE IN THE PATH++++++++++++++++++++++++++++++++++
		System.setProperty("hadoop.home.dir", "C:\\hadoop");
		System.out.println("Starting App...............");


		Stock stock = new StockUpdate();
		stock.readStockFile();
		


		System.out.println("Ending App...............");
	}

	// ----------------------------------- UNUSED BELOW -----------------------------------
/*	private void connMySql() {
		try {
			Connection conn = DriverManager.getConnection(
					MySqlConn.URL_AND_SCHEMA.value(), 
					MySqlConn.USERNAME.value(), 
					MySqlConn.PASSWORD.value());
			
			String query = "{ call " +  CD_Schema.SCHEMA.value() + ".`GetModelDetails`('Ford','Focus') }";
			CallableStatement stmt = conn.prepareCall(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("Model"
						));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
*/
	/*private static void dfUsingSpark() {
		Spark spark = new Spark("Spark1", "local", true);
		Dataset<Row> jdbcDF = spark.session().read()
				  .format("jdbc")
				  .option("url", MySqlConn.URL_AND_SCHEMA.value())
				  .option("dbtable", TableNames.EMPS.tblName())
				  .option("user", MySqlConn.USERNAME.value())
				  .option("password", MySqlConn.PASSWORD.value())
				  .load();
		jdbcDF.show();
		
	}*/
	
	private void connPostGres() {
	/*	SparkDAO spark = new SparkDAO("Spark1", "local", true);

		PostgresDAO dbDAO = new PostgresDAO("jdbc:postgresql://localhost/CarDealership", "org.postgresql.Driver",
				"postgres", "postgres");

		Dataset<Row> dfSpecCSV = new SparkDataFrame().getDataFrameFromSpecifiedCSV(spark.session(),
				"src/main/resources/amazonProducts.txt");
		dfSpecCSV.show();

		SparkDataFrame dfPeople = new SparkDfCSV(); // Create a new df to handle CSV
		dfPeople.createSparkDf(spark.session(), "src/main/resources/data/people.txt");
		dfPeople.getDataFrame().show();
		dfPeople.getDataFrame().write().mode(SaveMode.Overwrite).jdbc(dbDAO.getDbProp().getProperty("url"), "customer", dbDAO.getDbProp());
		*/		
	}

//	dfUsingSpark();
	
//	DB_DAO mySqlDAO = new MySqlDAO(url, "", username, password);
//	mySqlDAO.db_connect();

//	InferCSVSchema csv = new InferCSVSchema();
//	csv.printSchema(spark);
}