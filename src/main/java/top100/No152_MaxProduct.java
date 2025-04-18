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


    // 也可设置两个状态数组，语义会更清晰一些
    public int maxProduct2(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;

        // 状态定义：以索引 i 结尾
        // 思考清楚一种特例：[2, -1, 3]，前面乘起来是负数的话，倒不如另起炉灶
        int[] minDp = new int[len];
        int[] maxDp = new int[len];

        minDp[0] = nums[0];
        maxDp[0] = nums[0];

        for (int i = 1; i < len; i++) {
            if (nums[i] >= 0) {
                maxDp[i] = Math.max(maxDp[i - 1] * nums[i], nums[i]);
                minDp[i] = Math.min(minDp[i - 1] * nums[i], nums[i]);
            } else {
                maxDp[i] = Math.max(minDp[i - 1] * nums[i], nums[i]);
                minDp[i] = Math.min(maxDp[i - 1] * nums[i], nums[i]);
            }
        }

        int res = nums[0];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, Math.max(maxDp[i], minDp[i]));
        }
        return res;
    }


    // 因为每一个状态只与前一个状态有关，可以使用「滚动变量」技巧，使用常数个变量完成这道问题
    public int maxProduct3(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;

        int preMax = nums[0];
        int preMin = nums[0];
        int curMax;
        int curMin;

        int res = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] >= 0) {
                curMax = Math.max(nums[i] * preMax, nums[i]);
                curMin = Math.min(nums[i] * preMin, nums[i]);
            } else {
                curMax = Math.max(nums[i] * preMin, nums[i]);
                curMin = Math.min(nums[i] * preMax, nums[i]);
            }
            res = Math.max(res, curMax);

            // 滚动变量
            preMax = curMax;
            preMin = curMin;
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        // int[] nums = {-2, 0, -1};
        No152_MaxProduct sol = new No152_MaxProduct();
        System.out.println(sol.maxProduct3(nums));
    }
}
