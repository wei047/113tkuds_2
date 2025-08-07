import java.util.*;

public class RecursiveTreePreview {
    public static int countFiles(Map<String, Object> folder) {
        int count = 0;
        for (Object value : folder.values()) {
            if (value instanceof Map) count += countFiles((Map<String, Object>) value);
            else count++;
        }
        return count;
    }

    public static void printMenu(List<Object> menu, int depth) {
        for (Object item : menu) {
            if (item instanceof String) {
                System.out.println("  ".repeat(depth) + item);
            } else if (item instanceof List) {
                printMenu((List<Object>) item, depth + 1);
            }
        }
    }

    public static List<Object> flatten(List<Object> nested) {
        List<Object> result = new ArrayList<>();
        for (Object item : nested) {
            if (item instanceof List) result.addAll(flatten((List<Object>) item));
            else result.add(item);
        }
        return result;
    }

    public static int maxDepth(List<Object> nested) {
        int depth = 1;
        for (Object item : nested) {
            if (item instanceof List) {
                depth = Math.max(depth, 1 + maxDepth((List<Object>) item));
            }
        }
        return depth;
    }
}