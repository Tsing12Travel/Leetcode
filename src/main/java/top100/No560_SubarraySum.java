package top100;

import java.util.HashMap;
import java.util.Map;

public class No560_SubarraySum {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int len = nums.length;

        for (int left = 0; left < len; left++) {
            int sum = 0;
            // 区间里可能会有一些互相抵销的元素
            for (int right = left; right < len; right++) {
                sum += nums[right];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /* 判断 preSumFreq.containsKey(preSum - k) 的原因是当我们找到一个前缀和为 preSum - k 时，
    说明从之前的某个位置到当前位置的子数组和为 k。若当前遍历到位置 i，前缀和为 preSum，我们要找的是和为 k 的子数组。
    如果存在一个位置 j（j < i），其前缀和为 preSum - k，那么从位置 j 到位置 i 的子数组和为 k */
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        int preSum = 0;
        // key 前缀和，value (key 对应的)前缀和个数
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumFreq.put(0, 1);

        for (int num : nums) {
            preSum += num;
            // 先获得前缀和为 preSum - k 的个数，加到计数变量里
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }

            // 然后维护 preSumFreq 的定义
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        No560_SubarraySum solution = new No560_SubarraySum();
        int[] nums = {1, 1, 2, 2, 3, 3, 4};
        System.out.println(solution.subarraySum2(nums, 4));
    }
}
