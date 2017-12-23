package football.businesslogic.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by bolshanetskyi on 15.12.17.
 */
@Data
@AllArgsConstructor
public class FootballTime {
    public static final String TIME_SEPARATOR = ":";

    private int minutes;
    private int seconds;

    public FootballTime(String stringValue) {
        String[] values = stringValue.split(TIME_SEPARATOR);
        setMinutes(Integer.parseInt(values[0]));
        setSeconds(Integer.parseInt(values[1]));
    }

    public boolean validate() {
        return minutes < 100;
    }

    public int getMatchHalf() {
        return minutes < 46 ? 1 : 2;
    }
}
