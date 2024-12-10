package leetCode75;

/*
1071. 字符串的最大公因子
对于字符串 s 和 t，只有在 s = t + t + t + ... + t + t（t 自身连接 1 次或多次）时，我们才认定 “t 能除尽 s”。
给定两个字符串 str1 和 str2 。返回 最长字符串 x，要求满足 x 能除尽 str1 且 x 能除尽 str2 。
*/

public class No1071_GcdOfStrings {
    public String gcdOfStrings(String str1, String str2) {
        // 如果 str1 + str2 不等于 str2 + str1，说明不存在公因子
        if (!(str1 + str2).equals(str2 + str1)) return "";

        // 计算两个字符串长度的 GCD
        int gcdLength = gcd(str1.length(), str2.length());

        // 返回 str1 的前 gcdLength 个字符
        return str1.substring(0, gcdLength);
    }


    // 计算两个数的 GCD（欧几里得算法，迭代版）
    private int gcd(int len1, int len2) {
        int remainder = len1 % len2;

        while (remainder != 0) {
            len1 = len2;
            len2 = remainder;
            remainder = len1 % len2;
        }

        return len2;
    }


    // 计算两个数的 GCD（欧几里得算法，迭代版）(辗转相除法)
    private int gcd1(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }


    // 计算两个数的 GCD（欧几里得算法，递归版）
    private int gcd2(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    public static void main(String[] args) {
        No1071_GcdOfStrings No1071 = new No1071_GcdOfStrings();
        System.out.println(No1071.gcdOfStrings("ABCABC", "ABC")); // 输出：ABC
        System.out.println(No1071.gcdOfStrings("ABABAB", "ABAB")); // 输出：AB
        System.out.println(No1071.gcdOfStrings("LEET", "CODE"));   // 输出：""
    }
}
