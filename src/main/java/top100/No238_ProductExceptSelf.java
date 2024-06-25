package top100;

public class No238_ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        // L R 分别表示左右两侧的乘积
        int[] L = new int[len];
        int[] R = new int[len];
        int[] res = new int[len];

        // L[i] 为索引左侧所有元素的乘积
        // 对于索引为 0 的元素，其左侧没有元素，所以 L[0] = 1
        L[0] = 1;
        for (int i = 1; i < len; i++) {
            L[i] = L[i - 1] * nums[i - 1];
        }

        // R[i] 为索引右侧所有元素的乘积
        // 对于索引 len - 1 的元素，其右侧没有元素，所以 R[len - 1] = 1
        R[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            R[i] = R[i + 1] * nums[i + 1];
        }

        // 对于索引 i，除 num[i] 之外的所有元素乘积就是 左侧所有元素的乘积 × 右侧所有元素的乘积
        for (int i = 0; i < len; i++) {
            res[i] = L[i] * R[i];
        }

        return res;
    }
}
