package top100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class No438_FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> windows = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        // 初始化 need
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // 滑动窗口的左右边界：[left, right)
        int left = 0;
        int right = 0;
        int valid = 0;  // windows 中满足 need 的字符的个数

        while (right < s.length()) {
            char c = s.charAt(right);  // 将要加入 windows 中的字符
            right++;

            if (need.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(windows.get(c))) {
                    valid++;
                }
            }

            // 缩小窗口
            while (right - left >= p.length()) {
                if (valid == need.size()) res.add(left);

                char d = s.charAt(left);  // 要移除的字符
                left++;

                if (need.containsKey(d)) {
                    if (need.get(d).equals(windows.get(d))) {
                        valid--;
                    }
                    windows.put(d, windows.getOrDefault(d, 0) - 1);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "abab";
        String p = "ab";
        No438_FindAnagrams n = new No438_FindAnagrams();
        List<Integer> ans = n.findAnagrams(s, p);
        System.out.println(ans);
    }
}
