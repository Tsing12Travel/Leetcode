package test;

public class LowerBound {
    public int lowerBound(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) return 0;

        int left = 0;
        int right = len - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < k) {
                left = mid + 1;
            } else {  // nums[mid] = k 时，右边界依然左移，直到越界，即 left > right，故等于 k 的数的左边界为 left
                right = mid - 1;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 3, 3, 3, 3, 5, 5, 6, 7};
        LowerBound lb = new LowerBound();
        System.out.println(lb.lowerBound(nums, 4));  // 找不到 k 时，会返回比 k 小的前一个数的(最后一个)下标
    }
}
