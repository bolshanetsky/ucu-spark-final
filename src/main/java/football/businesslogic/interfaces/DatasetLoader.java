package football.businesslogic.interfaces;

import org.apache.spark.sql.Dataset;

/**
 * Created by bolshanetskyi on 23.12.17.
 */
public interface DatasetLoader {
    Dataset load();
}
