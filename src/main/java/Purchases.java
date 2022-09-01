public class Purchases implements Comparable<Purchases> {
    private String name;
    private int expenses;

    public Purchases(String name, int expenses) {
        this.name = name;
        this.expenses = expenses;
    }

    public int getExpenses() {
        return expenses;
    }

    public String getName() {
        return name;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    @Override
    public int compareTo(Purchases product) {
        int compareExpenses = product.getExpenses();
        return compareExpenses - this.expenses;
    }
}
