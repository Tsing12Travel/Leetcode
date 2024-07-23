package top100;

import java.util.Arrays;

public class No62_UniquePaths {
    // 用 f(i, j) 表示从左上角走到 (i, j) 的路径数量，其中 i 和 j 的范围分别是 [0, m) 和 [0, n)
    // f[i][j] = f[i - 1][j] + f[i][j - 1]
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];

        // 边界条件：第一行和第一列均只有一种方法到达。注意 f[0][0] = 1，即左上角到左上角(也认为)有一种方法
        Arrays.fill(f[0], 1);  // 将第一行填充为 1
        for (int i = 1; i < m; i++) {  // 将第一列填充为 1
            f[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }

        return f[m - 1][n - 1];
    }


    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        No62_UniquePaths sol = new No62_UniquePaths();
        System.out.println(sol.uniquePaths(m, n));
    }
}
