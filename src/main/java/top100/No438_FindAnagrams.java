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


    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        No438_FindAnagrams n = new No438_FindAnagrams();
        List<Integer> ans = n.findAnagrams(s, p);
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


