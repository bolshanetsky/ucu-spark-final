package football.aop;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.apache.spark.sql.Dataset;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static football.configuration.Const.DEV;

/**
 * Prints Dataset size and content after each method returning Dataset.
 */
@Aspect
@Component
@Log4j
@Profile(DEV)
public class DatasetPrinterAspect {

    @SneakyThrows
    @Around("execution(org.apache.spark.sql.Dataset football.businesslogic.implementation.*.*(..))")
    public Dataset print(ProceedingJoinPoint pjp) {
        Dataset dataset = (Dataset) pjp.proceed();
        log.warn("METHOD: " + pjp.getSignature().toShortString());
        log.warn("DATASET SIZE: " + dataset.count());
        dataset.show();
        return dataset;
    }
}
