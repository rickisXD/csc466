package labs;

import AssociationClasses.ItemSet;
import AssociationClasses.Rule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Lab6 {

    public static double minConfidence = 0.99;


    public static void main(String[] args) {
        process("./files/shopping_data.txt");
    }

    public static void process(String fileName) {
        ArrayList<ItemSet> transactions = new ArrayList<>();
        HashSet<Integer> allItems = new HashSet<>();
        HashMap<Integer, ArrayList<ItemSet>> freqTxns = new HashMap<>();
        int biggestTransaction = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            while (line != null) {
                String[] items = line.split(", ");
                biggestTransaction = Math.max(items.length - 1, biggestTransaction);
                items = Arrays.copyOfRange(items, 1, items.length);
                ItemSet txn = new ItemSet(items);
                transactions.add(txn);
                allItems.addAll(txn.items);
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        freqTxns.put(1, Lab5.findFrequentSingleItemSets(transactions, allItems));
        for (int i = 2; i < biggestTransaction; i++) {
            if (freqTxns.get(i - 1) == null) {
                break;
            }
            ArrayList<ItemSet> freq = Lab5.findFrequentItemSets(transactions, i, freqTxns.get(i - 1));
            if (freq.size() != 0) {
                freqTxns.put(i, freq);
            }
        }
        ArrayList<Rule> rules = new ArrayList<>();
        for (int i = 2; i <= Collections.max(freqTxns.keySet()); i++) {
            for (ItemSet items : freqTxns.get(i)) {
                rules.addAll(split(items, transactions));
            }
        }
        System.out.println(rules);
    }

    public static ArrayList<Rule> split(ItemSet itemSet, ArrayList<ItemSet> transactions) {
        ArrayList<Rule> rules = new ArrayList<>();
//            while (seenItemsLeft.size() != itemSet.items.size()) {
//                ArrayList<Integer> left = new ArrayList<>();
//                ArrayList<Integer> right = new ArrayList<>();
//                int leftSize = 0;
//                int j = 0;
//                while (leftSize != size && j < itemSet.items.size()) {
//                    int i = itemSet.items.get(j);
//                    if (!seenItemsLeft.contains(i)) {
//                        left.add(i);
//                        leftSize++;
//                        seenItemsLeft.add(i);
//                    } else {
//                        right.add(j);
//                    }
//                    j++;
//                }
//                while (j < itemSet.items.size()) {
//                    int i = itemSet.items.get(j);
//                    right.add(i);
//                    j++;
//                }
//                Rule r = new Rule(new ItemSet(left), new ItemSet(right));
//                if (confidence(r, transactions) >= minConfidence) {
//                    rules.add(r);
//                }
//            }
        // below code from GeekForGeeks!!
        /*set_size of power set of a set
        with set_size n is (2**n -1)*/
        int setSize = itemSet.items.size();
        long pow_set_size = (long) Math.pow(2, setSize);
        int counter, j;

        /*Run from counter 000..0 to
        111..1*/
        for (counter = 0; counter < pow_set_size; counter++) {
            ArrayList<Integer> left = new ArrayList<>();
            ArrayList<Integer> right = new ArrayList<>();
            for (j = 0; j < setSize; j++) {
                /* Check if jth bit in the
                counter is set If set then
                print jth element from set */
                if ((counter & (1 << j)) > 0) {
                    left.add(itemSet.items.get(j));
                } else {
                    right.add(itemSet.items.get(j));
                }
            }
            Rule r = new Rule(new ItemSet(left), new ItemSet(right));
            if (confidence(r, transactions) >= minConfidence) {
                rules.add(r);
            }
        }
        return rules;
    }

    public static double confidence(Rule r, ArrayList<ItemSet> transactions) {
        int i = 0;
        int correct = 0;
        ItemSet left = r.left;
        ItemSet right = r.right;
        if (left.items.size() == 0 || right.items.size() == 0)
            return 0;
        for (ItemSet txn : transactions) {
            int prev = i;
            i++;
            for (int item : left.items) {
                if (!txn.containsItem(item)) {
                    i--;
                    break;
                }
            }
            if (prev + 1 == i) {
                correct++;
                for (int item : right.items) {
                    if (!txn.containsItem(item)) {
                        correct--;
                        break;
                    }
                }
            }
        }
        return (double) correct / (double) i;
    }
}
// [[32, 45]->[16], [17, 29]->[47], [29, 47]->[17], [23, 41]->[24], [7, 11, 45]->[37], [7, 37, 45]->[11], [11, 37, 45]->[7], [12, 31, 48]->[36], [12, 36, 48]->[31], [31, 36, 48]->[12], [23, 24, 40]->[41], [23, 40, 41]->[24], [24, 40, 41]->[23], [23, 24, 43]->[40], [23, 40, 43]->[24], [24, 40, 43]->[23], [23, 24, 43]->[41], [23, 41, 43]->[24], [24, 41, 43]->[23], [23, 40, 43]->[41], [23, 41, 43]->[40], [40, 41, 43]->[23], [24, 40, 43]->[41], [24, 41, 43]->[40], [40, 41, 43]->[24]

// [23, 24, 40, 43]->[41], [23, 24, 41, 43]->[40], [23, 24, 43]->[40, 41], [23, 40, 41, 43]->[24], [23, 40, 43]->[24, 41], [23, 41, 43]->[24, 40], [24, 40, 41, 43]->[23], [24, 40, 43]->[23, 41], [24, 41, 43]->[23, 40], [40, 41, 43]->[23, 24]]