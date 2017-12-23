package football.businesslogic.implementation;

import football.businesslogic.enrichment.FootballEnricher;
import football.businesslogic.interfaces.DataProcessor;
import football.businesslogic.interfaces.DatasetLoader;
import football.businesslogic.validation.FootballValidator;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bolshanetskyi on 11.12.17.
 */
@Service
public class DataProcessorImpl implements DataProcessor {

    @Autowired
    private DatasetLoader datasetLoader;

    @Autowired
    private List<FootballEnricher> enrichers;

    @Autowired
    private List<FootballValidator> validators;

    @Override
    public Dataset<Row> runAllEnrichments(Dataset datasetToEnrich) {
        for (FootballEnricher enricher : enrichers) {
            datasetToEnrich = enricher.enrich(datasetToEnrich);
        }

        return datasetToEnrich;
    }

    @Override
    public Dataset runAllValidations(Dataset datasetToValidate) {
        for (FootballValidator validator : validators) {
            datasetToValidate = validator.validate(datasetToValidate);
        }

        return datasetToValidate;
    }

    @Override
    public Dataset process(){
        Dataset<Row> dataset = datasetLoader.load();
        dataset = this.runAllValidations(dataset);
        return this.runAllEnrichments(dataset);
    }
}
