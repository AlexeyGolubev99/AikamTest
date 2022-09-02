package Result.Stat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class StatOutputCreator {
    public static OutputStat createStatOutput(ResultSet resultSet, Stat stat) throws SQLException, ParseException {
        OutputStat resultForStat = new OutputStat();
        while (resultSet.next()) {
            String curName = resultSet.getString("lastName") + " " +
                    resultSet.getString("firstName");
            boolean flagToAddConsumer = true;
            Consumer curConsumer = null;
            for (Consumer consumer : resultForStat.getCustomers()) {
                if (consumer.getName().equals(curName)) {
                    curConsumer = consumer;
                    flagToAddConsumer = false;
                    break;
                }
            }
            if (flagToAddConsumer) {
                curConsumer = new Consumer(curName);
                resultForStat.getCustomers().add(curConsumer);
            }
            String curPurchase = resultSet.getString("productName");
            boolean flagToAddProduct = true;
            for (Purchases purchase : curConsumer.getPurchases()) {
                if (purchase.getName().equals(curPurchase)) {
                    flagToAddProduct = false;
                    purchase.setExpenses(purchase.getExpenses() + resultSet.getInt("price"));
                    break;
                }
            }
            if (flagToAddProduct) {
                Purchases purchase = new Purchases(curPurchase, resultSet.getInt("price"));
                curConsumer.getPurchases().add(purchase);
            }
        }

        findNumberOfDays(resultForStat, stat);
        findExpenses(resultForStat);

        for (Consumer consumer : resultForStat.getCustomers()) {
            Collections.sort(consumer.getPurchases());
        }
        Collections.sort(resultForStat.getCustomers());

        return resultForStat;
    }

    private static void findNumberOfDays(OutputStat resultForStat, Stat stat) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = df.parse(stat.getStartDate());
        Date date2 = df.parse(stat.getEndDate());
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        cal2.add(Calendar.DAY_OF_MONTH, 1);
        int numberOfDays = 0;
        while (cal1.before(cal2)) {
            if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
                    && (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                numberOfDays++;
            }
            cal1.add(Calendar.DATE, 1);
        }
        resultForStat.setTotalDays(numberOfDays);
    }

    private static void findExpenses(OutputStat resultForStat) {
        for (Consumer consumer : resultForStat.getCustomers()) {
            for (Purchases purchase : consumer.getPurchases()) {
                consumer.setTotalExpenses(consumer.getTotalExpenses() + purchase.getExpenses());
            }
            resultForStat.setTotalExpenses(resultForStat.getTotalExpenses() + consumer.getTotalExpenses());
        }
        resultForStat.setAvgExpenses((float) resultForStat.getTotalExpenses() / (float)
                resultForStat.getCustomers().size());
    }

}
