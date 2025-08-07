import java.util.*;

class MultiNode {
    String val;
    List<MultiNode> children = new ArrayList<>();
    MultiNode(String val) { this.val = val; }
}

public class MultiWayTreeAndDecisionTree {

    public static void dfs(MultiNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        for (MultiNode child : node.children)
            dfs(child);
    }

    public static void bfs(MultiNode root) {
        if (root == null) return;
        Queue<MultiNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            MultiNode node = q.poll();
            System.out.print(node.val + " ");
            for (MultiNode child : node.children)
                q.offer(child);
        }
    }

    public static int height(MultiNode root) {
        if (root == null) return 0;
        int h = 0;
        for (MultiNode child : root.children)
            h = Math.max(h, height(child));
        return h + 1;
    }

    public static Map<String, Integer> degreeCount(MultiNode root) {
        Map<String, Integer> map = new HashMap<>();
        count(root, map);
        return map;
    }

    private static void count(MultiNode node, Map<String, Integer> map) {
        if (node == null) return;
        map.put(node.val, node.children.size());
        for (MultiNode child : node.children)
            count(child, map);
    }

    public static void decisionGame(MultiNode root, Scanner sc) {
        MultiNode curr = root;
        while (!curr.children.isEmpty()) {
            System.out.println(curr.val);
            String input = sc.nextLine();
            boolean matched = false;
            for (MultiNode child : curr.children) {
                if (child.val.toLowerCase().contains(input.toLowerCase())) {
                    curr = child;
                    matched = true;
                    break;
                }
            }
            if (!matched) break;
        }
        System.out.println("Result: " + curr.val);
    }

    public static void main(String[] args) {
        MultiNode root = new MultiNode("Is it a number > 50?");
        MultiNode yes = new MultiNode("Is it even?");
        MultiNode no = new MultiNode("Is it prime?");
        root.children.add(yes);
        root.children.add(no);
        yes.children.add(new MultiNode("It's 60"));
        yes.children.add(new MultiNode("It's 55"));
        no.children.add(new MultiNode("It's 47"));
        no.children.add(new MultiNode("It's 49"));

        dfs(root);
        System.out.println();
        bfs(root);
        System.out.println();
        System.out.println("Height: " + height(root));
        System.out.println("Degrees: " + degreeCount(root));
    }
}
