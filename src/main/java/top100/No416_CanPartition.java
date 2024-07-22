package top100;

import java.util.Arrays;

public class No416_CanPartition {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;

        int len = nums.length;
        int[][] memo = new int[len][sum / 2 + 2];
        for (int[] row : memo) {
            Arrays.fill(row, -1);  // -1 表示没有计算过
        }

        return dfs(len - 1, sum / 2, nums, memo);
    }

    public boolean dfs(int i, int left, int[] nums, int[][] memo) {
        if (i < 0) {
            return left == 0;
        }

        if (memo[i][left] != -1) {  // 之前计算过
            return memo[i][left] == 1;
        }

        boolean res = left >= nums[i] && dfs(i - 1, left - nums[i], nums, memo) || dfs(i - 1, left, nums, memo);
        memo[i][left] = res ? 1 : 0;  // 记忆化
        return res;
    }
}
