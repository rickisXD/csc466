package MatrixClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Matrix {

    public ArrayList<ArrayList<Integer>> matrix;
    public Matrix(String filePath) {
        this.matrix = new ArrayList<>();
        File file = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                String[] info = line.split(",");
                int d1 = (int) Double.parseDouble(info[0]);
                int d2 = (int) Double.parseDouble(info[1]);
                int d3 = (int) Double.parseDouble(info[2]);
                int d4 = (int) Double.parseDouble(info[3]);
                int classification = Integer.parseInt(info[4]);
                this.matrix.add(new ArrayList<>(Arrays.asList(d1, d2, d3, d4, classification)));
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private int findFrequency(int attribute, int value, ArrayList<Integer> rows) {
        int numOcc = 0;
        for (int row : rows) {
            if (this.matrix.get(row).get(attribute) == value) {
                numOcc++;
            }
        }
        return numOcc;
    }//Examines only the specified rows of the array. It returns the number of rows in which the element at position attribute (a number between 0 and 4) is equal to value.

    public HashSet<Integer> findDifferentValues(int attribute, ArrayList<Integer> rows) {
        HashSet<Integer> seenVals = new HashSet<>();
        for (int row : rows) {
            seenVals.add(this.matrix.get(row).get(attribute));
        }
        return seenVals;
    } //Examines only the specified rows of the array. It returns a HashSet of the different values for the specified attribute.

    private ArrayList<Integer> findRows(int attribute, int value, ArrayList<Integer> rows) {
        ArrayList<Integer> rowOcc = new ArrayList<>();
        for (int row : rows) {
            if (this.matrix.get(row).get(attribute) == value) {
                rowOcc.add(row);
            }
        }
        return rowOcc;
    } //Examines only the specified rows of the array. Returns an ArrayList of the rows where the value for the attribute is equal to value.

    private double log2(double number) {
        if (number == 0) {
            return 0;
        }
        return (Math.log(number) / Math.log(2));
    } //returns log2 of the input

    private double findEntropy(ArrayList<Integer> rows) {
        double numRows = rows.size();
        double num1 = 0;
        double num2 = 0;
        double num3 = 0;
        for (int row : rows) {
            switch (this.matrix.get(row).get(4)) {
                case 1:
                    num1++;
                case 2:
                    num2++;
                case 3:
                    num3++;
            }
        }
        double n1 = -(num1 / numRows) * log2(num1 / numRows);
        double n2 = -(num2 / numRows) * log2(num2 / numRows);
        double n3 = -(num3 / numRows) * log2(num3 / numRows);
        return n1 + n2 + n3;
    } //finds the entropy of the dataset that consists of the specified rows.

    private double findEntropy(int attribute, ArrayList<Integer> rows) {
        HashSet<Integer> values = findDifferentValues(attribute, rows);
        double entropy = 0;
        for (int value : values) {
            ArrayList<Integer> rowsWithVal = findRows(attribute, value, rows);
            double e = findEntropy(rowsWithVal);
            entropy += ((double) rowsWithVal.size() / (double) rows.size()) * e;
        }
        return entropy;
    } //finds the entropy of the dataset that consists of the specified rows after it is partitioned on the attribute.

    private double findGain(int attribute, ArrayList<Integer> rows) {
        double e1 = findEntropy(rows);
        double e2 = findEntropy(attribute, rows);
        return e1 - e2;
    } // finds the information gain of partitioning on the attribute. Considers only the specified rows.

    public double computeIGR(int attribute, ArrayList<Integer> rows) {
        HashMap<Integer, Double> valueCounts = new HashMap<>();
        double numRows = rows.size();
        for (int row : rows) {
            int value = this.matrix.get(row).get(attribute);
            if (!valueCounts.containsKey(value)) {
                valueCounts.put(value, 1.0);
            } else {
                valueCounts.put(value, valueCounts.get(value) + 1);
            }
        }
        double entropy = 0;
        for (double count : valueCounts.values()) {
            entropy += -(count / numRows) * log2(count / numRows);
        }
        double gain = findGain(attribute, rows);
        if (gain == 0) {
            return 0;
        }
        return gain / entropy;
    }// returns the Information Gain Ratio, where we only look at the data defined by the set of rows and we consider splitting on attribute.

    public int findMostCommonValue(ArrayList<Integer> rows) {
        HashMap<Integer, Integer> classCounts = new HashMap<>() {{
            put(1, 0);
            put(2, 0);
            put(3, 0);
        }};
        for (int row : rows) {
            int classification = this.matrix.get(row).get(4);
            classCounts.put(classification, classCounts.get(classification) + 1);
        }
        int max = 0;
        int maxKey = 1;
        for (int key : classCounts.keySet()) {
            if (classCounts.get(key) > max) {
                max = classCounts.get(key);
                maxKey = key;
            }
        }
        return maxKey;
    }// returns the most common category for the dataset that is the defined by the specified rows.

    public HashMap<Integer, ArrayList<Integer>> split(int attribute, ArrayList<Integer> rows) {
        HashMap<Integer, ArrayList<Integer>> split = new HashMap<>();
        HashSet<Integer> values = findDifferentValues(attribute, rows);
        for (int value : values) {
            split.put(value, findRows(attribute, value, rows));
        }
        return split;
    } //Splits the dataset that is defined by rows on the attribute. Each element of the HashMap that is returned contains the value for the attribute and an ArrayList of rows that have this value.
}
