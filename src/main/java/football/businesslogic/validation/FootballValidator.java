package football.businesslogic.validation;

import org.apache.spark.sql.Dataset;

/**
 * Created by bolshanetskyi on 15.12.17.
 */
public interface FootballValidator {

    Dataset validate(Dataset dataset);
}
