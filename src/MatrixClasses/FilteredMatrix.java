package MatrixClasses;

import java.util.ArrayList;

public class FilteredMatrix extends Matrix{
    public FilteredMatrix(String filePath) {
        super(filePath);
    }

    public ArrayList<Integer> findAllRows() {
        ArrayList<Integer> rows = new ArrayList<>();
        for (int i = 0; i < this.matrix.size(); i++) {
            rows.add(i);
        }
        return rows;
    } //returns all the indices of all rows, e.g., 0,1,... up to the total number of rows -1
    public int getCategoryAttribute() {
        return 4;
    } //returns the index of the category attribute
    public double findProb(ArrayList<Integer> row, int category) {

    } //takes as input the values for a single row, e.g., 5,3,1,2 and the category, e.g. 2. Returns the probability that the row belongs to the category using the Naïve Bayesian model.
    // p(A=ai | C=cj) = (nij + (lambda: i / n)) / (nj + lambda*mi)
    public int findCategory(ArrayList<Integer> row) {

    } //takes as input the values for a single row, e.g., 5,3,1,2. Returns the most probable category of the row using the Naïve Bayesian Model.

}
