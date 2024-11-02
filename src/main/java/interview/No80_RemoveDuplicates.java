package interview;

/*
80.删除有序数组中的重复项 II
给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */

// 2024.11.01 百度文心一言测试开发一面笔试题
public class No80_RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        // 慢指针，表示处理后数组的长度
        int slow = 0;

        for (int fast = 0; fast < nums.length; fast++) {
            // 保留条件：前两个元素保留，或者当前元素与前两个元素不同
            if (slow < 2 || nums[fast] != nums[slow - 2]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }

        return slow;
    }


    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        No80_RemoveDuplicates No80 = new No80_RemoveDuplicates();
        System.out.println(No80.removeDuplicates(nums));
    }
}
