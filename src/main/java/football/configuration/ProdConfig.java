package football.configuration;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.*;

@Configuration
@Profile(Const.PROD)
public class ProdConfig {

    @Bean
    public SparkConf sparkConf() {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("football_prod");
        sparkConf.setMaster("local[*]");
        return sparkConf;
    }
}
