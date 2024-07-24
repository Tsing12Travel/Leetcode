package top100;

import java.util.Arrays;

public class No31_NextPermutation {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len <= 1) return;

        int i = len - 2;
        int j = len - 1;
        int k = len - 1;

        while (i >= 0 && nums[i] >= nums[j]) {
            i--;
            j--;
        }

        if (i >= 0) {
            while (nums[i] >= nums[k]) {
                k--;
            }

            swap(nums, i, k);
        }

        reverse(nums, j, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }


    public static void main(String[] args) {
        // int[] nums = {1, 2, 3};
        int[] nums = {3, 2, 1};
        // int[] nums = {1, 1, 5};
        No31_NextPermutation sol = new No31_NextPermutation();
        sol.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
