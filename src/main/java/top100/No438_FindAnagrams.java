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
                // 当窗口符合条件时，把起始索引加入 result 中
                if (valid == need.size()) res.add(left);

                char d = s.charAt(left);  // 要移除的字符
                left++;

                // 窗口数据的更新
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


    /*解题思路
    使用滑动窗口：
    使用两个哈希表来比较窗口内的字符频率：need 表示字符串 p 中字符的需求量，window 表示当前窗口内的字符统计。
    通过滑动窗口的方法，在字符串 s 上移动一个大小为 p.length() 的窗口，在每个窗口内比较字符频率是否一致。

    步骤：
    初始化：将字符串 p 的字符频率存入 need 哈希表。
    滑动窗口：从左到右遍历字符串 s，在每个位置维护一个大小为 p.length() 的窗口。
    每次移动右边界 right，将新进入窗口的字符加入 window 中，并更新 valid 计数（表示满足需求的字符数量）。
    如果 window 中某个字符的数量与 need 中相应字符的数量一致，则增加 valid。
    当窗口大小达到 p.length() 时，检查 valid 是否等于 need.size()（即所有字符的频率是否一致）。若一致，则当前窗口起始位置是一个符合条件的子串起始位置。
    收缩窗口：
    当窗口大小大于 p.length() 时，移动左边界 left，从窗口中移出最左边的字符，并更新 window 和 valid。
    继续向右移动 right 直到遍历完整个字符串 s。
    返回结果：
    收集所有符合条件的起始索引并返回。*/


    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.isEmpty() || p == null || p.isEmpty()) {
            return res;
        }

        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        // 初始化need哈希表
        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0; // 记录窗口中满足 need 条件的字符个数

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            // 更新窗口内字符计数
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            // 当窗口大小等于 p.length() 时，判断是否符合条件
            // 注意判断条件 p.length() 不能写成 need.size()，因为 need.size() 只是表示 p 中不同字符的数量，而不是 p 的总长度
            // 当 s = 'baa' p='aa' 时，need.size() 无法通过
            while (right - left >= p.length()) {
                if (valid == need.size()) {
                    res.add(left);
                }

                char d = s.charAt(left);
                left++;

                // 更新窗口内字符计数
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        No438_FindAnagrams n = new No438_FindAnagrams();
        List<Integer> ans = n.findAnagrams2(s, p);
        System.out.println(ans);
    }
}


/*
// 滑动窗口算法框架
void slidingWindow(string s, string t) {
    unordered_map<char, int> need, window;
    for (char c : t) need[c]++;

    int left = 0, right = 0;
    int valid = 0;
    while (right < s.size()) {
        // c 是将移入窗口的字符
        char c = s[right];
        // 右移窗口
        right++;
        // 进行窗口内数据的一系列更新
        ...

        */
/*** debug 输出的位置 ***//*

        printf("window: [%d, %d)\n", left, right);
        */
/********************//*


        // 判断左侧窗口是否要收缩
        while (window needs shrink) {
            // d 是将移出窗口的字符
            char d = s[left];
            // 左移窗口
            left++;
            // 进行窗口内数据的一系列更新
            ...
        }
    }
}


valid 变量表示窗口中满足 need 条件的字符个数，如果 valid 和 need.size 的大小相同，则说明窗口已满足条件，已经完全覆盖了串 T。

现在开始套模板，只需要思考以下四个问题：

        1、当移动 right 扩大窗口，即加入字符时，应该更新哪些数据？
        2、什么条件下，窗口应该暂停扩大，开始移动 left 缩小窗口？
        3、当移动 left 缩小窗口，即移出字符时，应该更新哪些数据？
        4、我们要的结果应该在扩大窗口时还是缩小窗口时进行更新？

如果一个字符进入窗口，应该增加 window 计数器；如果一个字符将移出窗口的时候，应该减少 window 计数器；当 valid 满足 need 时应该收缩窗口；应该在收缩窗口的时候更新最终结果
*/


