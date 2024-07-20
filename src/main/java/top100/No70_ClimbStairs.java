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


    // 1:1 翻译成递推
    public int climbStairs3(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    // 空间优化
    /* 观察状态转移方程，发现一旦算出 f[i]，那么 f[i−2] 及其左边的状态就永远不会用到了。
    这意味着每次循环，只需要知道「上一个状态」和「上上一个状态」的 f 值是多少，分别记作 f0、f1，它俩的初始值均为 1，对应着 f[1] 和 f[0]
    每次循环计算出新的状态，即 f = f0 + f1，那么对下一轮循环来说有：
    「上上一个状态」就是 f1，更新 f0 = f1
    「上一个状态」就是 f，更新 f1 = f
    最后答案为 f1，因为最后一轮循环算出的 f 赋给了 f1 */
    public int climbStairs4(int n) {
        int f0 = 1, f1 = 1;
        for (int i = 2; i <= n; i++) {
            int f = f0 + f1;
            f0 = f1;
            f1 = f;
        }
        return f1;
    }


    public static void main(String[] args) {
        int n = 45;
        No70_ClimbStairs solution = new No70_ClimbStairs();
        System.out.println(solution.climbStairs4(n));
    }
}
