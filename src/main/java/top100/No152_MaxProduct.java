package top100;

// 「滑动窗口」和「双指针」在本质上「暴力解法」的优化，使用两个变量同向移动或者相向移动的过程中，排除了很多不必要的区间，但依然不丢失最优解。这道题没有这样的特点
// 一般问最优解而不问最优解怎么来的，先往 dp 上靠，然后尝试一下什么广度优先遍历啊，贪心啥的
public class No152_MaxProduct {
    // dp[i] 以下标 i 结尾的连续子序列的乘积的最大值
    // 状态转移方程可能是：dp[i] = max(dp[i - 1] * nums[i], nums[i])
    /* 如果 dp[i - 1] 是负数，乘上 nums[i] 还是负数，倒不如另起炉灶。
    如果 nums[i] 是负数该怎么办呢？dp[i - 1] 是正数的时候，越乘越小，dp[i - 1] 是负数的时候，越乘越大，于是我们可能就需要记录一下负数的那个最小数
    遇到这样的问题，其实就在提示我们状态不够用了。因此，需要在原来的一维 dp 后面新增一个状态。

    针对这道题，第 2 维状态只需要两个：
    用 0 表示遍历的过程中得到的以 nums[i] 结尾的连续子序列的乘积的最小值；
    用 1 表示遍历的过程中得到的以 nums[i] 结尾的连续子序列的乘积的最大值

    dp[i][1] 表示：以 nums[i] 结尾的连续子序列的乘积的最大值；
    dp[i][0] 表示：以 nums[i] 结尾的连续子序列的乘积的最小值 */

    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        // 状态定义：以索引 i 结尾
        int[][] dp = new int[len][2];

        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        for (int i = 1; i < len; i++) {
            if (nums[i] >= 0) {
                dp[i][1] = Math.max(dp[i - 1][1] * nums[i], nums[i]);
                dp[i][0] = Math.min(dp[i - 1][0] * nums[i], nums[i]);
            } else {
                dp[i][1] = Math.max(dp[i - 1][0] * nums[i], nums[i]);
                dp[i][0] = Math.min(dp[i - 1][1] * nums[i], nums[i]);
            }
        }

        int res = dp[0][1];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, dp[i][1]);
        }
        return res;
    }


    public static void main(String[] args) {
        // int[] nums = {2, 3, -2, 4};
        int[] nums = {-2, 0, -1};
        No152_MaxProduct sol = new No152_MaxProduct();
        System.out.println(sol.maxProduct(nums));
    }
}
