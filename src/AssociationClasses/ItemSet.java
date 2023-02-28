package AssociationClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemSet {
    public ArrayList<Integer> items;

    public ItemSet(String[] items) {
        this.items = new ArrayList<>();
        for (String i : items) {
            this.items.add(Integer.parseInt(i));
        }
    }

    public String toString() {
        return items.toString();
    }
}
