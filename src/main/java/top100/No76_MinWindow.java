package top100;

import java.util.HashMap;
import java.util.Map;

/*
76. 最小覆盖子串
给你一个字符串 s 、一个字符串 t。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 ""
*/

/*
解题思路
滑动窗口:
    使用两个指针 left 和 right 表示当前的窗口范围。
    通过移动 right 扩大窗口，找到一个覆盖了 t 的窗口。
    通过移动 left 收缩窗口，寻找最小覆盖。
字符计数:
    使用一个哈希表 need 记录 t 中每个字符的需求数量。
    使用另一个哈希表 window 记录当前窗口中每个字符的数量。
有效字符匹配:
    记录窗口中已经满足需求的字符个数 valid，当 valid 等于 t 中不同字符的数量时，表示当前窗口覆盖了所有字符。
更新结果:
    当找到一个有效窗口后，记录其长度并更新最小子串。
优化:
    移动指针和更新窗口的操作是线性的，最终复杂度为 O(n+m)，其中 n 是字符串 s 的长度，m 是字符串 t 的长度。
*/
public class No76_MinWindow {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";

        Map<Character, Integer> need = new HashMap<>();
        for (char x : t.toCharArray()) {
            need.put(x, need.getOrDefault(x, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int start = 0;
        int valid = 0;
        int maxLen = Integer.MAX_VALUE;
        Map<Character, Integer> window = new HashMap<>();

        while (right < s.length()) {
            char x = s.charAt(right);
            right++;

            if (need.containsKey(x)) {
                window.put(x, window.getOrDefault(x, 0) + 1);
                if (window.get(x).equals(need.get(x))) valid++;
            }

            while (valid == need.size()) {
                if (right - left < maxLen) {
                    start = left;
                    maxLen = right - left;
                }

                char y = s.charAt(left);
                left++;
                if (need.containsKey(y)) {
                    if (window.get(y).equals(need.get(y))) valid--;
                    window.put(y, window.get(y) - 1);
                }
            }
        }

        return maxLen == Integer.MAX_VALUE ? "" : s.substring(start, start + maxLen);
    }


    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        No76_MinWindow No76 = new No76_MinWindow();
        System.out.println("最小覆盖子串: " + No76.minWindow(s, t));
    }
}
