package interview;

// 2025.04.15 京东（交易链路后端）一面
public class No322_CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            int min = amount + 1;

            for (int x : coins) {  // 遍历硬币的可选额
                if (i - x >= 0) {
                    min = Math.min(min, dp[i - x]);
                }
            }

            dp[i] = min + 1;
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }


    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        No322_CoinChange No322 = new No322_CoinChange();
        System.out.println(No322.coinChange(coins, amount));
    }
}
