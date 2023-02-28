package labs;

import GraphClasses.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.sort;

public class Lab4 {

    public static void main(String[] args) {
        double e = 0.001;
        double d = 0.9;
        HashMap<Integer, Double> pageRankOld = new HashMap<>();
        HashMap<Integer, Double> pageRankNew = new HashMap<>();
        Graph g = new Graph("./files/graph.txt");
        double cumsum = 0.0;

        // Initialize pageRankOld
        for (int node : g.nodes) {
            pageRankOld.put(node, 1.0 / (double) g.nodes.size());
        }

        // Initialize pageRankNew
        for (int node : g.nodes) {
            double rank = (1 - d) * (1 / (double) g.nodes.size());
            if (g.adjacencyList.get(node) != null) {
                for (int incoming : g.adjacencyList.get(node)) {
                    rank += ((pageRankOld.get(incoming) / (double) g.numOutgoingEdges.get(incoming)) * d);
                }
            }
            cumsum += rank;
            pageRankNew.put(node, rank);
        }

        // Normalize pageRankNew
        for (int node : pageRankNew.keySet()) {
            pageRankNew.put(node, pageRankNew.get(node) / cumsum);
        }

        // Iterate until convergence
        while (g.findDistance(pageRankOld, pageRankNew) >= e) {
            pageRankOld = (HashMap<Integer, Double>) pageRankNew.clone();
            cumsum = 0.0;
            for (int node : g.nodes) {
                double rank = (1 - d) * (1 / (double) g.nodes.size());
                if (g.adjacencyList.get(node) != null) {
                    for (int incoming : g.adjacencyList.get(node)) {
                        rank += ((pageRankOld.get(incoming) / (double) g.numOutgoingEdges.get(incoming)) * d);
                    }
                }
                cumsum += rank;
                pageRankNew.put(node, rank);
            }
            for (int node : pageRankNew.keySet()) {
                pageRankNew.put(node, pageRankNew.get(node) / cumsum);
            }
        }

        // Sort and print
        ArrayList<Map.Entry<Integer, Double>> nodeRanks = new ArrayList<>();
        for (Map.Entry<Integer, Double> entry : pageRankOld.entrySet()) {
            nodeRanks.add(entry);
        }
        sort(nodeRanks, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        System.out.println(nodeRanks.stream().limit(20).map(Map.Entry::getKey).collect(Collectors.toList()));
    }
}
// [1159, 1293, 155, 55, 1051, 641, 729, 1153, 855, 323, 1245, 1260, 798, 1112, 1461, 963, 1463, 1306, 1179, 535]
