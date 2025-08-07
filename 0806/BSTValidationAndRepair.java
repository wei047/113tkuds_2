import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class BSTValidationAndRepair {

    public static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    public static List<TreeNode> findInvalidNodes(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        findInvalid(root, Long.MIN_VALUE, Long.MAX_VALUE, result);
        return result;
    }

    private static void findInvalid(TreeNode node, long min, long max, List<TreeNode> result) {
        if (node == null) return;
        if (node.val <= min || node.val >= max) result.add(node);
        findInvalid(node.left, min, node.val, result);
        findInvalid(node.right, node.val, max, result);
    }

    static TreeNode first, second, prev;

    public static void recoverTree(TreeNode root) {
        first = second = prev = null;
        inorder(root);
        if (first != null && second != null) {
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }
    }

    private static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != null && root.val < prev.val) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;
        inorder(root.right);
    }

    public static int minRemovalsToBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderList(root, list);
        return list.size() - longestIncreasingSubsequence(list);
    }

    private static void inorderList(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inorderList(node.left, list);
        list.add(node.val);
        inorderList(node.right, list);
    }

    private static int longestIncreasingSubsequence(List<Integer> nums) {
        List<Integer> dp = new ArrayList<>();
        for (int num : nums) {
            int idx = Collections.binarySearch(dp, num);
            if (idx < 0) idx = -idx - 1;
            if (idx == dp.size()) dp.add(num);
            else dp.set(idx, num);
        }
        return dp.size();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        System.out.println(isValidBST(root));
        System.out.println(findInvalidNodes(root).size());
        recoverTree(root);
        System.out.println(isValidBST(root));
        System.out.println(minRemovalsToBST(root));
    }
}
