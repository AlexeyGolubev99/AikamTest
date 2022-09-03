package Result;

public class OutputError extends Output {
    private final String message;

    public OutputError(String type, String message) {
        super(type);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
