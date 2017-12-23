package football.configuration;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.*;


@Configuration
@Profile(Const.DEV)
public class DevConfig {

    @Bean
    public SparkConf sparkConf(){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("football_dev");
        sparkConf.setMaster("local[*]");
        return sparkConf;
    }
}
