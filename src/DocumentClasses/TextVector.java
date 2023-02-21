package DocumentClasses;

import java.io.Serializable;
import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.max;
import static java.util.Collections.sort;

public abstract class TextVector implements Serializable {
    public HashMap<String, Integer> rawVector;

    public TextVector() {
        this.rawVector = new HashMap<>();
    }

    public abstract Set<Map.Entry<String, Double>> getNormalizedVectorEntrySet();

    public abstract void normalize(DocumentCollection dc);

    public abstract double getNormalizedFrequency(String word);

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
        if (!this.rawVector.containsKey(word)) {
            return 0;
        }
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
        return this.rawVector.size();
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

    public double getL2Norm() {
        double total = 0;
        for (String word : this.rawVector.keySet()) {
            total += Math.pow(getNormalizedFrequency(word), 2);
        }
        return Math.sqrt(total);
    }

    public ArrayList<Integer> findClosestDocuments(DocumentCollection documents, DocumentDistance distanceAlg) {
        ArrayList<Map.Entry<Integer, Double>> documentDistances = new ArrayList<>();
        for (Map.Entry<Integer, TextVector> doc : documents.getEntrySet()) {
            int documentId = doc.getKey();
            TextVector document = doc.getValue();
            if (document.rawVector.size() == 0) {
                documentDistances.add(Map.entry(documentId, 0.0));
            } else {
                documentDistances.add(Map.entry(documentId, distanceAlg.findDistance(this, document, documents)));
            }
        }
        sort(documentDistances, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        return (ArrayList<Integer>) documentDistances.stream().limit(20).map(Map.Entry::getKey).collect(Collectors.toList());
    }
}