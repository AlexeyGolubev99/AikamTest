import java.sql.*;
import java.text.ParseException;

public class jdbcConnector {

    /*public Output criteriaSearch(Criteria criteria) throws SQLException, ClassNotFoundException {
        Connection connection = startConnection();
        Statement statement = connection.createStatement();
        for (CriteriaType curCriteriaType : criteria.getCriterias()) {
            if (curCriteriaType.getLastName() != null) {
                String request = "SELECT * FROM \"Consumer\" WHERE \"lastName\" = '" + curCriteriaType.getLastName() + "'";
                ResultSet resultSet = statement.executeQuery(request);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(2));
                }
            }
        }
    }*/

    public OutputStat statSearch(Stat stat) throws SQLException, ClassNotFoundException, ParseException {
        Connection connection = startConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Purchases\" JOIN \"Consumer\" ON consumer_id " +
                "= \"Consumer\".id JOIN \"Product\" ON product_id = \"Product\".id WHERE date >= '" +
                stat.getStartDate() + "' and date <= '" + stat.getEndDate() + "' and EXTRACT(DOW FROM date) != 6 and " +
                "EXTRACT(DOW FROM date) != 0");
        return StatOutputCreator.createStatOutput(resultSet, stat);
    }

    private Connection startConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/test_market";
        String username = "postgres";
        String password = "2816alex";
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, username, password);
    }
}
