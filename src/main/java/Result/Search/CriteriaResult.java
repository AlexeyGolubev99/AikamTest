package Result.Search;

public class CriteriaResult {
    private final String lastName;
    private final String firstName;

    public CriteriaResult(String lastName, String firstName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
