package doublePointer;

/*
5.最长回文字串
给你一个字符串 s，找到 s 中最长的回文子串。
如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
*/

public class No5_LongestPalindrome {
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 以 s[i] 为中心的最长回文子串
            String s1 = palindrome(s, i, i);
            // 以 s[i] 和 s[i+1] 为中心的最长回文子串
            String s2 = palindrome(s, i, i + 1);
            // res = longest(res, s1, s2)
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    // 在 s 中寻找以 s[l] 和 s[r] 为中心的最长回文串
    String palindrome(String s, int left, int right) {
        // 防止索引越界(可能会因为左右越界跳出循环，如 left == -1)
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            // 双指针，向两边展开
            left--;
            right++;
        }

        // 返回以 s[l] 和 s[r] 为中心的最长回文串
        // 回溯到回文字符串的起始和结束位置 -> left++; right--, 由于 substring 是左闭右开区间，故回文串为 [left + 1, right)
        return s.substring(left + 1, right);
    }
}
