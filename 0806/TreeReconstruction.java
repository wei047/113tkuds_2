
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class TreeReconstruction {

    public static TreeNode buildFromPreIn(int[] preorder, int[] inorder) {
        return buildPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private static TreeNode buildPreIn(int[] pre, int ps, int pe, int[] in, int is, int ie) {
        if (ps > pe || is > ie) return null;
        int rootVal = pre[ps];
        TreeNode root = new TreeNode(rootVal);
        int idx = is;
        while (in[idx] != rootVal) idx++;
        int leftSize = idx - is;
        root.left = buildPreIn(pre, ps + 1, ps + leftSize, in, is, idx - 1);
        root.right = buildPreIn(pre, ps + leftSize + 1, pe, in, idx + 1, ie);
        return root;
    }

    public static TreeNode buildFromPostIn(int[] post, int[] in) {
        return buildPostIn(post, 0, post.length - 1, in, 0, in.length - 1);
    }

    private static TreeNode buildPostIn(int[] post, int ps, int pe, int[] in, int is, int ie) {
        if (ps > pe || is > ie) return null;
        int rootVal = post[pe];
        TreeNode root = new TreeNode(rootVal);
        int idx = is;
        while (in[idx] != rootVal) idx++;
        int leftSize = idx - is;
        root.left = buildPostIn(post, ps, ps + leftSize - 1, in, is, idx - 1);
        root.right = buildPostIn(post, ps + leftSize, pe - 1, in, idx + 1, ie);
        return root;
    }

    public static TreeNode buildCompleteTree(int[] level) {
        return buildLevel(level, 0);
    }

    private static TreeNode buildLevel(int[] arr, int i) {
        if (i >= arr.length) return null;
        TreeNode root = new TreeNode(arr[i]);
        root.left = buildLevel(arr, 2 * i + 1);
        root.right = buildLevel(arr, 2 * i + 2);
        return root;
    }

    public static boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null || a.val != b.val) return false;
        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    public static void main(String[] args) {
        int[] pre = {3,9,20,15,7}, in = {9,3,15,20,7}, post = {9,15,7,20,3};
        TreeNode t1 = buildFromPreIn(pre, in);
        TreeNode t2 = buildFromPostIn(post, in);
        System.out.println(isSameTree(t1, t2));

        int[] level = {1,2,3,4,5,6,7};
        TreeNode t3 = buildCompleteTree(level);
        System.out.println(t3.val);
    }
}
