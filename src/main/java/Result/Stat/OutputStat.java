package Result.Stat;

import Result.Output;
import Result.Stat.Consumer;

import java.util.ArrayList;

public class OutputStat extends Output {
    private int totalDays;
    private ArrayList<Consumer> customers;
    private int totalExpenses;
    private float avgExpenses;

    public OutputStat() {
        super("stat");
        customers = new ArrayList<>();
    }

    public int getTotalExpenses() {
        return totalExpenses;
    }

    public ArrayList<Consumer> getCustomers() {
        return customers;
    }

    public float getAvgExpenses() {
        return avgExpenses;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public void setTotalExpenses(int totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public void setAvgExpenses(float avgExpenses) {
        this.avgExpenses = avgExpenses;
    }
}
