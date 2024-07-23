package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No5_LongestPalindrome {
    // 中心扩展算法
    // 枚举所有的「回文中心」并尝试「扩展」，直到无法扩展为止，此时的回文串长度即为此「回文中心」下的最长回文串长度
    public String longestPalindrome(String s) {
        int len = s.length();
        String res = "";
        if (len == 0) return res;

        for (int i = 0; i < len; i++) {
            String s1 = palindrome(s, i, i);
            String s2 = palindrome(s, i, i + 1);
            // String currLongest = s1.length() > s2.length() ? s1 : s2;
            // res = res.length() > currLongest.length() ? res : currLongest;
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }

        return res;
    }

    private String palindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }


    // Manacher 算法
    public String longestPalindrome2(String s) {
        int start = 0, end = -1;
        StringBuffer t = new StringBuffer("#");
        for (int i = 0; i < s.length(); ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        t.append('#');
        s = t.toString();

        List<Integer> arm_len = new ArrayList<Integer>();
        int right = -1, j = -1;
        for (int i = 0; i < s.length(); ++i) {
            int cur_arm_len;
            if (right >= i) {
                int i_sym = j * 2 - i;
                int min_arm_len = Math.min(arm_len.get(i_sym), right - i);
                cur_arm_len = expand(s, i - min_arm_len, i + min_arm_len);
            } else {
                cur_arm_len = expand(s, i, i);
            }

            arm_len.add(cur_arm_len);
            if (i + cur_arm_len > right) {
                j = i;
                right = i + cur_arm_len;
            }

            if (cur_arm_len * 2 + 1 > end - start) {
                start = i - cur_arm_len;
                end = i + cur_arm_len;
            }
        }

        StringBuffer ans = new StringBuffer();
        for (int i = start; i <= end; ++i) {
            if (s.charAt(i) != '#') {
                ans.append(s.charAt(i));
            }
        }

        return ans.toString();
    }

    public int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return (right - left - 2) / 2;
    }


    // 动态规划
    public static String longestPalindrome3(String s) {
        int n = s.length();
        // dp[i][j] 表示 i到j是否是回文串
        boolean[][] dp = new boolean[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], false);
        }

        int res = 0;
        String str = "";
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    res = Math.max(j - i, res);
                    if (j - i >= res) {
                        str = s.substring(i, j + 1);
                    }
                }
            }
        }
        return str;
    }


    public static void main(String[] args) {
        // String str = "babad";
        String str = "cbbd";
        No5_LongestPalindrome sol = new No5_LongestPalindrome();
        System.out.println(sol.longestPalindrome(str));
    }
}
