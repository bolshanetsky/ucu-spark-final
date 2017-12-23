package football.listeners;

import football.enums.ColumnTypes;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.api.java.UDF2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UdfRegistrationApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private SQLContext sqlContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Collection<Object> udfObjects = context.getBeansWithAnnotation(RegisterUDF.class).values();
        for (Object udfObject : udfObjects) {
            ColumnTypes returnType = udfObject.getClass().getAnnotation(RegisterUDF.class).returnType();
            int udfArguments = udfObject.getClass().getAnnotation(RegisterUDF.class).arguments();
            switch (udfArguments) {
                case 1: sqlContext.udf().register(udfObject.getClass().getName(), (UDF1<?, ?>) udfObject, ColumnTypes
                        .convertToSparkType(returnType));
                break;
                case 2: sqlContext.udf().register(udfObject.getClass().getName(), (UDF2<?, ?, ?>) udfObject, ColumnTypes
                        .convertToSparkType(returnType));
                break;
            }
        }
    }
}
