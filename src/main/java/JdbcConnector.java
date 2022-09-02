import Result.Search.Criteria;
import Result.Search.CriteriaType;
import Result.Search.OutputCriteria;
import Result.Search.SearchOutputCreator;
import Result.Stat.OutputStat;
import Result.Stat.Stat;
import Result.Stat.StatOutputCreator;

import java.sql.*;
import java.text.ParseException;

public class JdbcConnector {

    private Connection startConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/test_market";
        String username = "postgres";
        String password = "2816alex";
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    public OutputCriteria criteriaSearch(Criteria criteria) throws SQLException, ClassNotFoundException {
        try (Connection connection = startConnection()) {
            Statement statement = connection.createStatement();
            OutputCriteria outputCriteria = new OutputCriteria("search");
            for (CriteriaType curCriteriaType : criteria.getCriterias()) {
                if (curCriteriaType.getLastName() != null) {
                    String request = "SELECT \"lastName\", \"firstName\" FROM \"Consumer\" WHERE \"lastName\" = '" +
                            curCriteriaType.getLastName() + "'";
                    makeRequest(request, statement, outputCriteria, curCriteriaType);
                }
                if (curCriteriaType.getProductName() != null) {
                    String request = "SELECT DISTINCT \"lastName\", \"firstName\" FROM \"Purchases\" JOIN " +
                            "\"Consumer\" ON consumer_id = \"Consumer\".id JOIN \"Product\" ON product_id = " +
                            "\"Product\".id WHERE \"productName\" = '" + curCriteriaType.getProductName() +
                            "' GROUP BY \"Consumer\".id HAVING COUNT(\"Consumer\".id) >= " +
                            curCriteriaType.getMinTimes();
                    makeRequest(request, statement, outputCriteria, curCriteriaType);
                }
                if (curCriteriaType.getMinExpenses() != null && curCriteriaType.getMaxExpenses() != null) {
                    String request = "SELECT DISTINCT \"lastName\", \"firstName\" FROM \"Purchases\" JOIN " +
                            "\"Consumer\" ON consumer_id = \"Consumer\".id JOIN \"Product\" ON product_id = " +
                            "\"Product\".id GROUP BY \"Consumer\".id HAVING SUM(price) >= " +
                            curCriteriaType.getMinExpenses() + " AND SUM(price) <= " + curCriteriaType.getMaxExpenses();
                    makeRequest(request, statement, outputCriteria, curCriteriaType);
                }
                if (curCriteriaType.getBadCustomers() != null) {
                    String request = "SELECT \"lastName\", \"firstName\", COUNT(*) AS counts FROM " +
                            "\"Purchases\" JOIN \"Consumer\" ON consumer_id = \"Consumer\".id GROUP BY " +
                            "\"Consumer\".id ORDER BY COUNT(*) LIMIT " + curCriteriaType.getBadCustomers();
                    ResultSet resultSet = statement.executeQuery(request);
                    SearchOutputCreator.createBadCustomersOutput(outputCriteria, resultSet, curCriteriaType);
                }
            }
            return outputCriteria;
        }
    }

    private void makeRequest(String request, Statement statement, OutputCriteria outputCriteria,
                             CriteriaType curCriteriaType) throws SQLException {
        ResultSet resultSet = statement.executeQuery(request);
        SearchOutputCreator.createCriteriaOutput(outputCriteria, resultSet, curCriteriaType);
    }

    public OutputStat statSearch(Stat stat) throws SQLException, ClassNotFoundException, ParseException {
        Connection connection = startConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Purchases\" JOIN \"Consumer\" ON consumer_id "
                + "= \"Consumer\".id JOIN \"Product\" ON product_id = \"Product\".id WHERE date >= '" +
                stat.getStartDate() + "' and date <= '" + stat.getEndDate() + "' AND EXTRACT(DOW FROM date) != 6 AND " +
                "EXTRACT(DOW FROM date) != 0");
        return StatOutputCreator.createStatOutput(resultSet, stat);
    }
}
