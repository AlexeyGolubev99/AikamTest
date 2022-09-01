import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonHandler {

    public static Stat parseJSONStat(InputData data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(data.getInputFileName()), Stat.class);
    }

    public static Criteria parseJSONCriteria(InputData data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(data.getInputFileName()), Criteria.class);
    }
}
