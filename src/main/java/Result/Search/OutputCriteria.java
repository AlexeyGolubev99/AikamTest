package Result.Search;

import Result.Output;

import java.util.ArrayList;

public class OutputCriteria extends Output {
    private ArrayList<SearchResult> results;

    public OutputCriteria(String type) {
        super(type);
        results = new ArrayList<>();
    }

    public ArrayList<SearchResult> getResults() {
        return results;
    }
}
