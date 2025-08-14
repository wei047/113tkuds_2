import java.util.Map;

public class RBTreeIteratorExercise {
    public static void main(String[] args) {
        RBTree<Integer, String> t = new RBTree<>();
        int[] keys = {20, 10, 30, 5, 15, 25, 35, 1, 12, 27, 40};
        for (int k : keys) t.insert(k, "v" + k);

        System.out.print("Inorder: ");
        for (Map.Entry<Integer,String> e : t.inorder()) System.out.print(e.getKey() + " ");
        System.out.println();

        System.out.print("Reverse: ");
        for (Map.Entry<Integer,String> e : t.reverse()) System.out.print(e.getKey() + " ");
        System.out.println();

        System.out.print("Range [12,30]: ");
        for (Map.Entry<Integer,String> e : t.range(12, 30, true, true)) System.out.print(e.getKey() + " ");
        System.out.println();
    }
}
