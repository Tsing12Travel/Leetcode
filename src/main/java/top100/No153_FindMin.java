package top100;

public class No153_FindMin {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] <= nums[nums.length - 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }


    public int findMin2(int[] nums) {
        int left = 0, right = nums.length - 1;

        // 注意这里的循环条件是 left < right，而不是 left <= right
        // 二分查找的目的是通过不断缩小 left 和 right 之间的区间，直到它们重合。left 和 right 重合时，表示区间中只剩下一个元素，这个元素就是最小值。因此，在搜索时，left < right 是确保搜索区间有意义的条件
        // 当 left == right 时，搜索区间只剩下一个元素，表示我们已经缩小到了最小值的位置，因此循环应该退出。这意味着当前的 nums[left] 就是数组的最小元素
        // 如果条件改成 left <= right，当 left == right 时还会进入循环，而此时 mid 计算为 left，而 nums[mid] 就是 nums[left]，这会导致不必要的重复计算
        // 使用 left <= right 的话，在某些情况下（如极端情况），可能会导致左右边界不断地进行重复计算，使得循环无法有效终止。因为当 left == right 时，我们已经找到了唯一解，再进行迭代是无意义的
        while (left < right) {
            int mid = left + (right - left) / 2;

            // 如果中间元素大于右边界元素，最小值在右半部分
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                // 否则，最小值在左半部分或mid就是最小值
                right = mid;
            }
        }

        // 最终left和right会收敛到最小值的位置
        return nums[left];
    }


    public static void main(String[] args) {
        No153_FindMin n = new No153_FindMin();
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(n.findMin(nums));
    }
}
