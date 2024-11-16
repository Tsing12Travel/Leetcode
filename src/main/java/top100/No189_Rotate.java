package top100;

import java.util.Arrays;

public class No189_Rotate {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] newNums = new int[len];

        for (int i = 0; i < len; i++) {
            newNums[(i + k) % len] = nums[i];
        }

        System.arraycopy(newNums, 0, nums, 0, len);
    }

    public void rotate2(int[] nums, int k) {
        k = k % nums.length;  // 重要！无论 k 是否大于数组长度，实际移动的长度只有 k%nums.length
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
