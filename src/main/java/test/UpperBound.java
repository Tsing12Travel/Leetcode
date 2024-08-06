package test;

public class UpperBound {
    public int upperBound(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= k) {  // nums[mid] = k 时，左边界依然右移，直到越界，即 left > right，故等于 k 的数的右边界为 right
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;  // 注意返回值
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 3, 3, 3, 3, 5, 6, 7};
        UpperBound ub = new UpperBound();
        System.out.println(ub.upperBound(nums, 3));
    }
}
