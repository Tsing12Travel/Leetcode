package top100;

public class No136_SingleNumber {
    public int singleNumber(int[] nums) {
        // 任何数与 0 进行异或运算的结果都是它本身
        /* XOR(异或) 的性质：
        一个数与自身 XOR 的结果是 0，例如：a ^ a = 0。
        一个数与 0 XOR 的结果是其本身，例如：a ^ 0 = a。
        XOR 运算满足交换律和结合律。*/
        int res = 0;

        for (int num : nums) {
            res ^= num;
        }

        return res;
    }


    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        No136_SingleNumber No136 = new No136_SingleNumber();
        System.out.println(No136.singleNumber(nums));  // 输出: 4
    }
}
