package top100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* 3.无重复字符的最长字串 */
// 给定一个字符串 s，请你找出其中不含有重复字符的最长子串的长度。

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


    /* 步骤
     * 哈希表 map 统计：指针 j 遍历字符 s，哈希表统计字符 s[j] 最后一次出现的索引
     * 更新左指针 i：根据上轮左指针 i 和 map[s[j]]，每轮更新左边界 i，保证区间 [i + 1, j] 内无重复字符且最大
     * 更新结果 res：取上轮 res 和本轮双指针区间 [i + 1, j] 的宽度（即 j − i）中的最大值
     */
    public static int lengthOfLongestSubstring2(String s) {
        int len = s.length();
        if (len == 0) return 0;

        int left = -1;
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < len; right++) {
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(left, map.get(s.charAt(right)));  // 更新左边界
                // left = map.get(s.charAt(right));  // 此为错误写法，如字符串 abba，遍历到第二个 a 时，左指针又跳回到最左边 a 去了，导致错误的左边界
            }

            map.put(s.charAt(right), right);  // 哈希表记录
            res = Math.max(res, right - left);  // 更新结果
        }

        return res;
    }


    // 动态规划 + 哈希表
    public int lengthOfLongestSubstring3(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int res = 0, tmp = 0, len = s.length();
        for (int j = 0; j < len; j++) {
            int i = dic.getOrDefault(s.charAt(j), -1);  // 获取索引 i
            dic.put(s.charAt(j), j);  // 更新哈希表
            tmp = tmp < j - i ? tmp + 1 : j - i;  // dp[j - 1] -> dp[j]
            res = Math.max(res, tmp);  // max(dp[j - 1], dp[j])
        }
        return res;
    }


    public static void main(String[] args) {
        // String str = "abba";
        String str = "pwwkew";
        System.out.println(lengthOfLongestSubstring2(str));
    }
}
