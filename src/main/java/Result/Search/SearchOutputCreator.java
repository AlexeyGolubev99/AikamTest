package Result.Search;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchOutputCreator {
    public static void createCriteriaOutput(OutputCriteria outputCriteria, ResultSet resultSet, CriteriaType
            criteriaType) throws SQLException {
        SearchResult searchResult = new SearchResult();
        searchResult.setCriteria(criteriaType);
        while (resultSet.next()) {
            CriteriaResult criteriaResult = new CriteriaResult(resultSet.getString("lastName"),
                    resultSet.getString("firstName"));
            searchResult.getResults().add(criteriaResult);
        }
        outputCriteria.getResults().add(searchResult);
    }

    public static void createBadCustomersOutput(OutputCriteria outputCriteria, ResultSet resultSet, CriteriaType
            criteriaType) throws SQLException {
        SearchResult searchResult = new SearchResult();
        searchResult.setCriteria(criteriaType);
        int minimalCounts = 0, numOfPrinted = 0;
        boolean firstCustomerFlag = true;
        while (resultSet.next()) {
            if (firstCustomerFlag) {
                minimalCounts = resultSet.getInt("counts");
                firstCustomerFlag = false;
            }
            if (minimalCounts == resultSet.getInt("counts") && numOfPrinted != criteriaType.getBadCustomers()) {
                CriteriaResult criteriaResult = new CriteriaResult(resultSet.getString("lastName"),
                        resultSet.getString("firstName"));
                searchResult.getResults().add(criteriaResult);
            }
        }
        outputCriteria.getResults().add(searchResult);
    }
}
