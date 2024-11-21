package interview;

import java.util.HashSet;
import java.util.Set;

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


    public static void main(String[] args) {
        // int[] nums = {100, 4, 200, 1, 3, 2};
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(No128_LongestConsecutive.longestConsecutive(nums));
    }
}
