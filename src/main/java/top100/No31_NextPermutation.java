package top100;

import java.util.Arrays;

// 算法推导
/* 1.我们希望下一个数 比当前数大，这样才满足 “下一个排列” 的定义。因此只需要 将后面的「大数」与前面的「小数」交换，就能得到一个更大的数。
比如 123456，将 5 和 6 交换就能得到一个更大的数 123465

2.我们还希望下一个数 增加的幅度尽可能的小，这样才满足“下一个排列与当前排列紧邻“的要求。为了满足这个要求，我们需要：
    a.在 尽可能靠右的低位 进行交换，需要 从后向前 查找
    b.将一个 尽可能小的「大数」 与前面的「小数」交换。比如 123465，下一个排列应该把 5 和 4 交换而不是把 6 和 4 交换
    c.将「大数」换到前面后，需要将「大数」后面的所有数 重置为升序，升序排列就是最小的排列。
    以 123465 为例：首先按照上一步，交换 5 和 4，得到 123564；然后需要将 5 之后的数重置为升序，得到 123546。
    显然 123546 比 123564 更小，123546 就是 123465 的下一个排列 */


/* 算法过程
1.从后向前 查找第一个 相邻升序 的元素对 (i, j)，满足 A[i] < A[j]。此时 [j, end) 必然是降序
2.在 [j, end) 从后向前 查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」、「大数」
3.将 A[i] 与 A[k] 交换
4.可以断定这时 [j, end) 必然是降序，逆置 [j, end)，使其升序
5.如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin, end) 为一个降序顺序，则直接跳到步骤 4 */

public class No31_NextPermutation {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len <= 1) return;

        int i = len - 2;
        int j = len - 1;
        int k = len - 1;

        // 找到 nums[i] < nums[j]
        while (i >= 0 && nums[i] >= nums[j]) {
            i--;
            j--;
        }

        // 不是最后一个排列
        if (i >= 0) {
            // 找到 nums[i] < nums[k]
            while (nums[i] >= nums[k]) {
                k--;
            }

            // 找到 nums[i] < nums[k]
            swap(nums, i, k);
        }

        // 让下标区间在 [j, len - 1] 内的数升序排列
        // 注意为什么 reverse(nums, j, len - 1) 直接调用 swap(nums, start, end) 可以让下标区间在 [j, len - 1] 内的数升序排列
        // 因为跳出 while (i >= 0 && nums[i] >= nums[j]) 时有 j < 0 (对应整个数组降序排列)
        // 或 nums[i] < nums[j] (即从后往前找到第一个 nums[i] 小于 nums[j] => nums[j] 到数组末尾均为降序排列)
        reverse(nums, j, len - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }


    // 做法与上面一致，少定义一个变量(可读性下降，内存消耗竟然也比上面方法多，不理解)。注意调用了上面的 swap() 和 reverse() 方法
    public void nextPermutation2(int[] nums) {
        int len = nums.length;
        if (len <= 1) return;

        int i = len - 2;
        int j = len - 1;

        // 找到 nums[i] < nums[j]
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            // 找到 nums[i] < nums[j]
            while (nums[i] >= nums[j]) {
                j--;
            }

            swap(nums, i, j);
        }

        // 让下标区间在 [j, len - 1] 内的数升序排列
        reverse(nums, i + 1, len - 1);
    }


    public static void main(String[] args) {
        // int[] nums = {1, 2, 3};
        int[] nums = {3, 2, 1};
        // int[] nums = {1, 1, 5};
        No31_NextPermutation sol = new No31_NextPermutation();
        sol.nextPermutation2(nums);
        System.out.println(Arrays.toString(nums));
    }
}
