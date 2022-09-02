package Result.Search;

public class CriteriaType {
    private String lastName;
    private String productName;
    private Integer minTimes;
    private Integer badCustomers;
    private Integer minExpenses;
    private Integer maxExpenses;

    public Integer getBadCustomers() {
        return badCustomers;
    }

    public Integer getMaxExpenses() {
        return maxExpenses;
    }

    public Integer getMinExpenses() {
        return minExpenses;
    }

    public Integer getMinTimes() {
        return minTimes;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProductName() {
        return productName;
    }
}
