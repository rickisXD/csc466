package labs;

import AssociationClasses.ItemSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Lab6 {

    public static double minSupport = 0.01;

    public static void main(String[] args) {
        HashMap<Integer, ArrayList<ItemSet>> frequentItemSet;
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
        System.out.println(freqTxns);
    }
}
