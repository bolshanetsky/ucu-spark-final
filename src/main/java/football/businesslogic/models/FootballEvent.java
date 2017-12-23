package football.businesslogic.models;

import lombok.Data;

/**
 * Created by bolshanetskyi on 14.12.17.
 */
@Data
public class FootballEvent {

    private String codeDescription;
    private int code;
    private String from;
    private String to;
    private String eventTime;
    private String stadium;
    private String startTime;


    public void setCode(String value) {
        this.code = Integer.parseInt(value);
    }
}
