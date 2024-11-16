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
            if (nums[mid] <= nums[len - 1]) {  // 右侧有序
                if (nums[mid] < target && target <= nums[len - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {  // 左侧有序
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }


    public int search2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 如果中间值就是目标值，直接返回
            if (nums[mid] == target) {
                return mid;
            }

            // 判断左半部分是否有序
            if (nums[left] <= nums[mid]) {
                // 如果目标值在左半部分的范围内
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else { // 否则，去右半部分继续查找
                    left = mid + 1;
                }
            } else { // 否则右半部分有序
                // 如果目标值在右半部分的范围内
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else { // 否则，去左半部分继续查找
                    right = mid - 1;
                }
            }
        }

        // 未找到目标值
        return -1;
    }


    public static void main(String[] args) {
        No33_Search solution = new No33_Search();
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
//        int[] nums = new int[]{5, 1, 3};
        System.out.println(solution.search(nums, 3));
    }
}
