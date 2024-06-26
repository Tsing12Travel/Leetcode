package leetBook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class No268_MissingNumber {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }

        return nums.length;
    }

    public int missingNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(i)) return i;
        }

        return nums.length;
    }

    // 根据出现的次数的奇偶性，可以使用按位异或运算得到丢失的数字。按位异或运算 ⊕ 满足交换律和结合律，且对任意整数 x 都满足 x⊕x=0 和 x⊕0=x
    /*首先，遍历数组 nums，对 xor 变量进行异或运算。这一步会将所有数组中的数进行异或。
    接下来，遍历从 0 到 𝑛 的所有数，并再次对 xor 变量进行异或运算。由于 nums 中缺少一个数，这样就相当于异或了从 0 到 𝑛 的所有数中的每个数一次。
    由于异或运算的性质，相同的数异或结果为 0，所以 nums 中的数和从 0 到 𝑛 的数中除去缺失的那个数外，所有数都成对异或为 0，最终剩下的 xor 值就是缺失的那个数。*/
    public int missingNumber3(int[] nums) {
        int xor = 0;
        int n = nums.length;

        for (int num : nums) {
            xor ^= num;
        }

        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }

        return xor;
    }

    // 将数组 nums 的元素之和记为 arrSum，则 arrSum 比 total = n(n+1)/2 少了丢失的一个数字，因此丢失的数字即为 total 与 arrSum 之差
    public int missingNumber4(int[] nums) {
        int n = nums.length;
        int total = n * (n + 1) / 2;
        int arrSum = 0;

        for (int num : nums) {
            arrSum += num;
        }

        return total - arrSum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1};
        int[] nums2 = new int[]{3, 0, 1};

        No268_MissingNumber missingNumber = new No268_MissingNumber();
        System.out.println(missingNumber.missingNumber3(nums2));
    }
}
