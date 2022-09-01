public class CriteriaType {
    private String lastName;
    private String productName;
    private int minTimes;
    private int badCustomers;
    private int minExpenses;
    private int maxExpenses;

    public int getBadCustomers() {
        return badCustomers;
    }

    public int getMaxExpenses() {
        return maxExpenses;
    }

    public int getMinExpenses() {
        return minExpenses;
    }

    public int getMinTimes() {
        return minTimes;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProductName() {
        return productName;
    }
}
