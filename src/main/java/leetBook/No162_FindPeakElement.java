package leetBook;

public class No162_FindPeakElement {
    public int findPeakElement(int[] nums) {
        int left = 0;
        // 定义峰顶及其右侧为蓝色，左侧为红色。最后一个值必定在峰顶或峰顶右侧(即为蓝色)，从而需要染色的数组区间为 [0, len - 2]
        int right = nums.length - 2;

        while (left <= right) {  // 循环条件。此处使用闭区间方法
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {  // 注意判定条件。nums[mid] 比 nums[mid + 1] 大，可以确定 mid 右侧为蓝色
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        No162_FindPeakElement peakElement = new No162_FindPeakElement();
        int[] nums = new int[]{1, 2, 1, 3, 5, 6, 4};
        System.out.println(peakElement.findPeakElement(nums));
    }
}
