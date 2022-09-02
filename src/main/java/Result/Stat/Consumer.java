package Result.Stat;

import java.util.ArrayList;

public class Consumer implements Comparable<Consumer> {
    private String name;
    private ArrayList<Purchases> purchases;
    private int totalExpenses;

    public Consumer(String name) {
        purchases = new ArrayList<Purchases>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Purchases> getPurchases() {
        return purchases;
    }

    public int getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(int totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    @Override
    public int compareTo(Consumer consumer) {
        int compareExpenses = consumer.getTotalExpenses();
        return compareExpenses - this.totalExpenses;
    }
}
