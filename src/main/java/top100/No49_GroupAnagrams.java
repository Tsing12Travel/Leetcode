package top100;

/* 49.字母异位词分组 */
// 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
// 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。

import java.util.*;

public class No49_GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return null;
        }

        // 创建一个 HashMap 用于存储异位词分组的结果，键为排序后的字符串，值为对应分组的字符串列表
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 对当前字符串进行排序，作为 HashMap 的键
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);

            // 如果 HashMap 中不存在以 key 为键的列表，则创建一个新列表并放入 HashMap 中
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            // 将当前字符串添加到对应的列表中
            map.get(key).add(str);
        }

        // 将 HashMap 的值（即所有异位词分组）转换为 List 并返回
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] str = new String[]{"eat","tea","tan","ate","nat","bat"};
        List<List<String>> res = groupAnagrams(str);
        System.out.println(res);
    }
}
