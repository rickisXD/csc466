package AssociationClasses;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ItemSet {
    public ArrayList<Integer> items;

    public ItemSet(String[] items) {
        this.items = new ArrayList<>();
        for (String i : items) {
            this.items.add(Integer.parseInt(i));
        }
    }

    public ItemSet(List<Integer> items) {
        this.items = new ArrayList<>();
        this.items.addAll(items);
    }

    public boolean containsItem(int item) {
        return this.items.contains(item);
    }

    public String toString() {
        return items.toString();
    }
}
