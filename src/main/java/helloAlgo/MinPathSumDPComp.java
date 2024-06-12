package helloAlgo;

/* 最小路径和：空间优化后的动态规划 */
public class MinPathSumDPComp {
    public int minPathSumDPComp(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        // 初始化 dp 表
        int[] dp = new int[m];

        // 状态转移：首行
        dp[0] = grid[0][0];
        for (int j = 1; j < m; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }

        // 状态转移：其余行
        for (int i = 1; i < n; i++) {
            // 状态转移：首列
            dp[0] = dp[0] + grid[i][0];
            // 状态转移：其余列
            for (int j = 1; j < m; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }
        return dp[m - 1];
    }
}
