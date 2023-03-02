package AssociationClasses;

public class Rule {

    public ItemSet left, right;

    public Rule() {

    }

    public boolean equals(Rule r) {
        return left.items.equals(r.left.items) && right.items.equals(r.right.items);
    }

    public String toString() {
        return left.toString() + "->" + right.toString();
    }
}
