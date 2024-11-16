package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 给定一个数组，寻找左边都比它小，右边都比它大的元素
// 思路：一个数要比它左边的所有数要大，比右边的所有数要小，那么它必定大于左边元素的最大值，同时小于右边元素的最小值
// 2024.08.08 shopee 一面笔试
public class Shopee {
    public int[] shopee(int[] nums) {
        int len = nums.length;
        if (len == 0) return nums;
        List<Integer> res = new ArrayList<>();

        int[] max_array = new int[len];
        max_array[0] = nums[0];

        int[] min_array = new int[len];
        min_array[len - 1] = nums[len - 1];

        int max = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] > max) max = nums[i];
            max_array[i] = max;
        }

        int min = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] < min) min = nums[i];
            min_array[i] = min;
        }

        for (int i = 1; i < len - 1; i++) {  // 此处默认左右边界不会是答案
            if (nums[i] > max_array[i - 1] && nums[i] < min_array[i + 1]) res.add(nums[i]);
        }

        int[] res_array = new int[res.size()];
        for (int i = 0; i < res.size(); i++) res_array[i] = res.get(i);

        return res_array;
    }

    public static void main(String[] args) {
        // int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // int[] nums = new int[]{1, 2, 3, 1, 4, 5, 7, 6};
        int[] nums = new int[]{1, 2, 3, 1, 4, 0, 4, 5};
        Shopee sol = new Shopee();
        System.out.println(Arrays.toString(sol.shopee(nums)));
    }
}
