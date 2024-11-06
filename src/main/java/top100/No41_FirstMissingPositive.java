package top100;

import java.util.Arrays;

public class No41_FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            // 满足在指定范围内且没有放在正确的位置上，才进行交换
            // 如：数字 3 应该放在索引为 2 的位置上
            // 数值为 i 的数映射到下标为 i - 1 的位置。原地哈希就相当于，让每个数字 n 都回到下标为 n - 1 的家里
            // nums[i] != nums[nums[i] - 1 不能写成 i != nums[i] - 1 => [1,1] 这种元素相同的数组会陷入死循环，导致超时
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // 都正确则返回数组长度 + 1
        return len + 1;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }


    public static void main(String[] args) {
        // int[] nums = new int[]{3, 4, -1, 1};
        // int[] nums = new int[]{7, 8, 9, 11, 12};
        // int[] nums = new int[]{7, 8, 9, 3, 12};
        int[] nums = new int[]{1, 1};
        No41_FirstMissingPositive sol = new No41_FirstMissingPositive();
        System.out.println(sol.firstMissingPositive(nums));
        System.out.println(Arrays.toString(nums));
    }
}
