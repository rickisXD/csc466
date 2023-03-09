package labs;

import MatrixClasses.FilteredMatrix;

import java.util.ArrayList;
import java.util.Scanner;

public class Lab8 {
    public static void main(String[] args) {
        FilteredMatrix matrix = readInput("./files/data.txt");
        ArrayList<Integer> input = getCustomerInput();
        for (int i = 1; i < 4; i++) {
            System.out.println("For value " + i + ": Probability is: " + matrix.findProb(input, i));
        }
        System.out.println("Expected category: " + matrix.findCategory(input));
    }

    public static FilteredMatrix readInput(String filepath) {
        return new FilteredMatrix(filepath);
    }

    public static ArrayList<Integer> getCustomerInput() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> input = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter value for attribute " + (i + 1) + ": ");
            input.add(sc.nextInt());
        }
        return input;
    }
}