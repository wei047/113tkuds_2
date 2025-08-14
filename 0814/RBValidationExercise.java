/** 練習 1：驗證紅黑樹性質（顏色、根為黑、無連紅、黑高度一致）。 */
public class RBValidationExercise {
    public static void main(String[] args) {
        RBTree<Integer, String> t = new RBTree<>();
        int[] keys = {41, 38, 31, 12, 19, 8};
        for (int k : keys) t.insert(k, "v" + k);

        RBTree.ValidationResult r = t.validate();
        System.out.println("[After inserts] " + r);

        // 人為做一個違規（示範）：這段可註解掉
        // RBTree.Node<Integer,String> n = t.search(12);
        // if (n != null) { n.color = RBTree.Color.RED; if (n.parent != null) n.parent.color = RBTree.Color.RED; }
        // System.out.println("[Manual break] " + t.validate());
    }
}
