package test;

public class UpperBound {
    public int upperBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;  // 初始化结果为 -1，表示未找到目标值

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                result = mid;  // 记录当前找到的目标值下标
                left = mid + 1;  // 继续搜索右侧部分，寻找最后一个出现的目标值
            } else if (nums[mid] < target) {
                left = mid + 1;  // 搜索右半部分
            } else {
                right = mid - 1;  // 搜索左半部分
            }
        }

        return result; // 返回找到的最后一个目标值的下标
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 3, 3, 3, 3, 5, 5, 6, 7};
        UpperBound ub = new UpperBound();
        System.out.println(ub.upperBound(nums, 8));  // 找不到 K 时，会返回比 k 小的前一个数的下标
    }
}
