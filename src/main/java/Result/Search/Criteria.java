package Result.Search;

import java.util.ArrayList;

public class Criteria {
    private ArrayList<CriteriaType> criterias;

    public Criteria() {
        criterias = new ArrayList<>();
    }

    public ArrayList<CriteriaType> getCriterias() {
        return criterias;
    }
}
