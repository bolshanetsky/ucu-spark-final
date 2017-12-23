package football.enums;

import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by bolshanetskyi on 12.12.17.
 */
public enum ColumnTypes {
    STRING("string", DataTypes.StringType), INTEGER("integer", DataTypes.IntegerType)
    , BOOLEAN("boolean", DataTypes.BooleanType);

    private String val;
    private DataType sparkTypeAnalog;
    private DataType javaType;

    ColumnTypes(String stringValue, DataType sparkTypeAnalog) {
        this.val = stringValue;
        this.sparkTypeAnalog = sparkTypeAnalog;
    }

    String getValue() {
        return this.val;
    }

    public DataType getSparkTypeAnalog() {
        return this.sparkTypeAnalog;
    }

    public static DataType convertToSparkType(ColumnTypes type) {
        for (ColumnTypes columnType: ColumnTypes.values()) {
            if (columnType.equals(type)) {
                return columnType.getSparkTypeAnalog();
            }
        }

        throw new IllegalArgumentException("Type: " + type + " is not supported");
    }

    public static ColumnTypes getColumnTypeByName(String type) {
        for (ColumnTypes columnType: ColumnTypes.values()) {
            if (columnType.getValue().equals(type)) {
                return columnType;
            }
        }

        throw new IllegalArgumentException("Type: " + type + "is not supported");
    }
}
