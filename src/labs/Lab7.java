package labs;

import MatrixClasses.Matrix;

import java.util.*;

public class Lab7 {

    public static Matrix matrix;
    public static double minIGR = 0.01;
    public static void main(String[] args) {
        process("./files/data.txt");
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> attributes = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        for (int i = 0; i < matrix.matrix.size(); i++) {
            rows.add(i);
        }
        printDecisionTree(matrix, attributes, rows, 0, 100);
    }

    public static void process(String filename) {
        matrix = new Matrix(filename);
    }//creates a two-dimensional array from the input file.
    public static void printDecisionTree(Matrix data, ArrayList<Integer> attributes, ArrayList<Integer> rows, int level, double currentIGR) {
        String whenStmt = "When attribute %d has value %d";
        String endStmt = "value = %d";
        String tab = "  ";
        HashSet<Integer> diffVals = data.findDifferentValues(4, rows);
        if (attributes.size() == 0 || diffVals.size() == 1) {
            System.out.println(tab.repeat(level) + String.format(endStmt, data.findMostCommonValue(rows)));
            return;
        }
        HashMap<Integer, Double> infoGains = new HashMap<>();
        for (int attribute : attributes) {
            infoGains.put(attribute, data.computeIGR(attribute, rows));
        }
        int maxIGRattr = attributes.get(0);
        for (int attribute : attributes) {
            if (infoGains.get(attribute) > infoGains.get(maxIGRattr)) {
                maxIGRattr = attribute;
            }
        }
        if (currentIGR - infoGains.get(maxIGRattr) < minIGR) {
            System.out.println(tab.repeat(level) + String.format(endStmt, data.findMostCommonValue(rows)));
            return;
        }
        HashMap<Integer, ArrayList<Integer>> splits = data.split(maxIGRattr, rows);
        attributes.remove(Integer.valueOf(maxIGRattr));
        for (int value : splits.keySet()) {
            System.out.println(tab.repeat(level) + String.format(whenStmt, maxIGRattr + 1, value));
            if (level + 1 == 3 && splits.get(value).size() == 25) {
                double abc = data.computeIGR(maxIGRattr, rows);
            }
            printDecisionTree(data, attributes, splits.get(value), level + 1, infoGains.get(maxIGRattr));
        }
    } //recursive method that prints the decision tree. It takes as input the data, the set of attributes that have not been used so far in this branch of the tree, the set of rows to examine, the current level (initially 0, use to determine how many tabs to print), and the information gain ratio from last iteration (I set it initially equal to 100, used to create terminating condition).
}

/*
When attribute 4 has value 0
  value = 2
When attribute 4 has value 1
  When attribute 3 has value 3
    value = 1
  When attribute 3 has value 4
    When attribute 1 has value 4
      value = 3
    When attribute 1 has value 5
      value = 1
    When attribute 1 has value 6
      value = 1
    When attribute 1 has value 7
      value = 1
  When attribute 3 has value 5
    value = 3
  When attribute 3 has value 6
    value = 3
When attribute 4 has value 2
  value = 3

 */