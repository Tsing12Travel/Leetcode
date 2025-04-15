package interview;

import java.util.Arrays;

// dp[]
public class Coins {
    public int coins(int[] coins, int amount) {
        if (amount <= 0) return 0;

        int res = 0;
        Arrays.sort(coins);
        dfs(coins, res, amount);
        return res;
    }

    private void dfs(int[] coins,int res, int left) {
        if (left == 0) return;

        for (int coin : coins) {

        }
    }
}
