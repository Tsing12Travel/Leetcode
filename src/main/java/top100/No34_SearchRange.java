package top100;

import java.util.Arrays;

public class No34_SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int start = lowerBound(nums, target);
        // nums 中没有 target
        if (start == nums.length || nums[start] != target) return new int[]{-1, -1};
        // 如果 start 存在，那么 end 必定存在
        int end = lowerBound(nums, target + 1) - 1;

        return new int[]{start, end};
    }

    // lowerBound 返回最小的满足 nums[i] >= target 的 i
    private int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        No34_SearchRange sr = new No34_SearchRange();
        System.out.println(Arrays.toString(sr.searchRange(nums, 8)));
    }
}
