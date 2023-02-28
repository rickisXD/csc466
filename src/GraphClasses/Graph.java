package GraphClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Graph {

    public HashSet<Integer> nodes = new HashSet<>();
    public HashMap<Integer, ArrayList<Integer>> adjacencyList = new HashMap<>();
    public HashMap<Integer, Integer> numOutgoingEdges = new HashMap<>();

    public Graph(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();
            while (line != null) {
                String[] edges = line.split(",");
                int nodeFrom = Integer.parseInt(edges[0]);
                int nodeTo = Integer.parseInt(edges[2]);
                nodes.add(nodeTo);
                nodes.add(nodeFrom);
                if (adjacencyList.containsKey(nodeTo)) {
                    if (!adjacencyList.get(nodeTo).contains(nodeFrom))
                        adjacencyList.get(nodeTo).add(nodeFrom);
                } else {
                    adjacencyList.put(nodeTo, new ArrayList<>(){{add(nodeFrom);}});
                }
                if (numOutgoingEdges.containsKey(nodeFrom)) {
                    numOutgoingEdges.put(nodeFrom, numOutgoingEdges.get(nodeFrom) + 1);
                } else {
                    numOutgoingEdges.put(nodeFrom, 1);
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public double findDistance(HashMap<Integer, Double> pageRankOld, HashMap<Integer, Double> pageRankNew) {
        double l1Dist = 0.0;
        for (int node : pageRankOld.keySet()) {
            l1Dist += Math.abs(pageRankNew.get(node) - pageRankOld.get(node));
        }
        return l1Dist;
    }
}
