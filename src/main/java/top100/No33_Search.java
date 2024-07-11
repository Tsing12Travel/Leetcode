package top100;

public class No33_Search {
    public int search(int[] nums, int target) {
        int len = nums.length;

        if (len == 0) return -1;
        if (len == 1) return nums[0] == target ? 0 : -1;

        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid;

            // 将数组从中间分开成左右两部分的时候，一定有一部分的数组是有序的
            if (nums[0] <= nums[mid]) {  // 左侧有序
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {  // 右侧有序
                if (nums[mid] < target && target <= nums[len - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        No33_Search solution = new No33_Search();
//        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int[] nums = new int[]{5, 1, 3};
        System.out.println(solution.search(nums, 3));
    }
}
