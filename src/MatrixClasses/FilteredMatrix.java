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
        double nj = findFrequency(getCategoryAttribute(), category, findAllRows());
        double lambda = 1.0 / (double) this.matrix.size();
        double prob = nj / (double) this.matrix.size();
        for (int i = 0; i < row.size(); i++) {
            double nij = 0;
            for (ArrayList<Integer> r : this.matrix) {
                if (r.get(i).equals(row.get(i)) && r.get(getCategoryAttribute()).equals(category)) {
                    nij++;
                }
            }
            double mi = findDifferentValues(i, findAllRows()).size();
            prob *= (nij + lambda) / (nj + lambda * mi);
        }
        return prob;
    } //takes as input the values for a single row, e.g., 5,3,1,2 and the category, e.g. 2. Returns the probability that the row belongs to the category using the Naïve Bayesian model.

    public int findCategory(ArrayList<Integer> row) {
        double maxProb = 0;
        int maxCat = 0;
        for (int cat : findDifferentValues(getCategoryAttribute(), findAllRows())) {
            double prob = findProb(row, cat);
            if (prob > maxProb) {
                maxProb = prob;
                maxCat = cat;
            }
        }
        return maxCat;
    } //takes as input the values for a single row, e.g., 5,3,1,2. Returns the most probable category of the row using the Naïve Bayesian Model.

}
