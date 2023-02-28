package labs;

import AssociationClasses.ItemSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Lab5 {
    public static void main(String[] args) {//lists of all itemsets
        ArrayList<Integer> items; //lists of all items
        HashMap<Integer, ArrayList<ItemSet>> frequentItemSet; //lists frequent itemsets. E.g., for key=1, store all 1-itemsets, for key=2, all 2-itemsets and so on.
        double minSupport = 0.01;
    }

    public static void process(String fileName) {
        ArrayList<ItemSet> transactions = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            while (line != null) {
                String[] items = line.split(", ");
                items = Arrays.copyOfRange(items, 1, items.length);
                transactions.add(new ItemSet(items));
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(transactions);
    } //processes the input file

    public static boolean findFrequentItemSets(int k) {
        return false;
    } //finds all k-itemsets, Returns false if no itemsets were found (precondition k>=2)

    public static boolean isFrequent(ItemSet itemSet) {
        return false;
    } //tells if the itemset is frequent, i.e., meets the minimum support

    public static void findFrequentSingleItemSets() {

    } //finds all 1-itemsets

}
