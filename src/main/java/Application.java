import Result.Output;
import Result.Stat.OutputError;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        InputData data = new InputData(args);
        try {
            JdbcConnector connector = new JdbcConnector();
            Output output = null;
            if (data.getOperationType() == OperationType.STAT) {
                output = connector.statSearch(JsonHandler.parseJSONStat(data));
            } else {
                output = connector.criteriaSearch(JsonHandler.parseJSONCriteria(data));
            }
            FileWriter.write(output, data.getOutputFileName());
        } catch (Exception e) {
            OutputError error = new OutputError("error", e.getMessage());
            FileWriter.write(error, data.getOutputFileName());
        }
    }
}
