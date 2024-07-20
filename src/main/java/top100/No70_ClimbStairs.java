package top100;

public class No70_ClimbStairs {
    // 时间复杂度为 2^n
    public int climbStairs(int n) {
        return dfs(n);
    }

    private int dfs(int n) {
        if (n <= 1) return 1;  // 递归边界
        return dfs(n - 1) + dfs(n - 2);
    }


    // 递归 + 记录返回值 = 记忆化搜索
    public int climbStairs2(int n) {
        int[] dp = new int[n + 1];
        return dfs2(n, dp);
    }

    private int dfs2(int i, int[] dp) {
        if (i <= 1) return 1;  // 递归边界
        if (dp[i] != 0) return dp[i];  // 之前计算过

        dp[i] = dfs2(i - 1, dp) + dfs2(i - 2, dp);  // 记忆化
        return dp[i];
    }


    public static void main(String[] args) {
        int n = 46;
        No70_ClimbStairs solution = new No70_ClimbStairs();
        System.out.println(solution.climbStairs2(n));
    }
}
