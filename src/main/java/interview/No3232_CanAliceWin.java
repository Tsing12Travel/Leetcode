package interview;

public class No3232_CanAliceWin {
    public boolean canAliceWin(int[] nums) {
        int s1 = 0, s2 = 0;

        for (int num : nums) {
            if (0 <= num && num <= 9) {
                s1 += num;
            } else {
                s2 += num;
            }
        }

        return s1 != s2;
    }


    // 单数正加，双数反减，胜在不等，零则必输！
    // 设 s1 为 nums 中的所有个位数之和，s2 为 nums 中的所有两位数之和。注意题目保证只有个位数和两位数。
    // Alice 若要获胜，必须满足 s1>s2 或者 s1<s2，即 s1!=s2。
    // 代码实现时，可以令 s=s1−s2，即累加 nums 的所有元素，把其中的两位数变成相反数累加。这样最后只需判断 s!=0 即可
    public boolean canAliceWin2(int[] nums) {
        int s = 0;

        for (int x : nums) {
            s += x < 10 ? x : -x;
        }

        return s != 0;
    }


    public static void main(String[] args) {
        No3232_CanAliceWin No3232 = new No3232_CanAliceWin();
        int[] nums = {1, 2, 3, 4, 10};
        System.out.println(No3232.canAliceWin(nums));

        int[] nums2 = {1, 2, 3, 4, 5, 14};
        System.out.println(No3232.canAliceWin(nums2));

        int[] nums3 = {5, 5, 5, 25};
        System.out.println(No3232.canAliceWin(nums3));
    }
}
