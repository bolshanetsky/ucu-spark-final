package football;

import football.businesslogic.interfaces.DataProcessor;
import football.businesslogic.interfaces.DatasetLoader;
import org.apache.spark.sql.Dataset;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by bolshanetskyi on 11.12.17.
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Conf.class);

        DataProcessor businessLogic = context.getBean(DataProcessor.class);
        DatasetLoader dataLoader = context.getBean(DatasetLoader.class);

        Dataset dataset = dataLoader.load();
        dataset = businessLogic.runAllEnrichments(dataset);
        dataset = businessLogic.runAllValidations(dataset);

        /** work with prepared data set **/
    }
}
