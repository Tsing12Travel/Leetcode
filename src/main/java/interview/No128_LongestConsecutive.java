package interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


// 2024.11.21 腾讯 tRPC-#后端开发工程师#-Java方向 一面笔试
public class No128_LongestConsecutive {
    public static int longestConsecutive(int[] nums) {
        int len = nums.length;
        if (len <= 1) return len;

        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }

        int res = 1;
        for (int x : nums) {
            if (!set.contains(x - 1)) {
                int currNum = x;
                int currLen = 1;

                while (set.contains(currNum + 1)) {
                    currNum++;
                    currLen++;
                }

                res = Math.max(res, currLen);
            }
        }

        return res;
    }


    public static int longestConsecutive2(int[] nums) {
        int len = nums.length;
        if (len <= 1) return len;

        Arrays.sort(nums);
        int res = 1;
        int currLen = 1;
        for (int i = 0; i < len; i++) {
            if (i == 0 || nums[i] == nums[i - 1]) continue;

            if (nums[i] == nums[i - 1] + 1) {
                currLen++;
            } else {
                currLen = 1;
            }

            res = Math.max(res, currLen);
        }

        return res;
    }


    public static void main(String[] args) {
        // int[] nums = {100, 4, 200, 1, 3, 2};
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(No128_LongestConsecutive.longestConsecutive2(nums));
    }
}
