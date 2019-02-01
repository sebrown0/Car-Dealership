package spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class SparkDf {
	
	private Dataset<Row> dataFrame;

	public Dataset<Row> getDataFrame() {
		return dataFrame;
	}

	public void setDataFrame(Dataset<Row> dataFrame) {
		this.dataFrame = dataFrame;
	}
	
}
