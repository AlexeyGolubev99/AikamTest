public class InputData {
    private OperationType operationType;
    private String inputFileName;
    private String outputFileName;

    public InputData(String[] args) {
        for (String argument : args) {
            if (operationType == null) {
                if (argument.equals("search")) {
                    operationType = OperationType.SEARCH;
                }
                if (argument.equals("stat")) {
                    operationType = OperationType.STAT;
                }
            }
            if (argument.contains(".json")) {
                if (inputFileName == null) {
                    inputFileName = argument;
                } else if (outputFileName == null) {
                    outputFileName = argument;
                } else {
                    throw new IllegalArgumentException("Wrong number of arguments");
                }
            }
        }
        if (operationType == null || inputFileName == null || outputFileName == null) {
            throw new IllegalArgumentException("Wrong arguments");
        }
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public OperationType getOperationType() {
        return operationType;
    }
}
