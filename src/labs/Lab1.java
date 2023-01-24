package labs;

import DocumentClasses.DocumentCollection;
import DocumentClasses.TextVector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Lab1 {

    public static void main(String[] args) {
        DocumentCollection docs = new DocumentCollection("./files/documents.txt");
        String word = "";
        int frequency = 0;
        int distinctNumber = 0;
        int totalWordCount = 0;
        for (TextVector t : docs.getDocuments()) {
            if (t.getHighestRawFrequency() > frequency) {
                frequency = t.getHighestRawFrequency();
                word = t.getMostFrequentWord();
            }
            distinctNumber += t.getDistinctWordCount();
            totalWordCount += t.getTotalWordCount();
        }

        System.out.println("Word = " + word);
        System.out.println("Frequency = " + frequency);
        System.out.println("Distinct Number of Words = " + distinctNumber);
        System.out.println("Total word count = " + totalWordCount);

        try(ObjectOutputStream os = new ObjectOutputStream(new
                FileOutputStream(new File("./files/docvector")))){
            os.writeObject(docs);
        } catch(Exception e){
            System.out.println(e);
        }
    }
}