package top100;

import java.util.Arrays;

public class No322_CoinChange {
    // dfs(i, amount) = dfs(i - 1, amount) + min(dfs(i - 1, amount), dfs(i, amount - coins[i]) + 1)
    private int[] coins;
    private int[][] memo;

    public int coinChange(int[] coins, int amount) {
        this.coins = coins;
        int len = coins.length;
        memo = new int[len][amount + 1];

        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有访问过
        }

        int res = dfs(len - 1, amount);
        return res < Integer.MAX_VALUE / 2 ? res : -1;
    }

    private int dfs(int i, int left) {
        if (i < 0) {
            return left == 0 ? 0 : Integer.MAX_VALUE / 2;  // 除 2 防止下面 + 1 溢出
        }

        if (memo[i][left] != -1) {  // 之前计算过
            return memo[i][left];
        }

        if (left < coins[i]) {
            return memo[i][left] = dfs(i - 1, left);
        }

        return memo[i][left] = Math.min(dfs(i - 1, left), dfs(i, left - coins[i]) + 1);
    }


    // 1:1 翻译成递推
    public int coinChange2(int[] coins, int amount) {
        int n = coins.length;
        int[][] f = new int[n + 1][amount + 1];
        Arrays.fill(f[0], Integer.MAX_VALUE / 2); // 除 2 防止下面 + 1 溢出
        f[0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int c = 0; c <= amount; c++) {
                if (c < coins[i]) f[i + 1][c] = f[i][c];
                else f[i + 1][c] = Math.min(f[i][c], f[i + 1][c - coins[i]] + 1);
            }
        }

        int ans = f[n][amount];
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }


    // 空间优化：两个数组（滚动数组）
    public int coinChange3(int[] coins, int amount) {
        int n = coins.length;
        int[][] f = new int[2][amount + 1];
        Arrays.fill(f[0], Integer.MAX_VALUE / 2);
        f[0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int c = 0; c <= amount; c++) {
                if (c < coins[i]) f[(i + 1) % 2][c] = f[i % 2][c];
                else f[(i + 1) % 2][c] = Math.min(f[i % 2][c], f[(i + 1) % 2][c - coins[i]] + 1);
            }
        }

        int ans = f[n % 2][amount];
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }


    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        No322_CoinChange sol = new No322_CoinChange();
        System.out.println(sol.coinChange(coins, amount));
    }
}
