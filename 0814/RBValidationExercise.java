public class RBValidationExercise {
    public static void main(String[] args) {
        RBTree<Integer, String> t = new RBTree<>();
        int[] keys = {41, 38, 31, 12, 19, 8};
        for (int k : keys) t.insert(k, "v" + k);

        RBTree.ValidationResult r = t.validate();
        System.out.println("[After inserts] " + r);
    }
}
