package DocumentClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.max;

public class TextVector implements Serializable {
    public HashMap<String, Integer> rawVector;

    public TextVector() {
        this.rawVector = new HashMap<>();
    }

//    public abstract Set<Map.Entry<String, Double>> getNormalizedVectorEntrySet();
//
//    public abstract void normalize(DocumentCollection dc);
//
//    public abstract double getNormalizedFrequency(String word);

    public Set<Map.Entry<String, Integer>> getRawVectorEntrySet() {
        return this.rawVector.entrySet();
    }

    public void add(String word) {
        if (this.rawVector.containsKey(word)) {
            this.rawVector.put(word, this.rawVector.get(word) + 1);
        } else {
            this.rawVector.put(word, 1);
        }
    }

    public boolean contains(String word) {
        return this.rawVector.containsKey(word);
    }

    public int getRawFrequency(String word) {
        return this.rawVector.get(word);
    }

    public int getTotalWordCount() {
        int wordCount = 0;
        for (Integer i : this.rawVector.values()) {
            wordCount += i;
        }
        return wordCount;
    }

    public int getDistinctWordCount() {
        return this.rawVector.keySet().size();
    }

    public int getHighestRawFrequency() {
        if (this.rawVector.size() == 0) {
            return 0;
        }
        return max(this.rawVector.values());
    }

    public String getMostFrequentWord() {
        if (this.rawVector.size() == 0) {
            return "";
        }
        int maxCount = this.getHighestRawFrequency();
        for (Map.Entry<String, Integer> m : this.getRawVectorEntrySet()) {
            if (maxCount == m.getValue()) {
                return m.getKey();
            }
        }
        return "";
    }

//    public double getL2Norm() {
//
//    }

//    public ArrayList<Integer> findClosestDocuments(DocumentCollection documents, DocumentDistance distanceAlg) {
//
//    }
}
