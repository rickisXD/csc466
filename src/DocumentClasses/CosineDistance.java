package DocumentClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CosineDistance implements DocumentDistance{
    public double findDistance(TextVector query, TextVector document, DocumentCollection dc) {
        ArrayList<String> queryWords = new ArrayList<>(query.rawVector.keySet());
        queryWords.retainAll(document.rawVector.keySet());
        double queryNormalizer = 0;
        for (Map.Entry<String, Double> entry : query.getNormalizedVectorEntrySet()) {
            queryNormalizer += Math.pow(entry.getValue(), 2);
        }
        queryNormalizer = Math.sqrt(queryNormalizer);
        double documentNormalizer = 0;
        for (Map.Entry<String, Double> entry : document.getNormalizedVectorEntrySet()) {
            documentNormalizer += Math.pow(entry.getValue(), 2);
        }
        documentNormalizer = Math.sqrt(documentNormalizer);
//Remember that it is possible that a query word does not appear in any document. In this case, set the inverse document frequency to 0.
        double cosineDistance = 0;
        for (String word : queryWords) {
            cosineDistance += query.getNormalizedFrequency(word) * document.getNormalizedFrequency(word);
        }
        return cosineDistance / (queryNormalizer * documentNormalizer);
    }
}
