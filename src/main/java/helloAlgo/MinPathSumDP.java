package helloAlgo;

public class MinPathSumDP {
    public int minPathSumDP(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        // 初始化 dp 表
        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];

        // 状态转移：首行
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // 状态转移：首列
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // 状态转移：其余行和列
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }
}
