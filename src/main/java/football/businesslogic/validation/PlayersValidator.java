package football.businesslogic.validation;

import football.listeners.RegisterUDF;
import football.enums.ColumnTypes;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.api.java.UDF1;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import static org.apache.spark.sql.functions.*;

/**
 * Verifies if player exist in player configuration list.
 */
@RegisterUDF(returnType = ColumnTypes.BOOLEAN)
@Component
@Log4j
public class PlayersValidator implements FootballValidator, UDF1<String,Boolean>, Serializable {

    private static final String TEAMS_PROPERTIES_FILE = "teams.properties";
    private static final String TO_COLUMN_NAME = "to";
    private static final String FROM_COLUMN_NAME = "from";

    private Map<String, String> players;


    @PostConstruct
    @SneakyThrows
    private void loadPlayers() {
        Properties teams = PropertiesLoaderUtils.loadAllProperties(TEAMS_PROPERTIES_FILE);
        Map<String, String> players = new HashMap<>();
        teams.stringPropertyNames().stream().forEach(country -> {
                    Arrays.stream(teams.getProperty(country).split(","))
                            .forEach(player -> players.put(player, country));
                });
        this.players = players;
    }

    @Override
    public Boolean call(String playerName) throws Exception {
        if (players.containsKey(playerName)) {
            return true;
        }
        else {
            log.warn("Data Validation: Player name wasn't found - " + playerName);
            return false;
        }
    }

    @Override
    public Dataset validate(Dataset dataset) {
        return dataset.filter(callUDF(PlayersValidator.class.getName(), (col(TO_COLUMN_NAME))))
                .filter(callUDF(PlayersValidator.class.getName(), (col(FROM_COLUMN_NAME))));
    }
}
