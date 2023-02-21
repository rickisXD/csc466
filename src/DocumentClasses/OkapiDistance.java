package DocumentClasses;

import java.util.Map;

public class OkapiDistance implements DocumentDistance{

    public double findDistance(TextVector query, TextVector document, DocumentCollection dc) {
        double k1 = 1.2;
        double b = 0.75;
        double k2 = 100;
        double okapi = 0;
        for (Map.Entry<String, Integer> entry : query.getRawVectorEntrySet()) {
            String word = entry.getKey();
            double dcFreq = dc.getDocumentFrequency(word);
            double docFreq = document.getRawFrequency(word);
            double queryFreq = query.getRawFrequency(word);
            double docLength = document.rawVector.size();
            double avdl = dc.getAverageDocumentLength();
            okapi += (Math.log((dc.getSize() - dcFreq + 0.5) / (dcFreq + 0.5)) *
                    (((k1 + 1) * docFreq) / (k1 * (1 - b + (b * (docLength / avdl))) + docFreq)) *
                    (((k2 + 1) * queryFreq) / (k2 + queryFreq)));
        }
        return okapi;
    }
}
