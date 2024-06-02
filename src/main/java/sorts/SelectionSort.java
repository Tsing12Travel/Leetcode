package sorts;

/* 选择排序 */
// 开启一个循环，每轮从未排序区间选择最小的元素，将其放到已排序区间的末尾
public class SelectionSort {
    public void selectionSort(int[] nums) {
        int len = nums.length;
        // 外循环：未排序区间为 [0, len - 1]
        for (int i = 0; i < len - 1; i++) {
            // 内循环：找到未排序区间的最小元素
            int k = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[k]) {
                    // 最小元素的索引
                    k = j;
                }
            }
            // 将该最小元素与未排序区间的首个元素交换
            int temp = nums[i];
            nums[i] = nums[k];
            nums[k] = temp;
        }
    }
}
