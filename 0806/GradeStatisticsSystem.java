public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};
        int total = 0;
        int max = scores[0];
        int min = scores[0];
        int[] gradeCount = new int[5];
        for (int score : scores) {
            total += score;
            if (score > max) max = score;
            if (score < min) min = score;
            if (score >= 90) gradeCount[0]++;
            else if (score >= 80) gradeCount[1]++;
            else if (score >= 70) gradeCount[2]++;
            else if (score >= 60) gradeCount[3]++;
            else gradeCount[4]++;
        }
        double avg = (double) total / scores.length;
        int aboveAvgCount = 0;
        for (int score : scores) {
            if (score > avg) aboveAvgCount++;
        }
        System.out.println("平均分數: " + avg);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);
        System.out.println("等第統計:");
        System.out.println("A (90-100): " + gradeCount[0]);
        System.out.println("B (80-89): " + gradeCount[1]);
        System.out.println("C (70-79): " + gradeCount[2]);
        System.out.println("D (60-69): " + gradeCount[3]);
        System.out.println("F (<60): " + gradeCount[4]);
        System.out.println("高於平均分的學生人數: " + aboveAvgCount);
        System.out.println("完整成績報表:");
        for (int i = 0; i < scores.length; i++) {
            System.out.println("學生 " + (i + 1) + ": " + scores[i]);
        }
    }
}
