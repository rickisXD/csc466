package DocumentClasses;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class QueryVector extends TextVector{

    private HashMap<String, Double> normalizedVector = new HashMap<String, Double>();


    public Set<Map.Entry<String, Double>> getNormalizedVectorEntrySet() {
        return this.normalizedVector.entrySet();
    }

    public void normalize(DocumentCollection dc) {
        for (String word : this.rawVector.keySet()) {
            double numOccurrencesInDc = dc.getDocumentFrequency(word);
            double frequency = this.rawVector.get(word);
            double normalized;
            if (numOccurrencesInDc == 0) {
                normalized = 0.0;
            } else {
                normalized = (0.5 + 0.5 * (frequency / (double) this.getHighestRawFrequency())) *
                    (Math.log((double) dc.getSize() / numOccurrencesInDc) / Math.log(2));
            }
            this.normalizedVector.put(word, normalized);
        }
    }

    public double getNormalizedFrequency(String word) {
        return this.normalizedVector.get(word);
    }
}
