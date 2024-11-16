package top100;

/* 128.最长连续序列 */
// 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
// 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 思路：每个数都判断一次这个数是不是连续序列的开头那个数：用哈希表查找这个数前面一个数是否存在，即 num-1 在序列中是否存在。存在那这个数肯定不是开头，直接跳过
public class No128_LongestConsecutive {
    public static int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 1;

        for (int num : num_set) {
            // 检查 set 中是否存在比当前 num 小 1 的数
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // 以当前数为首的连续序列长度
                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    public static int longestConsecutive2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int tempLength = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            } else if (nums[i] == nums[i - 1] + 1) {
                tempLength++;
            } else {
                tempLength = 1;
            }
            res = Math.max(res, tempLength);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
    }
}
