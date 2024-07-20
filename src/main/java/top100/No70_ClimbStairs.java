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
    /* 注意：memo 数组的初始值一定不能等于要记忆化的值！
    例如初始值设置为 0，并且要记忆化的 dfs(i) 也等于 0，那就没法判断 0 到底表示第一次遇到这个状态，还是表示之前遇到过了，从而导致记忆化失效。
    一般把初始值设置为 −1。本题由于方案数均为正数，所以可以初始化成 0 */
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
        int n = 45;
        No70_ClimbStairs solution = new No70_ClimbStairs();
        System.out.println(solution.climbStairs2(n));
    }
}
