package top100;

public class No35_SearchInsert {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

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


    public int searchInsert2(int[] nums, int target) {
        // 不用判断数组为空，因为题目最后给出的数据范围说数组不为空
        int len = nums.length;
        // 特殊判断
        if (nums[len - 1] < target) {
            return len;
        }

        // 程序走到这里一定有 nums[len - 1] >= target，插入位置在区间 [0, len - 1]
        int left = 0;
        int right = len - 1;
        // 在区间 nums[left, right] 里查找第 1 个大于等于 target 的元素的下标
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                // 下一轮搜索的区间是 [mid + 1, right]
                left = mid + 1;
            } else {
                // 下一轮搜索的区间是 [left, mid]
                right = mid;
            }
        }
        return left;
    }
}

/*
二分查找，最后为什么要返回 left ？

穷尽且不重复所有可能.一共有以下4种可能：
1、目标值存在于数组中
2、目标值小于数组中所有元素
3、目标值大于数组中所有元素
4、目标值在数组的某个区间内，但又不等于数组任何一个值

第1种情况：如果目标值 target 存在于数组中，二分查找会在某一步 mid 恰好等于 target 并返回 mid。

第2种情况：如果 target 小于数组中所有元素，left 会最终指向数组的第一个位置，即 left == 0。
此时，nums[left] 是数组中的第一个元素，而 target 应插入的位置也是 left，即索引 0。

第3种情况：如果 target 大于数组中所有元素，left 会最终指向数组的末尾位置的下一个位置，即 left == nums.length。
此时，nums[left] 不存在，target 应插入的位置是数组末尾。

第4种情况：如果 target 在数组的某个区间内（比如介于两个元素之间），
当循环结束时，left 会指向第一个大于 target 的元素的位置，而 left - 1 会指向小于或等于 target 的元素。
*/
