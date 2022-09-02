package Result.Stat;

import Result.Output;

public class OutputError extends Output {
    private final String massage;

    public OutputError(String type, String massage) {
        super(type);
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }
}
