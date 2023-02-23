package GraphClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Graph {

    public HashSet<Integer> nodes = new HashSet<>();
    public HashMap<Integer, ArrayList<Integer>> adjacencyList = new HashMap<>();

    public Graph(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();
            while (line != null) {
                String[] edges = line.split(" ");
                int nodeFrom = Integer.parseInt(edges[0]);
                int nodeTo = Integer.parseInt(edges[2]);
                nodes.add(nodeTo);
                nodes.add(nodeFrom);
                if (adjacencyList.containsKey(nodeFrom)) {
                    adjacencyList.get(nodeFrom).add(nodeTo);
                } else {
                    adjacencyList.put(nodeFrom, new ArrayList<>(){{add(nodeTo);}});
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public double findDistance(HashMap<Integer, Double> pageRankOld, HashMap<Integer, Double> pageRankNew) {

    }
}
