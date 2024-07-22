package top100;

import java.util.Arrays;

public class No416_CanPartition {
    // 记忆化搜索
    // 定义 dfs(i, j) 表示能否从 nums[0] 到 nums[i] 中选出一个和恰好等于 j 的子序列
    /* 考虑 nums[i] 选或不选：
    选：问题变成能否从 nums[0] 到 nums[i − 1] 中选出一个和恰好等于 j − nums[i] 的子序列，即 dfs(i − 1, j − nums[i])。
    不选：问题变成能否从 nums[0] 到 nums[i − 1] 中选出一个和恰好等于 j 的子序列，即 dfs(i − 1, j)。

    这两个只要有一个成立，dfs(i, j) 就是 true，即 dfs(i, j) = dfs(i − 1, j − nums[i]) || dfs(i − 1, j)
    代码实现时，可以只在 j ≥ nums[i] 时才调用 dfs(i − 1, j − nums[i])，因为任何子序列的和都不会是负的

    递归边界：dfs(−1, 0) = true, dfs(−1, >0) = false
    递归入口：dfs(n − 1, s/2)，即答案 */
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
