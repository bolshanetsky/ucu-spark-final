package football.businesslogic.interfaces;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

/**
 * Created by bolshanetskyi on 23.12.17.
 */
public interface DataProcessor {
    Dataset process();

    Dataset<Row> runAllEnrichments(Dataset datasetToEnrich);

    Dataset runAllValidations(Dataset datasetToValidate);
}
