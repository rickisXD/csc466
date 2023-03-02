package AssociationClasses;

public class Rule {

    public ItemSet left, right;

    public Rule(ItemSet left, ItemSet right) {
        this.left = left;
        this.right = right;
    }

    public boolean equals(Rule r) {
        return left.items.equals(r.left.items) && right.items.equals(r.right.items);
    }

    public String toString() {
        return left.toString() + "->" + right.toString();
    }
}
