package labs;

import DocumentClasses.CosineDistance;
import DocumentClasses.DocumentCollection;
import DocumentClasses.OkapiDistance;
import DocumentClasses.TextVector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lab3 {

    public static DocumentClasses.DocumentCollection documents;
    public static DocumentClasses.DocumentCollection queries;

    public static void main(String[] args) {

        HashMap<Integer, ArrayList<Integer>> humanJudgement = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("./files/human_judgement.txt"));
            String line = br.readLine();
            while (line != null) {
                String[] docs = line.split(" ");
                int query = Integer.parseInt(docs[0]);
                int doc = Integer.parseInt(docs[1]);
                int decision = Integer.parseInt(docs[2]);
                if (decision < 4 && decision > 0) {
                    if (humanJudgement.containsKey(query)) {
                        humanJudgement.get(query).add(doc);
                    } else {
                        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(doc));
                        humanJudgement.put(query, a);
                    }
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        documents = new DocumentCollection("./files/documents.txt", "document");
        queries = new DocumentCollection("./files/queries.txt", "query");
        documents.normalize(documents);
        queries.normalize(documents);
        HashMap<Integer, ArrayList<Integer>> cosineResults = new HashMap<>();
        for (int q = 1; q < 21; q++) {
            cosineResults.put(q,
                    queries.getDocumentById(q).findClosestDocuments(documents, new CosineDistance()));
        }

        documents = new DocumentCollection("./files/documents.txt", "document");
        queries = new DocumentCollection("./files/queries.txt", "query");
        HashMap<Integer, ArrayList<Integer>> okapiResults = new HashMap<>();
        for (int q = 1; q < 21; q++) {
            okapiResults.put(q,
                    queries.getDocumentById(q).findClosestDocuments(documents, new OkapiDistance()));
        }

        System.out.println("Cosine MAP = " +
                computeMAP(humanJudgement, cosineResults));
        System.out.println("Okapi MAP = " +
                computeMAP(humanJudgement, okapiResults));
    }

    public static double computeMAP(HashMap<Integer, ArrayList<Integer>> relevant, HashMap<Integer, ArrayList<Integer>> returned) {
        double summedMap = 0.0;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : returned.entrySet()) {
            ArrayList<Integer> relevantDocs = relevant.get(entry.getKey());
            int numRelevant = 0;
            double map = 0.0;
            ArrayList<Integer> returnedList = entry.getValue();
            for (int i = 0; i < returnedList.size(); i++) {
                int docId = returnedList.get(i);
                if (relevantDocs.contains(docId)) {
                    numRelevant++;
                    map += (double) numRelevant / (i + 1);
                }
            }
            summedMap += map / (double) relevantDocs.size();
        }
        return summedMap / 20.0;
    }
}
