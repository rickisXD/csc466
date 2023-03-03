package labs;

import MatrixClasses.Matrix;

import java.util.ArrayList;

public class Lab7 {

    public static Matrix matrix;
    public static double minIGR = 0.01;
    public static void main(String[] args) {

    }

    public static int[][] process(String filename) {
        matrix = new Matrix(filename);
    }//creates a two-dimensional array from the input file.
    public static void printDecisionTree(int[][] data, ArrayList<Integer> attributes, ArrayList<Integer> rows, int level, double currentIGR) {

    } //recursive method that prints the decision tree. It takes as input the data, the set of attributes that have not been used so far in this branch of the tree, the set of rows to examine, the current level (initially 0, use to determine how many tabs to print), and the information gain ratio from last iteration (I set it initially equal to 100, used to create terminating condition).

}
