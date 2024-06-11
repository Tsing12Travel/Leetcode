package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 15.三数之和 */
// 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k
// 同时还满足 nums[i] + nums[j] + nums[k] == 0
// 请你返回所有和为 0 且不重复的三元组。
// 注意：答案中不可以包含重复的三元组。

// 思路：固定一个值，然后使用双指针求解
public class No15_ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int k = 0; k < nums.length - 2; k++) {
            // 如果最小的数大于 0 则不可能三个数的和为 0, 直接退出
            if (nums[k] > 0) break;

            // 如果和上一个数相同，则有重复解。k > 0是因为可能 k = 0 时去重出现异常
            if (k > 0 && nums[k] == nums[k - 1]) continue;

            // 取另外两个数，判断三数之和是否为 0
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum < 0) {  // 如果三数之和小于 0，则说明中间数 nums[i] 太小了，i 向右移，同时去重
                    while (i < j && nums[i] == nums[++i]) ;  // 注意 ++i 先自增，再判断二者的值，(符合 i < j 的情况下)两个数的索引相差 1
                } else if (sum > 0) {  // 如果三数之和大于 0，则说明最大数 nums[j] 太大了，j 向左移，同时去重
                    while (i < j && nums[j] == nums[--j]) ;
                } else {
                    // 如果三数之和等于0, 则把三数添加到答案
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));

                    // nums[i] 增大, nums[j] 减小, 依旧可能满足三数之和为 0，继续遍历去重
                    while (i < j && nums[i] == nums[++i]) ;
                    while (i < j && nums[j] == nums[--j]) ;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        // int[] nums = new int[]{0, 1, 1};
        // int[] nums = new int[]{0, 0, 0};
        System.out.println(threeSum(nums));
    }
}
