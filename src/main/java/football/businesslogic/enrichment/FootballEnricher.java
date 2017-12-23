package football.businesslogic.enrichment;

import org.apache.spark.sql.Dataset;

/**
 * Created by bolshanetskyi on 15.12.17.
 */
public interface FootballEnricher {

    Dataset enrich(Dataset dataset);
}
