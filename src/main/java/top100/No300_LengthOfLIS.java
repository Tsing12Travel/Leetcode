package top100;

public class No300_LengthOfLIS {
    private int[] nums;
    private int[] dp;

    // 记忆化搜索。dfs(i) 表示以 nums[i] 结尾的最长递增子序列（LIS）的长度
    public int lengthOfLIS(int[] nums) {
        this.nums = nums;
        int len = nums.length;
        dp = new int[len];  // 本题可以初始化成 0，表示没有计算过
        int res = 0;

        for (int i = 0; i < len; i++) {
            res = Math.max(res, dfs(i));
        }

        return res;
    }

    private int dfs(int i) {
        if (dp[i] != 0) return dp[i];  // 之前计算过

        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                dp[i] = Math.max(dp[i], dfs(j));
            }
        }

        return ++dp[i];
    }


    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        No300_LengthOfLIS sol = new No300_LengthOfLIS();
        System.out.println(sol.lengthOfLIS(nums));
    }
}
