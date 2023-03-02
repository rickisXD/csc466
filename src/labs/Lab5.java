package labs;

import AssociationClasses.ItemSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Lab5 {

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
        freqTxns.put(1, findFrequentSingleItemSets(transactions, allItems));
        for (int i = 2; i < biggestTransaction; i++) {
            if (freqTxns.get(i - 1) == null) {
                break;
            }
            ArrayList<ItemSet> freq = findFrequentItemSets(transactions, i, freqTxns.get(i - 1));
            if (freq.size() != 0) {
                freqTxns.put(i, freq);
            }
        }
        System.out.println(freqTxns);
    }

    public static ArrayList<ItemSet> findFrequentItemSets(ArrayList<ItemSet> transactions, int k, ArrayList<ItemSet> freqSetBelow) {
        ArrayList<ItemSet> freqSet = new ArrayList<>();
        for (int i = 0; i < freqSetBelow.size() - 1; i++) {
            ArrayList<Integer> a = freqSetBelow.get(i).items;
            for (int j = i + 1; j < freqSetBelow.size() - 1; j++) {
                ArrayList<Integer> b = freqSetBelow.get(j).items;
                if (k == 2 || a.subList(0, a.size() - 1).equals(b.subList(0, b.size() - 1))) {
                    HashSet<Integer> combined = new HashSet<>();
                    combined.addAll(a);
                    combined.addAll(b);
                    ArrayList<Integer> list = new ArrayList<>(combined);
                    Collections.sort(list);
                    if (isFrequent(transactions, new ItemSet(list)))
                        freqSet.add(new ItemSet(list));
                }
            }
        }
        return freqSet;
    }

    public static boolean isFrequent(ArrayList<ItemSet> transactions, ItemSet itemSet) {
        double i = 0;
        for (ItemSet txn : transactions) {
            i++;
            for (int item : itemSet.items) {
                if (!txn.containsItem(item)) {
                    i--;
                    break;
                }
            }
        }
        return ((i / (double) transactions.size()) >= minSupport);
    }

    public static ArrayList<ItemSet> findFrequentSingleItemSets(ArrayList<ItemSet> transactions, HashSet<Integer> allItems) {
        ArrayList<ItemSet> singleItemSets = new ArrayList<>();
        for (int item : allItems) {
            if (isFrequent(transactions, new ItemSet(List.of(item)))) {
                singleItemSets.add(new ItemSet(List.of(item)));
            }
        }
        return singleItemSets;
    }
}