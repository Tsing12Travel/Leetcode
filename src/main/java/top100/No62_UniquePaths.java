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


    // 由于 f(i, j) 仅与第 i 行和第 i − 1 行的状态有关，因此我们可以使用滚动数组代替代码中的二维数组，使空间复杂度降低为 O(n)
    public int uniquePaths2(int m, int n) {
        int[] f = new int[n];
        Arrays.fill(f, 1);  // 左上角 f[0] = 1(自己到自己有一种方法)，故到其他位置至少有一种方法，填充 1

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[j] += f[j - 1];
            }
        }

        return f[n - 1];
    }


    // 组合数学
    // 从左上角到右下角的过程中，我们需要移动 m + n − 2 次，其中有 m − 1 次向下移动，n − 1 次向右移动。
    // 因此路径的总数，就等于从 m + n − 2 次移动中选择 m − 1 次向下移动的方案数 -> C(m − 1, m + n − 2)
    public int uniquePaths3(int m, int n) {
        long ans = 1;

        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }

        return (int) ans;
    }


    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        No62_UniquePaths sol = new No62_UniquePaths();
        System.out.println(sol.uniquePaths3(m, n));
    }
}
