import java.util.*;

class BSTNode {
    int val;
    BSTNode left, right;
    int count;
    BSTNode(int val) {
        this.val = val;
        this.count = 1;
    }
}

public class BSTKthElement {

    public static BSTNode insert(BSTNode root, int val) {
        if (root == null) return new BSTNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        root.count = 1 + getCount(root.left) + getCount(root.right);
        return root;
    }

    public static BSTNode delete(BSTNode root, int val) {
        if (root == null) return null;
        if (val < root.val) root.left = delete(root.left, val);
        else if (val > root.val) root.right = delete(root.right, val);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            BSTNode minNode = getMin(root.right);
            root.val = minNode.val;
            root.right = delete(root.right, minNode.val);
        }
        root.count = 1 + getCount(root.left) + getCount(root.right);
        return root;
    }

    public static BSTNode getMin(BSTNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public static int getCount(BSTNode node) {
        return node == null ? 0 : node.count;
    }

    public static int findKthSmallest(BSTNode root, int k) {
        int left = getCount(root.left);
        if (k <= left) return findKthSmallest(root.left, k);
        if (k == left + 1) return root.val;
        return findKthSmallest(root.right, k - left - 1);
    }

    public static int findKthLargest(BSTNode root, int k) {
        int right = getCount(root.right);
        if (k <= right) return findKthLargest(root.right, k);
        if (k == right + 1) return root.val;
        return findKthLargest(root.left, k - right - 1);
    }

    public static List<Integer> findRange(BSTNode root, int k1, int k2) {
        List<Integer> result = new ArrayList<>();
        inorderRange(root, result, k1, k2, new int[]{1});
        return result;
    }

    private static void inorderRange(BSTNode node, List<Integer> list, int k1, int k2, int[] count) {
        if (node == null) return;
        inorderRange(node.left, list, k1, k2, count);
        if (count[0] >= k1 && count[0] <= k2) list.add(node.val);
        count[0]++;
        inorderRange(node.right, list, k1, k2, count);
    }

    public static void main(String[] args) {
        BSTNode root = null;
        int[] vals = {20, 8, 22, 4, 12, 10, 14};
        for (int v : vals) root = insert(root, v);

        System.out.println("3rd smallest: " + findKthSmallest(root, 3));
        System.out.println("2nd largest: " + findKthLargest(root, 2));
        System.out.println("Range 2~5 smallest: " + findRange(root, 2, 5));

        root = insert(root, 18);
        root = delete(root, 12);
        System.out.println("Updated 4th smallest: " + findKthSmallest(root, 4));
    }
}
