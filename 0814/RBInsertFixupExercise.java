import java.util.Map;

/** 練習 2：插入後的修復（三種情況：叔叔紅、叔叔黑且內彎、叔叔黑且外彎）。 */
public class RBInsertFixupExercise {
    public static void main(String[] args) {
        RBTree<Integer, Integer> t = new RBTree<>();

        int[] seq = {10, 20, 30, 15, 25, 5, 1, 50, 60, 55};
        for (int k : seq) {
            t.insert(k, k);
            RBTree.ValidationResult r = t.validate();
            if (!r.ok) throw new AssertionError("Insert broke RB properties: " + r.message);
        }
        System.out.println("All inserts keep tree valid: " + t.validate());

        System.out.print("Inorder: ");
        for (Map.Entry<Integer,Integer> e : t.inorder()) System.out.print(e.getKey() + " ");
        System.out.println();
    }
}
