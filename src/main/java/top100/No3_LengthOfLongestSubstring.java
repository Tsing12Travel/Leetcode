package top100;

import java.util.HashMap;
import java.util.HashSet;

/* 3.无重复字符的最长字串 */
// 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。

public class No3_LengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();

        if (len <= 1) {
            return len;
        }

        int left = 0;
        int right = 1;
        int res = 0;
        // 哈希集合，记录每个字符是否出现过
        HashSet<Character> set = new HashSet<>();
        set.add(s.charAt(left));

        while (right < len) {
            while (right < len && !set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
            }
            res = Math.max(res, right - left);
            set.remove(s.charAt(left));
            left++;
        }

        return res;
    }


    public static int lengthOfLongestSubstring2(String s) {
        int len = s.length();
        if (len == 0) return 0;

        int left = 0;
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < len; right++) {
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(left, map.get(s.charAt(right)));  // 更新左边界
            }

            map.put(s.charAt(right), right);  // 哈希表记录
            res = Math.max(res, right - left);  // 更新结果
        }

        return res;
    }


    public static void main(String[] args) {
        String str = "pwwkew";
        System.out.println(lengthOfLongestSubstring2(str));
    }
}
