package MatrixClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Matrix {

    ArrayList<ArrayList<Integer>> matrix;
    public Matrix(String filePath) {
        File file = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private int findFrequency(int attribute, int value, ArrayList<Integer> rows) {

    }//Examines only the specified rows of the array. It returns the number of rows in which the element at position attribute (a number between 0 and 4) is equal to value.

    private HashSet<Integer> findDifferentValues(int attribute, ArrayList<Integer> rows) {

    } //Examines only the specified rows of the array. It returns a HashSet of the different values for the specified attribute.
    private ArrayList<Integer> findRows(int attribute, int value, ArrayList<Integer> rows) {

    } //Examines only the specified rows of the array. Returns an ArrayList of the rows where the value for the attribute is equal to value.
    private double log2(double number) {

    } //returns log2 of the input
    private double findEntropy(ArrayList<Integer> rows) {

    } //finds the entropy of the dataset that consists of the specified rows.
    private double findEntropy(int attribute, ArrayList<Integer> rows) {

    } //finds the entropy of the dataset that consists of the specified rows after it is partitioned on the attribute.
    private double findGain(int attribute, ArrayList<Integer> rows) {

    } // finds the information gain of partitioning on the attribute. Considers only the specified rows.
    public double computeIGR(int attribute, ArrayList<Integer> rows) {

    }// returns the Information Gain Ratio, where we only look at the data defined by the set of rows and we consider splitting on attribute.
    public int findMostCommonValue(ArrayList<Integer> rows) {

    }// returns the most common category for the dataset that is the defined by the specified rows.
    public HashMap<Integer, ArrayList<Integer>> split(int attribute, ArrayList<Integer> rows) {

    } //Splits the dataset that is defined by rows on the attribute. Each element of the HashMap that is returned contains the value for the attribute and an ArrayList of rows that have this value.
}
