package labs;

import MatrixClasses.FilteredMatrix;

import java.util.ArrayList;
import java.util.Scanner;

public class Lab8 {
    public static void main(String[] args) {
        FilteredMatrix matrix = readInput("./files/data.txt");

    }

    public static FilteredMatrix readInput(String filepath) {
        return new FilteredMatrix(filepath);
    } //reads input from file

    public static ArrayList<Integer> getCustomerInput() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> input = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter value for attribute" + (i + 1) + ": ");
            input.add(sc.nextInt());
        }
        return input;
    } //reads input from keyboard
}