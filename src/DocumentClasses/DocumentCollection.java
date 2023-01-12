package DocumentClasses;

import java.io.Serializable;
import java.util.*;

public class DocumentCollection implements Serializable {
    public HashMap<Integer, TextVector> documents;
    private List<String> noiseWordArray = Arrays.asList("a", "about", "above", "all", "along",
            "also", "although", "am", "an", "and", "any", "are", "aren't", "as", "at",
            "be", "because", "been", "but", "by", "can", "cannot", "could", "couldn't",
            "did", "didn't", "do", "does", "doesn't", "e.g.", "either", "etc", "etc.",
            "even", "ever", "enough", "for", "from", "further", "get", "gets", "got", "had", "have",
            "hardly", "has", "hasn't", "having", "he", "hence", "her", "here",
            "hereby", "herein", "hereof", "hereon", "hereto", "herewith", "him",
            "his", "how", "however", "i", "i.e.", "if", "in", "into", "it", "it's", "its",
            "me", "more", "most", "mr", "my", "near", "nor", "now", "no", "not", "or", "on", "of", "onto",
            "other", "our", "out", "over", "really", "said", "same", "she",
            "should", "shouldn't", "since", "so", "some", "such",
            "than", "that", "the", "their", "them", "then", "there", "thereby",
            "therefore", "therefrom", "therein", "thereof", "thereon", "thereto",
            "therewith", "these", "they", "this", "those", "through", "thus", "to",
            "too", "under", "until", "unto", "upon", "us", "very", "was", "wasn't",
            "we", "were", "what", "when", "where", "whereby", "wherein", "whether",
            "which", "while", "who", "whom", "whose", "why", "with", "without",
            "would", "you", "your", "yours", "yes");

    public void DocumentCollection(String filePath) {
        this.documents = new HashMap<>();
        //reads the file that is specified as input and it uses the data in the file to populate the documents variable.
        // about 50 lines of code
    }

    public TextVector getDocumentById(int id) {
        return this.documents.get(id);
    }

    public double getAverageDocumentLength() {
        double numDocs = 0;
        double totalLength = 0;
        for (TextVector t : this.documents.values()) {
            numDocs += 1;
            totalLength += t.getTotalWordCount();
        }
        return totalLength / numDocs;
    }

    public int getSize() {
        return documents.values().size();
    }

    public Collection<TextVector> getDocuments() {
        return this.documents.values();
    }

    public Set<Map.Entry<Integer, TextVector>> getEntrySet() {
        return this.documents.entrySet();
    }

    public int getDocumentFrequency(String word) {
        int numDocs = 0;
        for (TextVector t : this.documents.values()) {
            if (t.contains(word)) {
                numDocs += 1;
            }
        }
        return numDocs;
    }

    private boolean isNoiseWord(String word) {
        return this.noiseWordArray.contains(word);
    }
}