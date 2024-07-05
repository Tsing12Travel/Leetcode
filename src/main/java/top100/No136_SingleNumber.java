package top100;

public class No136_SingleNumber {
    public int singleNumber(int[] nums) {
        // 任何数与 0 进行异或运算的结果都是它本身
        int x = 0;

        for (int num : nums) {
            x ^= num;
        }

        return x;
    }
}
