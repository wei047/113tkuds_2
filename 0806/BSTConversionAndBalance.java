

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class DLLNode {
    int val;
    DLLNode prev, next;
    DLLNode(int val) { this.val = val; }
}

public class BSTConversionAndBalance {

    static DLLNode head, prev;

    public static DLLNode bstToDLL(TreeNode root) {
        head = prev = null;
        convert(root);
        return head;
    }

    private static void convert(TreeNode node) {
        if (node == null) return;
        convert(node.left);
        DLLNode curr = new DLLNode(node.val);
        if (prev == null) head = curr;
        else {
            prev.next = curr;
            curr.prev = prev;
        }
        prev = curr;
        convert(node.right);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private static TreeNode build(int[] nums, int l, int r) {
        if (l > r) return null;
        int m = (l + r) / 2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = build(nums, l, m - 1);
        root.right = build(nums, m + 1, r);
        return root;
    }

    public static boolean isBalanced(TreeNode root) {
        return check(root) != -1;
    }

    private static int check(TreeNode node) {
        if (node == null) return 0;
        int l = check(node.left), r = check(node.right);
        if (l == -1 || r == -1 || Math.abs(l - r) > 1) return -1;
        return Math.max(l, r) + 1;
    }

    public static void convertToGreaterSum(TreeNode root) {
        sum = 0;
        reverseInorder(root);
    }

    static int sum = 0;

    private static void reverseInorder(TreeNode node) {
        if (node == null) return;
        reverseInorder(node.right);
        sum += node.val;
        node.val = sum;
        reverseInorder(node.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        DLLNode dll = bstToDLL(root);
        while (dll != null) {
            System.out.print(dll.val + " ");
            dll = dll.next;
        }

        int[] sorted = {1,2,3,4,5,6,7};
        TreeNode bst = sortedArrayToBST(sorted);
        System.out.println(isBalanced(bst));
        convertToGreaterSum(bst);
    }
}
