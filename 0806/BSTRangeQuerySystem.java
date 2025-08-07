import java.util.*;

class BSTNode {
    int val;
    BSTNode left, right;
    BSTNode(int val) {
        this.val = val;
    }
}

public class BSTRangeQuerySystem {

    public static void insert(BSTNode root, int val) {
        if (val < root.val) {
            if (root.left == null) root.left = new BSTNode(val);
            else insert(root.left, val);
        } else {
            if (root.right == null) root.right = new BSTNode(val);
            else insert(root.right, val);
        }
    }

    public static List<Integer> rangeQuery(BSTNode root, int min, int max) {
        List<Integer> result = new ArrayList<>();
        dfsRangeQuery(root, min, max, result);
        return result;
    }

    private static void dfsRangeQuery(BSTNode node, int min, int max, List<Integer> result) {
        if (node == null) return;
        if (node.val > min) dfsRangeQuery(node.left, min, max, result);
        if (node.val >= min && node.val <= max) result.add(node.val);
        if (node.val < max) dfsRangeQuery(node.right, min, max, result);
    }

    public static int rangeCount(BSTNode root, int min, int max) {
        return rangeQuery(root, min, max).size();
    }

    public static int rangeSum(BSTNode root, int min, int max) {
        return rangeQuery(root, min, max).stream().mapToInt(Integer::intValue).sum();
    }

    public static int closestValue(BSTNode root, int target) {
        int closest = root.val;
        BSTNode current = root;
        while (current != null) {
            if (Math.abs(current.val - target) < Math.abs(closest - target)) {
                closest = current.val;
            }
            current = target < current.val ? current.left : current.right;
        }
        return closest;
    }

    public static void main(String[] args) {
        BSTNode root = new BSTNode(10);
        insert(root, 5);
        insert(root, 15);
        insert(root, 3);
        insert(root, 7);
        insert(root, 13);
        insert(root, 18);

        System.out.println("Range Query [5, 15]: " + rangeQuery(root, 5, 15));
        System.out.println("Range Count [5, 15]: " + rangeCount(root, 5, 15));
        System.out.println("Range Sum [5, 15]: " + rangeSum(root, 5, 15));
        System.out.println("Closest to 11: " + closestValue(root, 11));
    }
}
