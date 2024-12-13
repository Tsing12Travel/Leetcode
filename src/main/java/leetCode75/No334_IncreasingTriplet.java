package leetCode75;

public class No334_IncreasingTriplet {
    /*贪心：
    从左到右遍历数组 nums，遍历过程中维护两个变量 first 和 second，分别表示递增的三元子序列中的第一个数和第二个数，任何时候都有 first < second。
    初始时，first = nums[0]，second = +∞。对于 1 ≤ i < n，当遍历到下标 i 时，令 num = nums[i]，进行如下操作：
    如果 num > second，则找到了一个递增的三元子序列，返回 true；
    否则，如果 num > first，则将 second 的值更新为 num；
    否则，将 first 的值更新为 num。
    如果遍历结束时没有找到递增的三元子序列，返回 false。
    上述做法的贪心思想是：为了找到递增的三元子序列，first 和 second 应该尽可能地小，此时找到递增的三元子序列的可能性更大。

    解释：赋初始值的时候，已经满足 second > first了，现在找第三个数 third
    (1) 如果 third 比 second 大，那就是找到了，直接返回 true
    (2) 如果 third 比 second 小，但是比 first 大，那就把 second 指向 third，然后继续遍历找 third
    (3) 如果 third 比 first 还小，那就把 first 指向 third，然后继续遍历找 third（这样的话 first 会跑到 second 的后边，但是不要紧，因为在 second 的前边，老 first 还是满足的）
    */
    public boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        if (len < 3) return false;

        int first = nums[0];
        int second = Integer.MAX_VALUE;
        for (int i = 1; i < len; i++) {
            if (nums[i] > second) {
                return true;
            } else if (nums[i] > first) {
                second = nums[i];
            } else {
                first = nums[i];
            }
        }

        return false;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 5, 0, 4, 6};
        No334_IncreasingTriplet No334 = new No334_IncreasingTriplet();
        System.out.println(No334.increasingTriplet(nums));
    }
}
