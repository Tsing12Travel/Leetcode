package top100;

import java.util.Arrays;

public class No198_Rob {
    // 最原始版本 f(n) = Math.max(f(n - 1), nums[n] + f(n - 2))
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        return dfs(len - 1, nums);
    }

    private int dfs(int len, int[] nums) {
        if (len < 0) return 0;
        return Math.max(nums[len] + dfs(len - 2, nums), dfs(len - 1, nums));
    }


    // 递归搜索 + 保存计算结果 = 记忆化搜索
    public int rob2(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        int[] dp = new int[len];
        Arrays.fill(dp, -1);  // -1 表示没有计算过

        // 定义全局变量 int[] dp, nums 可以让 dfs 参数更简洁(不用每次都携带参数)
        return dfs2(len - 1, dp, nums);  // 从最后一个房子开始思考
    }

    // dfs(i) 表示从 nums[0] 到 nums[i] 最多能偷多少
    private int dfs2(int i, int[] dp, int[] nums) {
        if (i < 0) return 0;  // 递归边界（没有房子）
        if (dp[i] != -1) return dp[i];  // 之前计算过

        int res = Math.max(nums[i] + dfs2(i - 2, dp, nums), dfs2(i - 1, dp, nums));
        dp[i] = res;  // 记忆化：保存计算结果
        return res;
    }


    // 1:1 翻译成递推
    public int rob3(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 2];

        for (int i = 0; i < len; i++) {
            dp[i + 2] = Math.max(nums[i] + dp[i], dp[i + 1]);
        }

        // 最后一间房子下标为 len - 1，由于 dp 数组最开始两个位置放置了 nums[0]、nums[1]
        // (相当于 dp 数组右移了两个下标，故 到最后一间房子所获价值的下标为 len - 1 + 2 = len + 1)
        return dp[len + 1];
    }


    public int rob4(int[] nums) {
        // 初始状态：在循环开始之前，f0 和 f1 都初始化为 0，这意味着如果没有房子，最大偷窃金额为 0
        int res = 0, f0 = 0, f1 = 0;

        for (int x : nums) {
            res = Math.max(f0 + x, f1);
            f0 = f1;
            f1 = res;
        }

        return res;
    }


    public int rob5(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        // 初始条件
        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);

        // 动态规划迭代
        for (int i = 2; i < n; i++) {
            int current = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }


    public static void main(String[] args) {
        // int[] nums = {1, 2, 3, 1};
        int[] nums = {2, 7, 9, 3, 1};
        No198_Rob rob = new No198_Rob();
        System.out.println(rob.rob4(nums));
    }
}

/*
如果想输出具体方案的话，可以从 f 数组的最后一项出发倒着走，只要发现 f[i + 2] 和 f[i] + nums[i] 是一样的，就表示选了 nums[i]
class Solution:
    def rob(self, nums: List[int]) -> int:
        f = [0] * (len(nums) + 2)
        for i, x in enumerate(nums):
            f[i + 2] = max(f[i + 1], f[i] + x)

        i = len(nums) - 1
        while i >= 0:
            if f[i + 2] == f[i] + nums[i]:  # 说明 f[i+2] 是从 f[i] 转移来的
                print(i, nums[i])
                i -= 2
            else:  # 说明 f[i+2] 是从 f[i+1] 转移来的
                i -= 1

        return f[-1]
*/
