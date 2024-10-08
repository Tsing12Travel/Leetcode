package interview;

public class RepeatedSubstring {
    // 字符串匹配法：通过将字符串拼接两次并去掉首尾字符，检查原字符串是否出现在新字符串中。如果是，则说明原字符串是由一个较短的子字符串重复组成的
    public static boolean isRepeatedSubstring(String s) {
        // 拼接字符串并去掉第一个和最后一个字符
        String doubled = (s + s).substring(1, 2 * s.length() - 1);
        // 检查原字符串是否是新字符串的子串
        return doubled.contains(s);
    }


    // KMP 算法：使用前缀数组来找到字符串的最长相同前后缀长度，并检查是否可以整除字符串的长度，从而判断是否由重复子字符串构成
    public static boolean isRepeatedSubstring2(String s) {
        int n = s.length();
        int[] prefix = new int[n];
        int j = 0;

        // 构建 KMP 的前缀数组
        for (int i = 1; i < n; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = prefix[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
                prefix[i] = j;
            }
        }

        // 最长相同前后缀长度
        int repeatedLength = prefix[n - 1];
        return repeatedLength > 0 && n % (n - repeatedLength) == 0;
    }

    public static void main(String[] args) {
        System.out.println(isRepeatedSubstring("abab"));  // 输出: true
        System.out.println(isRepeatedSubstring("aba"));   // 输出: false
        System.out.println(isRepeatedSubstring("abcabcabc"));  // 输出: true
        System.out.println(isRepeatedSubstring2("a"));     // 输出: false
    }
}
