import java.util.Map;

public class RBDeleteFixupExercise {
    public static void main(String[] args) {
        RBTree<Integer, Integer> t = new RBTree<>();
        int[] seq = {30, 15, 50, 10, 20, 40, 60, 5, 12, 18, 25, 35, 45, 55, 70};
        for (int k : seq) t.insert(k, k);

        System.out.println("Before deletions: " + t.validate());

        int[] del = {10, 12, 15, 18, 20, 25, 30};
        for (int k : del) {
            boolean ok = t.delete(k);
            if (!ok) throw new AssertionError("Key not found: " + k);
            RBTree.ValidationResult r = t.validate();
            if (!r.ok) throw new AssertionError("Delete broke RB properties at key " + k + ": " + r.message);
        }
        System.out.println("All deletions keep tree valid: " + t.validate());

        System.out.print("Reverse: ");
        for (Map.Entry<Integer,Integer> e : t.reverse()) System.out.print(e.getKey() + " ");
        System.out.println();
    }
}
