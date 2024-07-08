package top100;

import java.util.Arrays;

public class No239_MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {}


    // 暴力解法
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k <= 0) return new int[0];

        int[] res = new int[n - k + 1];

        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                if (nums[j] > max) max = nums[j];
            }
            res[i] = max;
        }

        return res;
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        No239_MaxSlidingWindow slidingWindow = new No239_MaxSlidingWindow();
        int[] res = slidingWindow.maxSlidingWindow2(nums, k);
        System.out.println(Arrays.toString(res));
    }
}
