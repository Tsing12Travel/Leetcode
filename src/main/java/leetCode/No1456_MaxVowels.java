package leetCode;

/*
1456. 定长子串中元音的最大数目
给你字符串 s 和整数 k
请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数
英文中的 元音字母 为（a, e, i, o, u）
*/
public class No1456_MaxVowels {
    public int maxVowels(String s, int k) {
        int vowel = 0;
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            // 进入窗口
            char in = s.charAt(i);
            if (in == 'a' || in == 'e' || in == 'i' || in == 'o' || in == 'u') vowel++;

            // 窗口长度不足 k
            if (i < k - 1) continue;
            // 更新答案
            res = Math.max(res, vowel);

            // 离开窗口
            char out = s.charAt(i - k + 1);
            if (out == 'a' || out == 'e' || out == 'i' || out == 'o' || out == 'u') vowel--;
        }

        return res;
    }


    public static void main(String[] args) {
        No1456_MaxVowels No1456 = new No1456_MaxVowels();
        System.out.println(No1456.maxVowels("aeiou", 2));
        System.out.println(No1456.maxVowels("leetcode", 3));
        System.out.println(No1456.maxVowels("rhythms", 4));
        System.out.println(No1456.maxVowels("tryhard", 4));
    }
}
