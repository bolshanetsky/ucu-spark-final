package football.businesslogic.enrichment;

import football.businesslogic.models.FootballTime;
import football.listeners.RegisterUDF;
import football.enums.ColumnTypes;
import lombok.extern.log4j.Log4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.api.java.UDF1;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import static org.apache.spark.sql.functions.callUDF;
import static org.apache.spark.sql.functions.col;

/**
 * Add Time Half to the Dataset based on action time.
 */
@RegisterUDF(returnType = ColumnTypes.INTEGER)
@Component
@Log4j
public class TimeHalfEnricher implements FootballEnricher, UDF1<String,Integer>, Serializable {

    private static final String EVENT_TIME_COLUMN_NAME = "eventTime";
    private static final String TIME_HALF_COLUMN_NAME = "Time_Half";

    @Override
    public Integer call(String eventTime) throws Exception {
        return new FootballTime(eventTime).getMatchHalf();
    }

    @Override
    public Dataset enrich(Dataset dataset) {
        return dataset.withColumn(TIME_HALF_COLUMN_NAME, callUDF(TimeHalfEnricher.class.getName(),
                (col(EVENT_TIME_COLUMN_NAME))));
    }
}
