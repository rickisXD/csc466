package DocumentClasses;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.max;

public class TextVector {
    public HashMap<String, Integer> rawVector;

    public void TextVector() {
        this.rawVector = new HashMap<>();
    }

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
        return max(this.rawVector.values());
    }

    public String getMostFrequentWord() {
        int maxCount = this.getHighestRawFrequency();
        for (Map.Entry<String, Integer> m : this.getRawVectorEntrySet()) {
            if (maxCount == m.getValue()) {
                return m.getKey();
            }
        }
        return "";
    }
}
