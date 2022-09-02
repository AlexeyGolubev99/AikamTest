package Result.Search;

import java.util.ArrayList;

public class SearchResult {
    private CriteriaType criteria;
    private ArrayList<CriteriaResult> results;

    public CriteriaType getCriteria() {
        return criteria;
    }

    public void setCriteria(CriteriaType criteria) {
        this.criteria = criteria;
    }

    public SearchResult() {
        results = new ArrayList<>();
    }

    public ArrayList<CriteriaResult> getResults() {
        return results;
    }
}
