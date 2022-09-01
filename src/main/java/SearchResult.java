import java.util.ArrayList;

public class SearchResult {
    private final String type = "search";
    private ArrayList<OutputCriteria> results;

    public SearchResult() {
        results = new ArrayList<>();
    }
}
