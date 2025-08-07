public class MatrixCalculator {
    public static void main(String[] args) {
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{5, 6}, {7, 8}};
        int[][] sum = new int[2][2];
        int[][] product = new int[2][2];
        int[][] transpose = new int[2][2];
        int max = a[0][0];
        int min = a[0][0];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                sum[i][j] = a[i][j] + b[i][j];
                for (int k = 0; k < 2; k++) {
                    product[i][j] += a[i][k] * b[k][j];
                }
                if (a[i][j] > max) max = a[i][j];
                if (a[i][j] < min) min = a[i][j];
                transpose[j][i] = a[i][j];
            }
        }
        System.out.println("加法結果:");
        printMatrix(sum);
        System.out.println("乘法結果:");
        printMatrix(product);
        System.out.println("轉置結果:");
        printMatrix(transpose);
        System.out.println("最大值: " + max);
        System.out.println("最小值: " + min);
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}