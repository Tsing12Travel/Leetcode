package top100;

import java.util.Arrays;

public class No279_NumSquares {
    /*
    把 1, 4, 9, 16,⋯ 这些完全平方数视作物品体积，物品价值都是 1。由于每个数（物品）选的次数没有限制，所以本题是一个标准的完全背包问题
    定义 dfs(i, j) 表示从前 i 个完全平方数中选一些数（可以重复选），满足元素和恰好等于 j，最少要选的数字个数

    考虑第 i 个完全平方数 i^2 选或不选：
    不选：问题变成从前 i−1 个完全平方数中选一些数（可以重复选），满足元素和恰好等于 j，最少要选的数字个数，即 dfs(i, j) = dfs(i − 1, j)
    选：前提是 j ≥ i^2 。问题变成从前 i 个完全平方数中选一些数（可以重复选），满足元素和恰好等于 j − i^2 ，最少要选的数字个数，
    即 dfs(i, j) = dfs(i, j − i^2) + 1。注意这里是 i 而不是 i−1，因为我们可以继续选第 i 个完全平方数

    这两种情况取最小值，就得到了 dfs(i, j)，即
    dfs(i, j) = dfs(i − 1, j), j < i^2
    dfs(i, j) = min(dfs(i − 1, j), dfs(i, j − i^2) + 1), j >= i^2
    递归边界：dfs(0, 0) = 0，因为没有数可以选了，且要得到的数等于 0，那么答案为 0。如果 j > 0，那么 dfs(0, j) = ∞，这里用 ∞ 表示不合法的状态，
    从而保证上式中的 min 取到合法的状态。注意本题是一定有解的，因为 1 是完全平方数
    递归入口：由于 i^2 ≤n，所以 i ≤ ⌊Math.sqrt(n)⌋，所以递归入口为 dfs(⌊Math.sqrt(n)⌋, n)，也就是答案
    */
    private static final int[][] memo = new int[101][10001];

    static {
        for (int[] row : memo) {
            Arrays.fill(row, -1);  // -1 表示没有计算过
        }
    }

    public int numSquares(int n) {
        return dfs((int) Math.sqrt(n), n);
    }

    private int dfs(int i, int left) {
        if (i == 0) {
            return left == 0 ? 0 : Integer.MAX_VALUE;
        }

        if (memo[i][left] != -1) {  // 之前计算过
            return memo[i][left];
        }

        if (left < i * i) {  // 只能不选
            return dfs(i - 1, left);
        }

        memo[i][left] = Math.min(dfs(i - 1, left), dfs(i, left - i * i) + 1);  // 不选 vs 选
        return memo[i][left];
    }


    // 1:1 翻译成递推
    private static final int N = 100;
    private static final int[][] f = new int[101][N + 1];

    static {
        Arrays.fill(f[0], Integer.MAX_VALUE);
        f[0][0] = 0;
        for (int i = 1; i * i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (j < i * i) {  // 只能不选
                    f[i][j] = f[i - 1][j];
                } else {
                    f[i][j] = Math.min(f[i - 1][j], f[i][j - i * i] + 1);  // 不选 vs 选
                }
            }
        }
    }

    public int numSquares2(int n) {
        return f[(int) Math.sqrt(n)][n];  // 也可以写 f[100][n]
    }


    // 空间优化
    private static final int NN = 10000;
    private static final int[][] dp = new int[101][NN + 1];

    static {
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;
        for (int i = 1; i * i <= NN; i++) {
            for (int j = 0; j <= NN; j++) {
                if (j < i * i) {  // 只能不选
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - i * i] + 1);  // 不选 vs 选
                }
            }
        }
    }

    public int numSquares3(int n) {
        return dp[(int) Math.sqrt(n)][n];  // 也可以写 dp[100][n]
    }


    // 动态规划
    public int numSquares4(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }


    // 数学:「四平方和定理」
    /* 四平方和定理证明了任意一个正整数都可以被表示为至多四个正整数的平方和。这给出了本题的答案的上界。
    同时四平方和定理包含了一个更强的结论：当且仅当 n 不等于 4^k × (8m + 7) 时，n 可以被表示为至多三个正整数的平方和。
    因此，当 n 不等于 4^k × (8m + 7)，n 只能被表示为四个正整数的平方和。此时我们可以直接返回 4 */
    public int numSquares5(int n) {
        if (isPerfectSquare(n)) {
            return 1;
        }

        if (checkAnswer4(n)) {
            return 4;
        }

        for (int i = 1; i * i <= n; i++) {
            int j = n - i * i;
            if (isPerfectSquare(j)) {
                return 2;
            }
        }

        return 3;
    }

    // 判断是否为完全平方数
    public boolean isPerfectSquare(int x) {
        int y = (int) Math.sqrt(x);
        return y * y == x;
    }

    // 判断是否能表示为 4^k * (8m + 7)
    public boolean checkAnswer4(int x) {
        while (x % 4 == 0) {
            x /= 4;
        }
        return x % 8 == 7;
    }


    public static void main(String[] args) {
        int n = 12;
        No279_NumSquares solution = new No279_NumSquares();
        System.out.println(solution.numSquares5(n));
    }
}
