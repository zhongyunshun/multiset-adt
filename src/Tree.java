import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Tree<T> {
    private T root;
    private List<Tree<T>> subtrees;

    public Tree(Optional<T> root, List<Tree<T>> subtrees) {
        this.root = root.orElse(null);
        this.subtrees = (subtrees != null) ? new ArrayList<>(subtrees) : new ArrayList<>();
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        if (isEmpty()) {
            return 0;
        }
        int size = 1;  // root
        for (Tree<T> subtree : subtrees) {
            size += subtree.size();
        }
        return size;
    }

    public int count(T item) {
        if (isEmpty()) {
            return 0;
        }
        int num = 0;
        if (root.equals(item)) {
            num += 1;
        }
        for (Tree<T> subtree : subtrees) {
            num += subtree.count(item);
        }
        return num;
    }

    public String toString() {
        return toStringIndented(0);
    }

    private String toStringIndented(int depth) {
        if (isEmpty()) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            s.append("  ");
        }
        s.append(root).append("\n");
        for (Tree<T> subtree : subtrees) {
            s.append(subtree.toStringIndented(depth + 1));
        }
        return s.toString();
    }

    public float average() {
        if (isEmpty()) {
            return 0.0f;
        }
        int[] result = averageHelper();
        return (float) result[0] / result[1];
    }

    private int[] averageHelper() {
        if (isEmpty()) {
            return new int[]{0, 0};
        }
        int total = (Integer) root;
        int count = 1;
        for (Tree<T> subtree : subtrees) {
            int[] subResult = subtree.averageHelper();
            total += subResult[0];
            count += subResult[1];
        }
        return new int[]{total, count};
    }

    // ... other methods
}

