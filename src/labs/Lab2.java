package labs;

import DocumentClasses.CosineDistance;
import DocumentClasses.DocumentCollection;
import DocumentClasses.TextVector;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

public class Lab2 {
    public static DocumentClasses.DocumentCollection documents;
    public static DocumentClasses.DocumentCollection queries;

    public static void main(String[] args) {
//        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(new File("./files/docvector")))) {
//            documents = (DocumentCollection)
//                    is.readObject();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        documents = new DocumentCollection("./files/documents.txt", "document");
        queries = new DocumentCollection("./files/queries.txt", "query");
        documents.normalize(documents);
        queries.normalize(documents);
        for (Map.Entry<Integer, TextVector> entry : queries.getEntrySet()) {
            System.out.println("documents for query " + entry.getKey() + ":" + " " +
                    entry.getValue().findClosestDocuments(documents, new CosineDistance()));
        }
    }
}