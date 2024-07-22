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


    // 1:1 翻译成递推
    // [i][j] 的定义和 dfs(i, j) 的定义是一样的，都表示能否从 nums[0] 到 nums[i] 中选出一个和恰好等于 j 的子序列
    /* 相应的递推式（状态转移方程）也和 dfs 一样：f[i][j] = f[i − 1][j − nums[i]] || f[i − 1][j]
    但是，这种定义方式没有状态能表示递归边界，即 i = −1 的情况
    解决办法：在二维数组 f 的最上边插入一排状态，那么其余状态全部向下偏移一位，把 f[i] 改为 f[i + 1]，把 f[i − 1] 改为 f[i]
    修改后 f[i + 1][j] 表示能否从 nums[0] 到 nums[i] 中选出一个和为 j 的子序列。f[0] 对应递归边界。
    修改后的递推式为：f[i + 1][j] = f[i][j − nums[i]] || f[i][j]

    初始值 f[0][0] = true，翻译自递归边界 dfs(−1, 0) = true。其余值初始化成 false
    答案为 f[n][s/2]，翻译自递归入口 dfs(n − 1, s/2) */
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;

        sum /= 2;  // 注意这里把 s 减半了
        int len = nums.length;
        boolean[][] f = new boolean[len + 1][sum + 1];
        f[0][0] = true;

        for (int i = 0; i < len; i++) {
            int x = nums[i];
            for (int j = 0; j <= sum; j++) {
                f[i + 1][j] = j >= x && f[i][j - x] || f[i][j];
            }
        }

        return f[len][sum];
    }


    public static void main(String[] args) {
        // int[] nums = new int[]{1, 5, 11, 5};
        int[] nums = new int[]{2, 1, 1, 2};
        No416_CanPartition sol = new No416_CanPartition();
        System.out.println(sol.canPartition2(nums));
    }
}
