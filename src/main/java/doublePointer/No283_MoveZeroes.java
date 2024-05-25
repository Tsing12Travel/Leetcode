package doublePointer;

/*
283.移动零
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
请注意 ，必须在不复制数组的情况下原地对数组进行操作。
*/

// 思路：将非零元素移动到一起，再补零到与原数组相同长度
public class No283_MoveZeroes {
    public void moveZeroes(int[] nums) {
        // 去除 nums 中的所有 0，返回不含 0 的数组长度
        int p = removeElement(nums, 0);
        // 将 nums[p..] 的元素赋值为 0
        for (; p < nums.length; p++) {
            nums[p] = 0;
        }
    }

    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }

        int slow = 0;
        int fast = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}

/*
class Solution {
    public void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        while (slow < nums.length) {
            nums[slow] = 0;
            slow++;
        }
    }
}
*/
