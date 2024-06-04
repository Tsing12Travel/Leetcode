package top100;

import java.util.Arrays;

/* 283.移动零 */
// 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
// 请注意 ，必须在不复制数组的情况下原地对数组进行操作。

public class No283_MoveZeroes {
    public static int[] moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;

        while (fast < nums.length) {
            // nums[fast] 不为零时，将 nums[fast] 复制到 nums[slow]
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            // 无论 nums[fast] 是否为 0，索引 fast 都要 +1，即遍历下一个数组元素
            fast++;
        }

        // 对 slow 至数组末尾的元素补 0
        while (slow < nums.length) {
            nums[slow] = 0;
            slow++;
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        System.out.println(Arrays.toString(moveZeroes(nums)));
    }
}
