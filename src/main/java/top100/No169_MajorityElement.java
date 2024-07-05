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


    public int majorityElement3(int[] nums) {  // 需要考虑不存在众数的写法
        int x = 0, votes = 0, count = 0;

        for (int num : nums) {
            if (votes == 0) x = num;
            votes += num == x ? 1 : -1;
        }

        // 验证 x 是否为众数
        for (int num : nums)
            if (num == x) count++;

        return count > nums.length / 2 ? x : 0;  // 当无众数时返回 0
    }
}
