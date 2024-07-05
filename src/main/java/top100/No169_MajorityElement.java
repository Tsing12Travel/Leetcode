package top100;

import java.util.Arrays;

public class No169_MajorityElement {
    // 摩尔投票。本题该方法的时间复杂度为 O(N)，空间复杂度为 O(1)
    public int majorityElement(int[] nums) {
        int vote = 0;
        int candidate = 0;

        for (int num : nums) {
            if (vote == 0) candidate = num;

            // 下面的代码可以写成 vote += num == candidate ? 1 : -1;
            if (num == candidate) {
                vote++;
            } else {
                vote--;
            }
        }
        return candidate;
    }


    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        int mid = nums.length / 2;
        return nums[mid];
    }
}
